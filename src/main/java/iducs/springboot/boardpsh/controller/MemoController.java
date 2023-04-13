package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Memo;
import iducs.springboot.boardpsh.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/memo")
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
        return "/memo/list";
    }
    @GetMapping("/memo")
    public String getmemolist(Model model) {
        List<Memo> result = new ArrayList<>();
        result = memoService.readList();
        model.addAttribute("attr", result);
        return "/memo/list";
    }
    @GetMapping("/memo/{mno}")
    public String getmemo(@PathVariable("mno") Long mno, Model model) {
        Memo m = new Memo();
        m.setMno(mno);
        model.addAttribute("attr", memoService.read(m));
        return "/memo/one";
    }
}
