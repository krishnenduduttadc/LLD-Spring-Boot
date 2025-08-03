package com.example.demo24.onlineexam.model;

public class Option {
    private final int optionId;
    private final String text;

    public Option(int optionId, String text) {
        this.optionId = optionId;
        this.text = text;
    }

    public int getOptionId() { return optionId; }
    public String getText() { return text; }
}
