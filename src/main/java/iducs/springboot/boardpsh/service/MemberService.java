package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;

import java.util.List;

public interface MemberService {
    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    boolean isExist(String email, String pwd);

    PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO);
}
