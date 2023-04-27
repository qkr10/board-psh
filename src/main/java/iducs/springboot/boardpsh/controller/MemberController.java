package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;

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
