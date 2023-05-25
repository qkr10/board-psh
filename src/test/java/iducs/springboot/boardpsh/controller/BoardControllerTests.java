package iducs.springboot.boardpsh.controller;


import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.repository.BoardRepository;
import iducs.springboot.boardpsh.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static iducs.springboot.boardpsh.entity.QBoardEntity.boardEntity;

@SpringBootTest
public class BoardControllerTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void registerBoard() {
        Board board = Board.builder()
                .bno(1L)
                .title("title")
                .content("content")
                .writerSeq(1L)
                .writerEmail("email")
                .writerName("name")
                .build();

        boardService.registerBoard(board);
    }
}
