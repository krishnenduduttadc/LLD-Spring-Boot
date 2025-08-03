package com.example.demo16.onlineide.model;

public class CodeResult {
    private String output;
    private String error;
    private int exitCode;

    public CodeResult(String output, String error, int exitCode) {
        this.output = output;
        this.error = error;
        this.exitCode = exitCode;
    }

    public String getOutput() { return output; }
    public String getError() { return error; }
    public int getExitCode() { return exitCode; }
}
