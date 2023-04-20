package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.entity.MemoEntity;
import iducs.springboot.boardpsh.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.IntStream;

@Controller
public class HomeController {

    @GetMapping("/")
    public String goHome() {
        return "admin/index";
    }

    @GetMapping("/tables")
    public String getTables() { return "admin/tables"; }

    @GetMapping("/buttons")
    public String getButtons() { return "admin/buttons"; }

    @GetMapping("/cards")
    public String getCards() { return "admin/cards"; }
}
