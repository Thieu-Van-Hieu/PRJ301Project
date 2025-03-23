/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dto.MessageResponse;
import entity.Member;
import entity.User;
import entity.UserInformation;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import services.MessageService;

/**
 * @author quann
 */
public class MessengerServlet extends HttpServlet {

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
            out.println("<title>Servlet MessengerServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MessengerServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private MessageResponse getMessageFromRequest(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Member member = (Member) session.getAttribute("member");

        int clubId = member.getClubId();
        int memberId = member.getId();
        String content = request.getParameter("content");

        MessageResponse message = new MessageResponse();
        message.setClubId(clubId);
        message.setMemberId(memberId);
        message.setMessage(content);

        return message;
    }

    /** Handles the HTTP GET method. */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Sau sẽ có dữ liệu trên session
        Member member = (Member) session.getAttribute("member");

        MessageService service = new MessageService();
        ArrayList<MessageResponse> messages = service.getMessagesByClubId(member.getClubId());

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        request.setAttribute("action", session.getAttribute("action"));

        request.setAttribute("messages", messages);
        session.setAttribute("includeWeb", "messenger.jsp");

        request.getRequestDispatcher("/view/homePage.jsp").forward(request, response);
    }

    /** Handles the HTTP POST method. */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        MessageService service = new MessageService();
        String action = request.getParameter("action");
        request.setAttribute("action", action);

        if (action.equals("createMessage")) {
            MessageResponse message = getMessageFromRequest(request);
            if (message != null) {
                message = service.addMessage(message);
                session.setAttribute("action", "createMessage");
            }
        }

        if (action.equals("deleteMessage")) {
            int messageId = Integer.parseInt(request.getParameter("messageId"));
            service.deleteMessageById(messageId);
        }

        response.sendRedirect(request.getContextPath() + "/MessengerServlet");
    }

    /** Returns a short description of the servlet. */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
