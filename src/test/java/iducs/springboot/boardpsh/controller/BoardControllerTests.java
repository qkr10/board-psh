package iducs.springboot.boardpsh.controller;


import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.repository.BoardRepository;
import iducs.springboot.boardpsh.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.LongStream;

@SpringBootTest
public class BoardControllerTests {
    @Autowired
    BoardService boardService;
    @Autowired
    BoardRepository boardRepository;

    @Test
    void initializeBoard() {
        boardRepository.deleteAll();
        LongStream.rangeClosed(1, 101).forEach(i -> {
            Board board = Board.builder()
                    .bno(i)
                    .title("title"+i)
                    .content("content"+i)
                    .writerSeq(i)
                    .replyCount(0L)
                    .build();
            boardService.registerBoard(board);
        });
    }
}
