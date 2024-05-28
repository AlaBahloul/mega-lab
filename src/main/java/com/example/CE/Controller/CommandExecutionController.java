package com.example.CE.Controller;

import com.example.CE.Service.CommandExecutionCMD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class CommandExecutionController {

    @Autowired
    private CommandExecutionCMD commandExecutionCMD;

    @GetMapping("/ping")
    public String test(@RequestParam(required = false) String comm, Model model) {
        model.addAttribute("comm", comm);
        return "CE/ping";
    }

    @GetMapping(value = "/execute3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter executeCommand(@RequestParam String comm) {
        SseEmitter emitter = new SseEmitter();
        commandExecutionCMD.executeCommand("ping "+comm, emitter);
        return emitter;
    }
}