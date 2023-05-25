package iducs.springboot.boardpsh.controller;

import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.service.BoardService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping(value = {"/", ""})
    public String getBoardList(Model model) {
        model.addAttribute("key", "value");
        return "/boards/list";
    }

    @GetMapping("/reg-form")
    public String getRegisterForm(Model model) {
        model.addAttribute("board", Board.builder().build());
        return "/boards/register";
    }

    @PostMapping
    public String createBoard(@ModelAttribute("board") Board board, Model model, HttpServletRequest request) {
        var session = request.getSession();
        Member member = (Member) session.getAttribute("me");
        if (member != null) {
            //board.setWriterSeq(member.getSeq());
            if (boardService.registerBoard(board) == 1)
                return "redirect:/boards";
            else
                return "/errors/404"; //게시물 등록 예외 처리
        }
        else
            return "redirect:/members/reg-form"; //로그인이 안된 경우
    }
}
