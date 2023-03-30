package iducs.springboot.boardthymeleaf.controller;

import iducs.springboot.boardthymeleaf.entity.MemoEntity;
import iducs.springboot.boardthymeleaf.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

@RestController
public class RestfulController {
    @Autowired
    MemoRepository memoRepository;

    @GetMapping("/save-memo")
    public String saveMemo() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("sample" + i).build();
            memoRepository.save(memo);
        });
        return "insert memo";
    }

    @GetMapping("/")
    public String getHome() {
        return "welcome-rest-request";
    }
}
