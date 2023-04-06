package iducs.springboot.boardthymeleaf.service;

import iducs.springboot.boardthymeleaf.domain.Memo;
import iducs.springboot.boardthymeleaf.entity.MemoEntity;
import iducs.springboot.boardthymeleaf.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service(value = "Impl")
public class MemoServiceImpl implements MemoService {

    @Autowired
    MemoRepository memoRepository;

    @Override
    public Memo create(Memo m) {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("sample" + i).build();
            memoRepository.save(memo);
        });
        return null;
    }

    @Override
    public List<Memo> initialize() {
        IntStream.rangeClosed(1, 10).forEach(i -> {
            MemoEntity memo = MemoEntity.builder().memoText("egyou" + i).build();
            memoRepository.save(memo);
        });

        return readList();
    }

    @Override
    public List<Memo> readList() {
        List<MemoEntity> entities = memoRepository.findAll();
        List<Memo> result = new ArrayList<>();
        for (MemoEntity memo : entities) {
            Memo m = new Memo();
            m.setMno(memo.getMno());
            m.setMemoText(memo.getMemoText());
            result.add(m);
        }
        return result;
    }

    @Override
    public Memo read(Memo m) {
        MemoEntity memoEntity = memoRepository.getById(m.getMno());
        Memo result = new Memo();
        result.setMno(memoEntity.getMno());
        result.setMemoText(memoEntity.getMemoText());
        return result;
    }
}
