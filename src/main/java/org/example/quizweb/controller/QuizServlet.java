package org.example.quizweb.controller;

import java.io.IOException;
import java.util.List;

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

    private static final int DEFAULT_TIMER = 60;

    private final QuestionApiService questionApiService = new QuestionApiService();
    private final GameService gameService = new GameService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int timer = getIntAttribute(req, "timer", DEFAULT_TIMER);

        if (req.getAttribute("timer") == null) {
            initializeGameAttributes(req);
        }

        Difficulty difficulty = determineDifficulty(getIntAttribute(req, "correctAnswers", 0));
        req.setAttribute("difficulty", difficulty.toString());

        try {
            List<Question> questions = questionApiService.fetchQuestionByDifficulty(difficulty);
            Question question = questions.get(0);

            req.setAttribute("question", question);
            req.setAttribute("answers", question.getAllAnswers());
            req.setAttribute("timer", timer);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        req.getRequestDispatcher("quiz.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        int correctAnswers = parseInt(req.getParameter("correctAnswers"));
        int incorrectAnswers = parseInt(req.getParameter("incorrectAnswers"));
        int totalTime = parseInt(req.getParameter("totalTime"));
        int timer = parseInt(req.getParameter("timer"));

        boolean isCorrect = req.getParameter("answer") != null &&
                req.getParameter("answer").equals(req.getParameter("correctAnswer"));

        if (isCorrect) {
            timer += 5;
            correctAnswers++;
        } else {
            timer -= 5;
            incorrectAnswers++;
        }

        req.setAttribute("correctAnswers", correctAnswers);
        req.setAttribute("incorrectAnswers", incorrectAnswers);

        if (timer > 0) {
            req.setAttribute("timer", timer);
            req.setAttribute("totalTime", totalTime);
            doGet(req, resp);
            return;
        }

        endGame(req, correctAnswers, incorrectAnswers, totalTime);
        resp.sendRedirect(req.getContextPath() + "/menu");
    }


    private void initializeGameAttributes(HttpServletRequest req) {
        req.setAttribute("score", 0);
        req.setAttribute("correctAnswers", 0);
        req.setAttribute("incorrectAnswers", 0);
        req.setAttribute("totalTime", 0);
    }

    private Difficulty determineDifficulty(int correctAnswers) {
        if (correctAnswers >= 6) return Difficulty.HARD;
        if (correctAnswers >= 3) return Difficulty.MEDIUM;
        return Difficulty.EASY;
    }

    private void endGame(HttpServletRequest req, int correctAnswers, int incorrectAnswers, int totalTime) {
        User user = (User) req.getSession().getAttribute("user");
        gameService.addGame(new Game(user, correctAnswers, incorrectAnswers, totalTime));

        HttpSession session = req.getSession();
        session.invalidate();
    }

    private int getIntAttribute(HttpServletRequest req, String name, int defaultValue) {
        Object value = req.getAttribute(name);
        return (value instanceof Integer) ? (Integer) value : defaultValue;
    }

    private int parseInt(String value) {
        try {
            return Integer.parseInt(value);
        } catch (Exception e) {
            return 0;
        }
    }
}
