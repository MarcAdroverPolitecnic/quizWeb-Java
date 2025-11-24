package org.example.quizweb.controller;

import java.io.*;
import java.util.List;
import java.util.Objects;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.example.quizweb.model.Difficulty;
import org.example.quizweb.model.Game;
import org.example.quizweb.model.Question;
import org.example.quizweb.model.User;
import org.example.quizweb.service.GameService;
import org.example.quizweb.service.QuestionApiService;

@WebServlet(name = "quizServlet", value = "/quiz")
public class QuizServlet extends HttpServlet {
    private final QuestionApiService questionApiService = new QuestionApiService();
    private final GameService gameService = new GameService();

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int timer;
        if (req.getAttribute("timer") != null) {
            Object timerObject = (req.getAttribute("timer"));
            timer = (Integer) timerObject;
        } else {
            timer = 30;
            req.setAttribute("score", 0);
            req.setAttribute("correctAnswers", 0);
            req.setAttribute("incorrectAnswers", 0);
            req.setAttribute("totalTime", 0);
        }

        try {
            Difficulty difficulty = Difficulty.EASY;
            if ((Integer) (req.getAttribute("correctAnswers")) >= 3){
                difficulty = Difficulty.MEDIUM;
            } else if ((Integer) (req.getAttribute("correctAnswers")) >= 6){
                difficulty = Difficulty.HARD;
            }
            req.setAttribute("difficulty", difficulty.toString());
            List<Question> lista = questionApiService.fetchQuestionByDifficulty(difficulty);
            req.setAttribute("question", lista.get(0));
            req.setAttribute("answers", lista.get(0).getAllAnswers());
            req.setAttribute("timer", timer);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("quiz.jsp").forward(req,resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int correctAnswers = Integer.parseInt(req.getParameter("correctAnswers"));
        int incorrectAnswers = Integer.parseInt(req.getParameter("incorrectAnswers"));
        int totalTime = Integer.parseInt(req.getParameter("totalTime"));
        int timer = Integer.parseInt(req.getParameter("timer"));
        if (Objects.equals(req.getParameter("answer"), req.getParameter("correctAnswer"))) {
            timer += 5;
            correctAnswers++;
            req.setAttribute("correctAnswers", correctAnswers);
        } else  {
            timer -= 5;
            incorrectAnswers++;
            req.setAttribute("incorrectAnswers", incorrectAnswers);
        }

        if (timer > 0) {
            req.setAttribute("timer", timer);
            req.setAttribute("correctAnswers", correctAnswers);
            req.setAttribute("incorrectAnswers", incorrectAnswers);
            req.setAttribute("totalTime", totalTime);
            doGet(req,resp);
        } else {
            User user = (User) req.getSession().getAttribute("user");

            gameService.addGame(new Game(user, correctAnswers, incorrectAnswers, totalTime));

            HttpSession session = req.getSession();
            session.invalidate();
            resp.sendRedirect(req.getContextPath() + "/menu");
        }
    }
}