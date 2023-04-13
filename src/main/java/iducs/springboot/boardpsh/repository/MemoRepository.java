package iducs.springboot.boardpsh.repository;

import iducs.springboot.boardpsh.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
}
