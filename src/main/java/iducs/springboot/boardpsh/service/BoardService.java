package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.BoardEntity;

import java.util.List;

public interface BoardService {
    PageResultDTO<Board, BoardEntity> getList(PageRequestDTO requestDTO);
    int registerBoard(Board board);
    Board findBoardById(Board board);

    PageResultDTO<Board, Object[]> findBoardAll(PageRequestDTO pageRequestDTO);

    List<Board> findBoardAll();
    int updateBoard(Board board);
    int deleteBoard(Board board);
}
