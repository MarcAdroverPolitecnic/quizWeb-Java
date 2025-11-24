package org.example.quizweb.model;

import lombok.Getter;

@Getter
public enum Difficulty {
    EASY ("easy"),
    MEDIUM ("medium"),
    HARD ("hard");
    private final String value;

    Difficulty(String value) {
        this.value = value;
    }

}
