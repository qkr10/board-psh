package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Board;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.BoardEntity;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.repository.BoardRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
    private final BoardRepository boardRepository;

    @Override
    public PageResultDTO<Board, BoardEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("bno"));

        //var ret = findByCondition(requestDTO);
        //var result = boardRepository.findAll(ret, pageable);
        var result = boardRepository.findAll(pageable);
        return new PageResultDTO<>(result, Board::new, requestDTO);
    }

    @Override
    @Transactional
    public int registerBoard(Board board) {
        BoardEntity entity = new BoardEntity(board);
        boardRepository.save(entity);
        return 1;
    }

    @Override
    public Board findBoardById(Board board) {
        var result = boardRepository.findById(board.getBno());
        return result.map(Board::new).orElse(null);
    }

    @Override
    public PageResultDTO<Board, Object[]> findBoardAll(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("replyCount").descending());
        Page<Object[]> result = boardRepository.searchPage(
                pageRequestDTO.getType(),
                pageRequestDTO.getKeyword(),
                pageable
        );
        Function<Object[], Board> fn = (e ->
                new Board((BoardEntity) e[0],
                        (MemberEntity) e[1],
                        (Long) e[2])
        );
        return new PageResultDTO<>(result, fn, pageRequestDTO);
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
    @Transactional
    public int deleteBoard(Board board) {
        return boardRepository.deleteByBnoAndWriterName(board.getBno(), board.getWriterName());
    }

//    BooleanBuilder findByCondition(PageRequestDTO pageRequestDTO) {
//        String type = pageRequestDTO.getType();
//        BooleanBuilder booleanBuilder = new BooleanBuilder();
//
//        QBoardEntity qBoardEntity = QBoardEntity.boardEntity;
//
//        BooleanExpression expression = qBoardEntity.seq.gt(0L); // where seq > 0 and title == "title"
//        booleanBuilder.and(expression);
//
//        if(type == null || type.trim().length() == 0) {
//            return booleanBuilder;
//        }
//
//        String keyword = pageRequestDTO.getKeyword();
//
//        BooleanBuilder conditionBuilder = new BooleanBuilder();
//        if(type.contains("email")) { // email로 검색
//            conditionBuilder.or(qBoardEntity.email.contains(keyword));
//        }
//        if(type.contains("name")) { // name으로 검색
//            conditionBuilder.or(qBoardEntity.name.contains(keyword));
//        }
//        booleanBuilder.and(conditionBuilder);
//        return booleanBuilder;
//    }
}
