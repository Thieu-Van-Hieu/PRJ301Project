/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.ClubResponse;
import dto.CommentDTO;
import dto.PostDTO;
import dto.UserInformationResponse;
import entity.Member;
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
import services.ClubService;
import services.MemberService;
import services.UserService;
import util.FileService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50) // 50MB
public class ForumServlet extends HttpServlet {

    private static final String IMG_RELATIVE_PATH = "/assets/img/img-download";

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the
    // + sign on the left to edit the code.">
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
        ClubService clubService = new ClubService();
        PostService postService = new PostService();
        UserService userService = new UserService();
        MemberService memberService = new MemberService();

        int userId = -1;
        try {
            userId = (Integer) session.getAttribute("userId");
            if (userId == -1) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
        request.setAttribute("userId", userId);
        int clubId = (Integer) session.getAttribute("clubId");
        String clubName = clubService.clubName(clubId);
        UserInformationResponse user = userService.getFullNameAndAvatar(userId);
        String userFullName = user.getUserName();
        String userAvatarImg = user.getAvatar();
        ArrayList<ClubResponse> clubListItems = clubService.selectAllClubItems(userId);
        ArrayList<Post> posts = postService.getAllPostOfClub(clubId);
        Member member = memberService.getMemberInfor(userId, clubId);
        if ("love".equals(action)) {
            try {
                int postId = Integer.parseInt(request.getParameter("postLoveId"));
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                if (!postService.isLove(postId, memberId)) {
                    postService.addLove(postId, memberId);
                    session.setAttribute("success", "Bạn đã Tim thành công, vui lòng click vào Diễn Đàn để hiện kết quả!");
                } else {
                    throw new Exception();
                }
            } catch (Exception e){
                session.setAttribute("error", "Bạn đã Tim bài viết này rồi!");
            }
        } else if ("deletePost".equals(action)) {
            try {
                int memberPostId = Integer.parseInt(request.getParameter("postMemberId"));
                if (member.getId() == memberPostId || member.getRole().equals("Chủ Nhiệm")) {
                    int postDeleteId = Integer.parseInt(request.getParameter("postDeleteId"));
                    postService.deletePost(postDeleteId);
                    session.setAttribute("success", "Bạn đã Xoá thành công, vui lòng click vào Diễn Đàn để hiện kết quả!");
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                session.setAttribute("error", "Không phải chủ nhiệm sao lại đi xoá bài người ta!");
            }
        }

        String coverImg = clubService.getCoverImg(clubId);
        session.setAttribute("member", member);
        session.setAttribute("coverImg", coverImg);

        session.setAttribute("userFullName", userFullName);
        session.setAttribute("userAvatarImg", userAvatarImg);
        session.setAttribute("clubListItems", clubListItems);
        session.setAttribute("clubName", clubName);
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
        HttpSession session = request.getSession();
        int userId = -1;
        try {
            userId = (Integer) session.getAttribute("userId");
            if (userId == -1) {
                throw new Exception();
            }
        } catch (Exception e) {
            response.sendRedirect(request.getContextPath() + "/view/login.jsp");
        }
        String action = request.getParameter("action");
        PostService postService = new PostService();
        if (action.equals("add")) {
            try {
                int clubId = Integer.parseInt(request.getParameter("clubId"));
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                String content = request.getParameter("content");
                Timestamp createdAt = getCurrentTimestamp();
                Part filePart = request.getPart("file");
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                String fileExtension = FileService.getFileExtension(fileName);
                String uploadDir = getServletContext().getRealPath(IMG_RELATIVE_PATH);

                File uploadFolder = new File(uploadDir);
                if (!uploadFolder.exists()) {
                    uploadFolder.mkdirs();
                }
                String normalized = FileService.normalizeFileName(fileName);

                String filePath = uploadDir + File.separator + normalized;
                filePart.write(filePath);

                postService.addPost(new PostDTO(clubId, memberId, content, createdAt, normalized));
                session.setAttribute("success", "Bạn đã tạo bài viết thành công!");
            } catch (Exception e) {
                session.setAttribute("error", "Không tạo được rồi bạn ek!");
            }

        } else if ("comment".equals(action)) {
            try {
                String content = request.getParameter("content");
                int memberId = Integer.parseInt(request.getParameter("memberId"));
                int postId = Integer.parseInt(request.getParameter("postId"));
                Timestamp createdAt = getCurrentTimestamp();
                CommentDTO commentDTO = new CommentDTO(postId, memberId, content, createdAt);
                postService.addComment(commentDTO);
            } catch (Exception e) {
                session.setAttribute("error", "Không gửi được rồi bạn ek!");
            }
        }
        doGet(request, response);
    }

    private Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
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
