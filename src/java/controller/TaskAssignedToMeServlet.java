/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;

import dto.MemberTaskResponse;
import dto.TaskAssignedToMeResponse;
import entity.Member;
import entity.User;
import entity.UserInformation;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.TaskService;

/**
 * @author quann
 */
public class TaskAssignedToMeServlet extends HttpServlet {

    /**
     * Handles requests for both HTTP GET and POST methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet TaskAssignedToMeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaskAssignedToMeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private TaskAssignedToMeResponse getTaskFromRequest(HttpServletRequest request) {
        String taskId = request.getParameter("taskId");
        String taskStatus = request.getParameter("taskStatus");
        String taskAssignedBy = request.getParameter("taskAssignedBy");
        Timestamp taskDueDate = Timestamp.valueOf(request.getParameter("taskDueDate"));

        TaskAssignedToMeResponse task = new TaskAssignedToMeResponse();
        task.setId(Integer.parseInt(taskId));
        task.setStatus(taskStatus);
        task.setAssignedByMemberName(taskAssignedBy);
        task.setDueDate(taskDueDate);

        return task;
    }

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Sau sẽ có dữ liệu trên session
        User user = new User.Builder().setId(1).build();
        UserInformation userInformation = new UserInformation.Builder().setFirstName("Hiếu").build();
        Member member = new Member.Builder().setId(2).setClubId(1).build();
        session.setAttribute("userInformation", userInformation);
        session.setAttribute("member", member);

        TaskService service = new TaskService();
        ArrayList<TaskAssignedToMeResponse> tasks = service.getTasksAssignedToMe(member.getClubId(),
                member.getId());

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        String taskId = request.getParameter("taskId");
        if (taskId == null) {
            taskId = "";
        } else {
            TaskAssignedToMeResponse task = service.getTaskAssignedToMe(Integer.parseInt(taskId));
            request.setAttribute("currentTask", task);
        }

        if ("updateStatus".equals(session.getAttribute("action"))) {
            session.removeAttribute("action");
            request.setAttribute("action", "updateStatus");
            request.setAttribute("taskId", session.getAttribute("taskId"));
            session.removeAttribute("taskId");
        }

        request.setAttribute("error", session.getAttribute("error"));
        session.removeAttribute("error");

        request.setAttribute("tasks", tasks);
        session.setAttribute("includeWeb", "taskAssignedToMe.jsp");
        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
    }

    /** Handles the HTTP POST method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");
        TaskService service = new TaskService();

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        if (action.equals("updateStatus")) {
            TaskAssignedToMeResponse task = getTaskFromRequest(request);
            task = service.updateStatusTask(task, member.getId());

            if (task == null) {
                session.setAttribute("error", "Cập nhật trạng thái thất bại");
            } else {
                session.setAttribute("success", "Cập nhật trạng thái thành công");
                if (service.isTaskCompleted(task.getId())) {
                    session.setAttribute("action", "updateStatus");
                    session.setAttribute("taskId", task.getId());
                }
            }

        }

        response.sendRedirect(request.getContextPath() + "/TaskAssignedToMeServlet");
    }

    /** Returns a short description of the servlet. */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
