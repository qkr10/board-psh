package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Member;
import iducs.springboot.boardpsh.domain.Memo;
import iducs.springboot.boardpsh.domain.PageRequestDTO;
import iducs.springboot.boardpsh.domain.PageResultDTO;
import iducs.springboot.boardpsh.entity.MemberEntity;
import iducs.springboot.boardpsh.entity.MemoEntity;
import iducs.springboot.boardpsh.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    final MemberRepository memberRepository;
    final HttpSession httpSession;

    @Override
    public int create(Member m) {
        memberRepository.save(new MemberEntity(m));
        return 1;
    }

    @Override
    public Member read(Member m) {
        return memberRepository
                .findById(m.getSeq())
                .map(Member::new)
                .orElse(null);
    }

    @Override
    public List<Member> readList() {
        List<MemberEntity> entities = memberRepository.findAll();
        List<Member> result = new ArrayList<>();
        for (MemberEntity member : entities)
            result.add(new Member(member));
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

    @Override
    public boolean isExist(String email, String pwd) {
        List<MemberEntity> entityList = memberRepository.findByEmailAndPw(email, pwd);
        boolean result = entityList.size() == 1;
        System.out.println(entityList.size());
        if (result) {
            httpSession.setAttribute("me", new Member(entityList.get(0)));
        }
        return result;
    }

    @Override
    public PageResultDTO<Member, MemberEntity> getList(PageRequestDTO requestDTO) {
        Pageable pageable = requestDTO.getPageable(Sort.by("seq"));
        Page<MemberEntity> result = memberRepository.findAll(pageable);
        return new PageResultDTO<>(result, Member::new, requestDTO.getPerPagination());
    }
}
