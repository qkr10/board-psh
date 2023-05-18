package iducs.springboot.boardpsh.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResultDTO<DTO, EN> {
    private List<DTO> dtoList;

    private int totalPage;
    private int curPage;
    private int size;

    private int perPagination;

    private int totalRows;

    private int start, end;
    private boolean prev, next;

    private List<Integer> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn, int perPagination) {
        this.perPagination = perPagination;
        this.totalRows = (int) result.getTotalElements();
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable());
    }

    private void makePageList(Pageable pageable) {
        this.curPage = pageable.getPageNumber() + 1;

        this.size = pageable.getPageSize();
        int tempEnd = (int)(Math.ceil(curPage / ((double) perPagination))) * perPagination;

        start = tempEnd - perPagination + 1;
        end = Math.min(totalPage, tempEnd);

        prev = start > 1;
        next = totalPage > tempEnd;

        pageList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());
    }
}
