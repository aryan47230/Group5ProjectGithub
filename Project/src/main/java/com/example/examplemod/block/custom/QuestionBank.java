package com.example.examplemod.block.custom;

public class QuestionBank {

    public record Question(String prompt, String[] choices, int correctIndex) {}

    public static final List<Question> QUESTIONS = List.of(
        new Question(
            "What does 'int' stand for in Java?",
            new String[]{"Internal", "Integer", "Interval", "Interface"},
            1
        ),
        new Question(
            "Which symbol starts a comment in Python?",
            new String[]{"//", "/*", "#", "--"},
            2
        ),
        new Question(
            "What is 2^8?",
            new String[]{"128", "256", "512", "64"},
            1
        ),
        new Question(
            "What does CPU stand for?",
            new String[]{"Central Processing Unit", "Computer Power Unit",
                         "Core Processing Utility", "Central Program Uplink"},
            0
        )
    );

    public static Question getRandom() {
        return QUESTIONS.get((int)(Math.random() * QUESTIONS.size()));
    }
}