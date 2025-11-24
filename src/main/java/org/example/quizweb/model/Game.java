package org.example.quizweb.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "game")
public class Game {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "correct_answers")
    private int correctAnswers;
    @Column(name = "incorrect_answers")
    private int incorrectAnswers;
    @Column(name = "total_game_time")
    private int totalGameTime;

    public Game(User user, int correctAnswers, int incorrectAnswers, int totalGameTime) {
        this.user = user;
        this.correctAnswers = correctAnswers;
        this.incorrectAnswers = incorrectAnswers;
        this.totalGameTime = totalGameTime;
    }

    public Game(User user, int correctAnswers, int totalGameTime) {
        this.user = user;
        this.correctAnswers = correctAnswers;
        this.totalGameTime = totalGameTime;
    }
}

