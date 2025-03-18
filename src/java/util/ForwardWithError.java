/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author ngoct
 */
public class ForwardWithError {

    public static void forwardWithError(HttpServletRequest request, HttpServletResponse response, String errorMessage, String errorKey) throws ServletException, IOException {
        request.setAttribute("username", request.getParameter("username"));
        request.setAttribute(errorKey, errorMessage);
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
    }
}
