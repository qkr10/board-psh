package iducs.springboot.boardpsh.repository;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
