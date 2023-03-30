package iducs.springboot.boardthymeleaf.repository;

import iducs.springboot.boardthymeleaf.entity.MemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemoRepository extends JpaRepository<MemoEntity, Long> {
}
