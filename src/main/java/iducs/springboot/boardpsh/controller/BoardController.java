package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = {""})
    public String getBoardList(
            @RequestParam(value = "page", required = false, defaultValue = "1") String page,
            @RequestParam(value = "perPage", required = false, defaultValue = "8") String perPage,
            @RequestParam(value = "perPagination", required = false, defaultValue = "5") String perPagination,
            @RequestParam(value = "type", required = false, defaultValue = "") String type,
            @RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
            Model model) {
        var pageNum = Integer.parseInt(page);
        var sizeNum = Integer.parseInt(perPage);
        var pageSizeNum = Integer.parseInt(perPagination);
        var pageRequestDTO = new PageRequestDTO(pageNum, sizeNum, pageSizeNum, type, keyword,
                (map) -> "?"+map.entrySet().stream().map(e -> e.getKey() + "=" + e.getValue())
                        .collect(Collectors.joining("&")));
        model.addAttribute("list", boardService.findBoardAll(pageRequestDTO));
        model.addAttribute("pageRequestDTO", pageRequestDTO);
        return "/boards/list";
    }

    @GetMapping(value = {"/{bno}"})
    public String getBoardDetail(
            @PathVariable String bno,
            Model model) {
        var bnoNum = Long.parseLong(bno);
        model.addAttribute("board", boardService.findBoardById(Board.builder().bno(bnoNum).build()));
        return "/boards/detail";
    }

    @GetMapping("/modi-form/{bno}")
    public String getModifyForm(@PathVariable String bno,
                                Model model, HttpSession session) {
        var bnoNum = Long.parseLong(bno);
        Member member = (Member) session.getAttribute("me");
        if (member == null) {
            return "redirect:/members/login"; //로그인이 안된 경우
        }

        model.addAttribute("board", boardService.findBoardById(Board.builder().bno(bnoNum).build()));
        return "/boards/modi-form";
    }

    @PutMapping
    public String modifyBoard(@ModelAttribute("board") Board board, Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("me");
        if (member != null) {
            board.setWriterSeq(member.getSeq());
            if (boardService.registerBoard(board) == 1)
                return "redirect:/boards";
            else
                return "/errors/404"; //게시물 등록 예외 처리
        }
        else
            return "redirect:/members/login"; //로그인이 안된 경우
    }

    @GetMapping(value = {"/delete/{bno}"})
    public String deleteBoard(
            @PathVariable String bno,
            Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("me");
        if (member != null) {
            var bnoNum = Long.parseLong(bno);
            var res = boardService.deleteBoard(Board.builder().bno(bnoNum).writerName(member.getName()).build());
            if (res == 1)
                return "redirect:/boards";
            else
                return "/errors/404"; //게시물 삭제 예외 처리
        }
        else
            return "redirect:/members/login"; //로그인이 안된 경우
    }

    @GetMapping("/reg-form")
    public String getRegisterForm(Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("me");
        if (member == null) {
            return "redirect:/members/login"; //로그인이 안된 경우
        }

        model.addAttribute("board", Board.builder().build());
        return "/boards/reg-form";
    }

    @PostMapping
    public String createBoard(@ModelAttribute("board") Board board, Model model, HttpSession session) {
        Member member = (Member) session.getAttribute("me");
        if (member != null) {
            board.setWriterSeq(member.getSeq());
            if (boardService.registerBoard(board) == 1)
                return "redirect:/boards";
            else
                return "/errors/404"; //게시물 등록 예외 처리
        }
        else
            return "redirect:/members/login"; //로그인이 안된 경우
    }
}
