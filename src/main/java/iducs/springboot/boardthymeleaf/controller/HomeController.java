package iducs.springboot.boardthymeleaf.controller;

import iducs.springboot.boardthymeleaf.entity.MemoEntity;
import iducs.springboot.boardthymeleaf.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.IntStream;

@Controller
public class HomeController {
    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/")
    public String goHome() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("egyou : " + i).build();
            memoRepository.save(memo);
        });
        return "index";
    }
}
