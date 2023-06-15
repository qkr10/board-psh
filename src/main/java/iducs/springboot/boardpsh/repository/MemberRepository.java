package iducs.springboot.boardpsh.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.entity.QMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long>,
        QuerydslPredicateExecutor<MemberEntity> {
    List<MemberEntity> findByEmailAndPw(String email, String pwd);

    @Query("select m from MemberEntity m where m.email = :email and m.pw = :pw")
    MemberEntity getByEmailAndPw(@Param("email") String email, @Param("pw") String pwd);

    List<MemberEntity> getMemberEntitiesByEmail(String email);

    List<MemberEntity> getMemberEntitiesByMobile(String mobile);
}
