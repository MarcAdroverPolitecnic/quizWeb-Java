package org.example.quizweb.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Question {
    private String category;
    private QuestionText question;
    private String correctAnswer;
    private String [] incorrectAnswers;

    public Question(String category, QuestionText question, String correctAnswer, String[] incorrectAnswers) {
        this.category = category;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectAnswers = incorrectAnswers;


    }

    public List<String> getAllAnswers() {
        List<String> allAnswers = new ArrayList<>();
        allAnswers.add(correctAnswer);
        allAnswers.add(incorrectAnswers[0]);
        allAnswers.add(incorrectAnswers[1]);
        allAnswers.add(incorrectAnswers[2]);
        Collections.shuffle(allAnswers);

        return allAnswers;
    }
}