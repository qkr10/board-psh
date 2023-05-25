package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Board;

import java.util.List;

public interface BoardService {
    int registerBoard(Board board);
    Board findBoardById(Board board);
    List<Board> findBoardAll();
    int updateBoard(Board board);
    int deleteBoard(Board board);
}
