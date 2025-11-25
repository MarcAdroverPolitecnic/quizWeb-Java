package org.example.quizweb.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.example.quizweb.model.User;
import org.example.quizweb.service.UserService;
import org.example.quizweb.util.Hasher;

import java.io.IOException;

@WebServlet(name = "signInServlet", value = "/signin")
public class SignInServlet extends HttpServlet {
    private final UserService service = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/signin.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String hashedPassword = Hasher.hash(password, "SHA-256");

        User newUser = new User(username, hashedPassword);

        if (service.getUserId(username) == null) {
            service.register(newUser);
        } else {
            req.setAttribute("error", "Already exists");
            req.getRequestDispatcher("/signin.jsp").forward(req, resp);
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", newUser);
        session.setMaxInactiveInterval(30 * 60);

        resp.sendRedirect(req.getContextPath() + "/quiz");
    }
}