package controller;

import dto.MemberTaskResponse;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import dto.TaskAssignedByMeResponse;
import entity.Member;
import entity.User;
import entity.UserInformation;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import services.TaskService;

/**
 * @author quann
 */
public class TaskAssignedByMeServlet extends HttpServlet {

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
            out.println("<title>Servlet TaskAssignedByMeServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet TaskAssignedByMeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private TaskAssignedByMeResponse getTaskFromRequest(HttpServletRequest request) {
        String name = request.getParameter("taskName");
        String description = request.getParameter("taskDescription");
        ArrayList<Integer> assignedTo = new ArrayList<>();
        String[] assignedToArr = request.getParameterValues("taskAssignedTo[]");
        if (assignedToArr != null) {
            for (String assigned : assignedToArr) {
                assignedTo.add(Integer.parseInt(assigned));
            }
        }

        String taskStatus = request.getParameter("taskStatus");
        Timestamp dueDate = Timestamp.valueOf(request.getParameter("taskDueDate"));

        TaskAssignedByMeResponse task = new TaskAssignedByMeResponse();
        if ("editTask".equals(request.getParameter("action"))) {
            task.setId(Integer.parseInt(request.getParameter("taskId")));
        }
        task.setName(name);
        task.setDescription(description);
        task.setAssignedTo(assignedTo);
        task.setStatus(taskStatus);
        task.setDueDate(dueDate);

        return task;
    }

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Sau sẽ có dữ liệu trên session
        Member member = (Member) session.getAttribute("member");

        TaskService service = new TaskService();
        ArrayList<TaskAssignedByMeResponse> tasks = service.getTasksAssignedByMe(member.getId(), member.getClubId());

        ArrayList<MemberTaskResponse> members = service.getMembers(member);

        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        if (session.getAttribute("action") != null && session.getAttribute("action").equals("createTask")) {
            request.setAttribute("action", "createTask");
            session.removeAttribute("action");
            request.setAttribute("taskId", session.getAttribute("taskId"));
            session.removeAttribute("taskId");
        }

        request.setAttribute("error", session.getAttribute("error"));
        session.removeAttribute("error");

        request.setAttribute("members", members);
        request.setAttribute("tasks", tasks);
        session.setAttribute("includeWeb", "taskAssignedByMe.jsp");
        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
    }

    /** Handles the HTTP POST method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        String action = request.getParameter("action");
        TaskService service = new TaskService();

        if (action == null) {
            action = "";
        }

        if (action.equals("createTask")) {
            TaskAssignedByMeResponse task = getTaskFromRequest(request);
            task.setClubId(member.getClubId());
            task.setAssignedBy(member.getId());
            task = service.createTask(task);
            if (task != null) {
                session.setAttribute("action", "createTask");
                session.setAttribute("taskId", task.getId());
            } else {
                session.setAttribute("error", "Có lỗi xảy ra khi tạo task");
            }
        } else if (action.equals("editTask")) {
            TaskAssignedByMeResponse task = getTaskFromRequest(request);
            task = service.editTask(task);
            if (task == null) {
                session.setAttribute("error", "Có lỗi xảy ra khi sửa task");
            }
        } else if (action.equals("deleteTask")) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            if (!service.deleteTask(taskId)) {
                session.setAttribute("error", "Có lỗi xảy ra khi xóa task");
            }
        } else if (action.equals("setTaskUncompleted")) {
            int taskId = Integer.parseInt(request.getParameter("taskId"));
            if (!service.setTaskUncompleted(taskId)) {
                session.setAttribute("error", "Có lỗi xảy ra khi đánh dấu task chưa hoàn thành");
            }
        }

        response.sendRedirect(request.getContextPath() + "/TaskAssignedByMeServlet");
    }

    /** Returns a short description of the servlet. */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
