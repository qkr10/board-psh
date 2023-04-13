package iducs.springboot.boardpsh.service;

import iducs.springboot.boardpsh.domain.Memo;
import java.util.List;

public interface MemoService {
    Memo create(Memo m);
    List<Memo> initialize();
    List<Memo> readList();
    Memo read(Memo m);
}
