package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.service.MemberService;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RestApiController {
    private final MemberService memberService;

//    @GetMapping
//    public PageResultDTO<Member, MemberEntity> getMemberList(
//            @RequestParam Optional<Integer> page,
//            @RequestParam Optional<Integer> size
//    ) {
//        var pageRequestDTO = new PageRequestDTO();
//        if (page.isPresent() && size.isPresent()) {
//            pageRequestDTO = new PageRequestDTO(page.get(), size.get(), 5);
//        }
//        return memberService.getList(pageRequestDTO);
//    }

    @GetMapping("/")
    public String readList() {
        String result = "";

        return result;
    }
    @GetMapping("/{id}")
    public String readOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
    @GetMapping("/register-form")
    public String getCreate(Model model) { // register-form 호출
        String result = "";

        return result;
    }
    @PostMapping("/{id}")
    public String createOne(@RequestBody Member member, Model model) { // @RequestBody
        String result = "";

        return result;
    }
    @PutMapping("/{id}")
    public String updateOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Long id, Model model) {
        String result = "";

        return result;
    }
}