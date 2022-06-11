package com.kloong.retreat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/longstone")
public class LongstoneController {
    @GetMapping
    public String password() {
        return "/longstone/longstonePassword";
    }

    @PostMapping
    public String getInfo(@RequestParam String password) {
        if (!password.equals("19961213")) {
            return "wrongPassword";
        }
        return "/longstone/longstoneInfo";
    }
}
