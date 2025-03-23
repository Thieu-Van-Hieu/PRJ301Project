/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dto.ClubResponse;
import dto.MemberDTO;
import entity.Club;
import entity.Member;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import services.ClubService;
import services.MemberService;
import util.FileService;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, //2MB
        maxFileSize = 1024 * 1024 * 10, //10MB
        maxRequestSize = 1024 * 1024 * 50)
public class CreateClubServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateClubServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateClubServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private static final String IMG_DIR = "D:\\Study\\PRJ301\\NB_workplace\\PRJ301Project\\web\\assets\\img\\img-download";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ClubService clubService = new ClubService();
        MemberService memberService = new MemberService();
        int userId = (Integer)session.getAttribute("userId");
        String clubName = request.getParameter("clubName");
        String clubType = request.getParameter("type");
        String description = request.getParameter("description");
        Part avatar = request.getPart("avatarClub");
        Part coverImg = request.getPart("coverImgClub");
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = today.format(formatter);
        
        String avatarName = Paths.get(avatar.getSubmittedFileName()).getFileName().toString();
        String coverImgName = Paths.get(coverImg.getSubmittedFileName()).getFileName().toString();
        String uploadDir = IMG_DIR;

        File uploadFolder = new File(uploadDir);
        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs();
        }

        String avatarNormalized = FileService.normalizeFileName(avatarName);
        String coverImgNormalized = FileService.normalizeFileName(coverImgName);
        
        String avatarPath = uploadDir + File.separator + avatarNormalized;
        String coverImgPath = uploadDir + File.separator + coverImgNormalized;
        
        avatar.write(avatarPath);
        coverImg.write(coverImgPath);
        
        ClubResponse club = new ClubResponse(clubName, description, clubType, date, avatarName, coverImgName);
        int clubId = clubService.addClub(club);
        MemberDTO newMember = new MemberDTO(clubId, userId, 1, "Chủ Nhiệm");
        memberService.addMember(newMember);
        
        session.setAttribute("clubId", clubId);
        response.sendRedirect(request.getContextPath() + "/DiscoveryServlet?action=open&clubId=" + clubId);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
