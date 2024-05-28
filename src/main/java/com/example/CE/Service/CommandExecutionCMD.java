package com.example.CE.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class CommandExecutionCMD {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


    public void executeCommand(String command, SseEmitter emitter) {
        executorService.execute(() -> {
            try {
                String[] cmdArray;
                if (System.getProperty("os.name").startsWith("Windows")) {
                    cmdArray = new String[]{"cmd.exe", "/c", command};
                } else {
                    cmdArray = new String[]{"/bin/sh", "-c", command};
                }

                Process process = Runtime.getRuntime().exec(cmdArray);
                BufferedReader inputStream = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = inputStream.readLine()) != null) {
                    emitter.send(line);
                }
                emitter.complete();
                int exitCode = process.waitFor();
                System.out.println("Command executed with exit code: " + exitCode);
            } catch (Exception e) {
                emitter.completeWithError(e);
                e.printStackTrace();
            }
        });
    }
}
