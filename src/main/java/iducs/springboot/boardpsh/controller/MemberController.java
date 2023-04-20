package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;

    @GetMapping("/login")
    public String getLoginForm() {
        return "/members/login";
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
