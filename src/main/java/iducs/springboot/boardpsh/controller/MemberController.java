package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    final MemberService memberService;

//    @GetMapping(value = {"/{pn}/{size}"})
//    public String listMemberPagination(
//            @PathVariable("pn") Optional<String> pn,
//            @PathVariable("size") Optional<String> size,
//            Model model
//    ) {
//        var pageRequestDTO = new PageRequestDTO();
//        if (pn.isPresent() && size.isPresent()) {
//            var pageNum = Integer.parseInt(pn.get());
//            var sizeNum = Integer.parseInt(size.get());
//            pageRequestDTO = new PageRequestDTO(pageNum, sizeNum, 5);
//        }
//        var members = memberService.getList(pageRequestDTO);
//        if(members != null) {
//            model.addAttribute("list", members);
//            return "/members/list";
//        }
//        else {
//            model.addAttribute("error message", "목록 조회에 실패. 권한 확인");
//            return "/error/message";
//        }
//    }

    @GetMapping(value = {""})
    public String listMemberPagination2(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "perPage", required = false, defaultValue = "10") String perPage,
            @RequestParam(value = "perPagination", required = false, defaultValue = "5") String perPagination,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model
    ) {
        var pageNum = Integer.parseInt(page);
        var sizeNum = Integer.parseInt(perPage);
        var pageSizeNum = Integer.parseInt(perPagination);
        var pageRequestDTO = new PageRequestDTO(pageNum, sizeNum, pageSizeNum, type, keyword,
                (map) -> "?"+map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining("&")));

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

    @GetMapping("/pn")
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

//    @GetMapping("/pn/{pn}")
//    public String getMemberList2(@PathVariable String pn, Model model) {
//        var pnNum = Integer.parseInt(pn);
//        List<Member> members = memberService.getList(new PageRequestDTO(pnNum, 10, 5)).getDtoList();
//        if(members != null) {
//            model.addAttribute("list", members);
//            return "/members/list";
//        }
//        else {
//            model.addAttribute("error message", "목록 조회에 실패. 권한 확인");
//            return "/error/message";
//        }
//
//    }

    @GetMapping("/reg-form")
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
