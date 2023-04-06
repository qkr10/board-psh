package iducs.springboot.boardthymeleaf.service;

import iducs.springboot.boardthymeleaf.domain.Memo;
import java.util.List;

public interface MemoService {
    Memo create(Memo m);
    List<Memo> initialize();
}
