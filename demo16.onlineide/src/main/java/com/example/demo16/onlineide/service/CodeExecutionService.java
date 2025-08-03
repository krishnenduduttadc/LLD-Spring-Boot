package com.example.demo16.onlineide.service;

import com.example.demo16.onlineide.model.CodeRequest;
import com.example.demo16.onlineide.model.CodeResult;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class CodeExecutionService {

    private static final String JAVA_FILE = "Solution.java";
    private static final String CLASS_NAME = "Solution";

    public CodeResult execute(CodeRequest request) {
        if (!request.getLanguage().equalsIgnoreCase("java")) {
            return new CodeResult("", "Only Java is supported.", -1);
        }

        try {
            // 1. Write to Solution.java
            FileWriter writer = new FileWriter(JAVA_FILE);
            writer.write(request.getCode());
            writer.close();

            // 2. Compile
            Process compile = Runtime.getRuntime().exec("javac " + JAVA_FILE);
            String compileError = readStream(compile.getErrorStream());
            compile.waitFor();

            if (compile.exitValue() != 0) {
                return new CodeResult("", compileError, compile.exitValue());
            }

            // 3. Run
            Process run = Runtime.getRuntime().exec("java " + CLASS_NAME);
            String output = readStream(run.getInputStream());
            String error = readStream(run.getErrorStream());
            run.waitFor();

            return new CodeResult(output, error, run.exitValue());

        } catch (Exception e) {
            return new CodeResult("", e.getMessage(), -1);
        }
    }

    private String readStream(InputStream inputStream) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
}
