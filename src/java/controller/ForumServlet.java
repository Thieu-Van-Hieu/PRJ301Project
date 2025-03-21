/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.PostDTO;
import entity.Post;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import services.PostService;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import util.FileService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50)    //50MB
public class ForumServlet extends HttpServlet {
private static final String IMG_DIR = "D:\\Study\\PRJ301\\NB_workplace\\PRJ301Project\\web\\assets\\img\\img-download";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ForumServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ForumServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        int clubId = (Integer)session.getAttribute("clubId");
        int userId = (Integer)session.getAttribute("userId");
        
  
        PostService postService = new PostService();
        ArrayList<Post> posts = postService.getAllPostOfClub(clubId);

        session.setAttribute("clubId", clubId);
        session.setAttribute("userId", userId);
        session.setAttribute("posts", posts);
        session.setAttribute("includeWeb", "forum.jsp");
        response.sendRedirect(request.getContextPath() + "/view/homePage.jsp");
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        PostService postService = new PostService();
        if (action == null) {
            action = ""; // hoặc gán giá trị mặc định
        } else if (action.equals("add")) {
            try {
                int clubId = Integer.parseInt(request.getParameter("clubId"));
                int userId = Integer.parseInt(request.getParameter("userId"));
                String content = request.getParameter("content");
                Timestamp createdAt = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                    createdAt = new Timestamp(dateFormat.parse(request.getParameter("createTime")).getTime());

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Part filePart = request.getPart("file");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String fileExtension = FileService.getFileExtension(fileName);
                String uploadDir = "";

                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }
                String imgName = "Ảnh" + "_" + clubId + "." + fileExtension;
                String normalized = FileService.normalizeFileName(imgName);

                String filePath = uploadDir + File.separator + normalized;
                filePart.write(filePath);
                
                postService.addPost(new PostDTO(clubId, userId, content, createdAt, normalized));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        doGet(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
