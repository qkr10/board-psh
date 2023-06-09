package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.repository.MemberRepository;
import iducs.springboot.boardpsh.service.MemberService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

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
        memberRepository.deleteAll();
        IntStream.rangeClosed(1, 101).forEach(i -> {
            int zeroCount = i < 10 ? 3 : (i < 100 ? 2 : 1);
            String four = "0".repeat(zeroCount) + i;
            String five = "0" + four;
            MemberEntity member = MemberEntity.builder()
                    .email("a"+i+"@induk.ac.kr")
                    .name("name" + i)
                    .pw("name" + i)
                    .mobile("010-"+four+"-"+four)
                    .zipcode(five)
                    .seq((long) i)
                    .build();
            memberRepository.save(member);
        });
    }

    @Test
    void initializeAdmin() {
        MemberEntity member = MemberEntity.builder()
                .email("admin201912024@induk.ac.kr")
                .name("admin")
                .pw("admin")
                .mobile("010-1111-1111")
                .zipcode("00000")
                .build();
        memberRepository.save(member);
    }

    @Test
    public void testPageList() {
        var pageResultDTO = PageRequestDTO.builder().page(2).size(10).build();
        var resultDTO = memberService.getList(pageResultDTO);

        for (Member m : resultDTO.getDtoList())
            System.out.println(m);

        System.out.println("Prev : " + resultDTO.isPrev());
        System.out.println("Next : " + resultDTO.isNext());
        System.out.println("Total Page : " + resultDTO.getTotalPage());
        resultDTO.getPageList().forEach(i -> System.out.println(i));
    }

    @Test
    //@Transactional
    public void readMember() {
        Member member = new Member();
        member.setSeq(51L);
        Member result = null;

        if ((result = memberService.read(member)) != null)
            System.out.println("조회 성공! " + result.getEmail() + ":::" + result.getName());
        else
            System.out.println("조회 실패!");
    }
}
