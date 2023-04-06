package iducs.springboot.boardthymeleaf.controller;

import iducs.springboot.boardthymeleaf.domain.Memo;
import iducs.springboot.boardthymeleaf.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemoController {

    @Autowired
    MemoService memoService;

    public MemoController(MemoService memoService) {
        this.memoService = memoService;
    }

    @GetMapping("/init")
    public String initialize(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.initialize();
        model.addAttribute("attr", result);
        return "list";
    }
}
