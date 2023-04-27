package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.entity.MemoEntity;
import iducs.springboot.boardpsh.repository.MemoRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final HttpSession httpSession;

    @GetMapping("/")
    public String goHome(Model model) {
        return "admin/index";
    }

    @GetMapping("/tables")
    public String getTables() { return "admin/tables"; }

    @GetMapping("/buttons")
    public String getButtons() { return "admin/buttons"; }

    @GetMapping("/cards")
    public String getCards() { return "admin/cards"; }
}
