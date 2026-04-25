package com.example.examplemod.block.custom;

import java.util.List;

public class QuestionBank {

    public record Question(String prompt, String[] choices, int correctIndex) {}

    public static final List<Question> QUESTIONS = List.of(
        new Question(
            "To store the value 10.8 in a variable, what\nwould be a good Java numeric type to use?",
            new String[]{"int", "long", "byte", "float"},
            3
        ),
        new Question(
            "Which of the following is the correct way to\nwrite a single-line comment in Java?",
            new String[]{"# This is a comment", "* This is a comment", "// This is a comment", "/* This is a comment */"},
            2
        ),
        new Question(
            "Ruchi Sanghvi is known for contributing\nto what Facebook feature?",
            new String[]{"the Like button", "the News Feed", "Facebook Groups", "Facebook Messenger"},
            1
        ),
        new Question(
            "A node that has no children and appears\nat the end of a branch is called a",
            new String[]{"terminal", "endpoint", "tail", "leaf"},
            3
        )
    );

    public static Question getRandom() {
        return QUESTIONS.get((int)(Math.random() * QUESTIONS.size()));
    }
}