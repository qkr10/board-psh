package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.service.MemberService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;

    @GetMapping(value = {"/{pn}/{size}"})
    public String listMemberPagination(
            @PathParam("pn") Optional<Integer> pn,
            @PathParam("size") Optional<Integer> size,
            Model model
    ) {
        var pageRequestDTO = new PageRequestDTO();
        if (pn.isPresent() && size.isPresent()) {
            pageRequestDTO = new PageRequestDTO(pn.get(), size.get());
        }
        var members = memberService.getList(pageRequestDTO);
        if(members != null) {
            model.addAttribute("list", members);
            return "/members/list";
        }
        else {
            model.addAttribute("error message", "목록 조회에 실패. 권한 확인");
            return "/error/message";
        }
    }

    @GetMapping("/login")
    public String getLoginForm(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "/members/login";
    }

    @GetMapping("/logout")
    public String getLogout(HttpSession httpSession) {
        httpSession.removeAttribute("me");
        httpSession.invalidate();
        return "redirect:/";
    }

    @PostMapping(value = "/login")
    public String loginMember(@ModelAttribute("member") Member member, Model model) {
        String email = member.getEmail();
        String pwd = member.getPw();
        if (memberService.isExist(email, pwd))
            return "redirect:/";
        return "redirect:/members/login";
    }

    @GetMapping("/")
    public String getMemberList(Model model) {

        List<Member> members;
        if((members = memberService.readList()) != null) {
            model.addAttribute("list", members);
            return "/members/list";
        }
        else {
            model.addAttribute("error message", "목록 조회에 실패. 권한 확인");
            return "/error/message";
        }

    }

    @GetMapping("/pn/{pn}")
    public String getMemberList(@PathVariable int pn, Model model) {

        List<Member> members = memberService.getList(new PageRequestDTO(pn, 10)).getDtoList();
        if(members != null) {
            model.addAttribute("list", members);
            return "/members/list";
        }
        else {
            model.addAttribute("error message", "목록 조회에 실패. 권한 확인");
            return "/error/message";
        }

    }

    @GetMapping("/register")
    public String getRegisterForm(Model model) {
        model.addAttribute("member", Member.builder().build());
        return "/members/register";
    }

    @PostMapping("/register")
    public String registerMember(@ModelAttribute("member") Member member, Model model) {
        if (memberService.create(member) > 0)
            return "redirect:/members/login";
        else
            return "redirect:/members/register";
    }

    @GetMapping("/forgot-password")
    public String getForgotPasswordForm() {
        return "/members/forgot-password";
    }
}
