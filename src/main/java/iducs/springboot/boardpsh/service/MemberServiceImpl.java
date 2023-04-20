package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.Memo;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.entity.MemoEntity;
import iducs.springboot.boardpsh.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final MemberRepository memberRepository;

    @Override
    public int create(Member m) {
        MemberEntity entity = MemberEntity.builder()
                .seq(m.getSeq())
                .email(m.getEmail())
                .name(m.getName())
                .pw(m.getPw())
                .build();
        memberRepository.save(entity);
        return 1;
    }

    @Override
    public Member read(Member m) {
        return memberRepository
                .findById(m.getSeq())
                .map((MemberEntity entity) -> Member.builder()
                        .seq(entity.getSeq())
                        .pw(entity.getPw())
                        .name(entity.getName())
                        .email(entity.getEmail())
                        .build())
                .orElse(null);
    }

    @Override
    public List<Member> readList() {
        List<MemberEntity> entities = memberRepository.findAll();
        List<Member> result = new ArrayList<>();
        for (MemberEntity member : entities) {
            Member m = Member.builder()
                    .email(member.getEmail())
                    .name(member.getName())
                    .seq(member.getSeq())
                    .build();
            result.add(m);
        }
        return result;
    }

    @Override
    public int update(Member m) {
        return 0;
    }

    @Override
    public int delete(Member m) {
        return 0;
    }
}
