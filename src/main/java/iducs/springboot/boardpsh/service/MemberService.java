package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Member;

import java.util.List;

public interface MemberService {
    int create(Member m);
    Member read(Member m);
    List<Member> readList();
    int update(Member m);
    int delete(Member m);

    boolean isExist(String email, String pwd);
}
