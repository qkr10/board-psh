package iducs.springboot.boardpsh.repository;

import iducs.springboot.boardpsh.entity.BoardEntity;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.repository.search.SearchBoardRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long>,
        SearchBoardRepository {

}
