package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.entity.BoardEntity;
import iducs.springboot.boardpsh.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public int registerBoard(Board board) {
        BoardEntity entity = new BoardEntity(board);
        boardRepository.save(entity);
        return 1;
    }

    @Override
    public Board findBoardById(Board board) {
        return null;
    }

    @Override
    public List<Board> findBoardAll() {
        return null;
    }

    @Override
    public int updateBoard(Board board) {
        return 0;
    }

    @Override
    public int deleteBoard(Board board) {
        return 0;
    }
}
