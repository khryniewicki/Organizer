package com.khryniewicki.organizer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/layout")
public class ViewController {
    @GetMapping
    public String page1() {
        return "layoutDashboard";
    }


}
