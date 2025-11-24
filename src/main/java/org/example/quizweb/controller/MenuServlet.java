package org.example.quizweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.quizweb.service.GameService;

import java.io.IOException;

@WebServlet(name = "menuServlet", value = "/menu")
public class MenuServlet extends HttpServlet {

    private final GameService service = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", service.findAllBestGames());
        req.getRequestDispatcher("menu.jsp").forward(req,resp);
    }
}