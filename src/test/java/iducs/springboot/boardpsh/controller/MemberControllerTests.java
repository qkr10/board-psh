package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.repository.MemberRepository;
import iducs.springboot.boardpsh.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

import static java.lang.Long.parseLong;

@SpringBootTest
public class MemberControllerTests {
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    void contextLoads() {
        List<Member> result = memberService.readList();
        for (Member m : result)
            System.out.println(m.toString());
    }

    @Test
    void initializeMember() {
        IntStream.rangeClosed(1, 33).forEach(i -> {
            MemberEntity member = MemberEntity.builder()
                    .email("a"+i+"@induk.ac.kr")
                    .name("name" + i)
                    .pw("name" + i)
                    .seq((long) i)
                    .build();
            memberRepository.save(member);
        });
    }
}
