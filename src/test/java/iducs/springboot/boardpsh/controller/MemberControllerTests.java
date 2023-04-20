package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MemberControllerTests {
    @Autowired
    MemberService memberService;

    @Test
    void contextLoads() {
        List<Member> result = memberService.readList();
        for (Member m : result)
            System.out.println(m.toString());
    }
}
