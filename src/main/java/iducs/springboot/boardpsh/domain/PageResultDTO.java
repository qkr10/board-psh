package iducs.springboot.boardpsh.domain;

import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private String prevLink, nextLink;

    private List<Map<String, Object>> pageList;

    public PageResultDTO(Page<EN> result, Function<EN, DTO> fn, PageRequestDTO pageRequestDTO) {
        this.perPagination = pageRequestDTO.getPerPagination();
        this.totalRows = (int) result.getTotalElements();
        dtoList = result.stream().map(fn).collect(Collectors.toList());
        totalPage = result.getTotalPages();
        makePageList(result.getPageable(), pageRequestDTO.getGetPageLink());
    }

    private void makePageList(Pageable pageable, Function<Map<String, Integer>, String> getPageLink) {
        this.curPage = pageable.getPageNumber() + 1;

        this.size = pageable.getPageSize();
        int tempEnd = (int)(Math.ceil(curPage / ((double) perPagination))) * perPagination;

        start = tempEnd - perPagination + 1;
        end = Math.min(totalPage, tempEnd);

        prev = start > 1;
        next = totalPage > tempEnd;

        Map<String, Integer> page = new HashMap<>();
        page.put("perPage", size);
        page.put("perPagination", perPagination);
        pageList = IntStream.rangeClosed(start, end)
                .mapToObj(i -> {
                    page.put("page", i);

                    Map<String, Object> res = new HashMap<>();
                    res.put("page", ""+i);
                    res.put("pageLink", getPageLink.apply(page));
                    return res;
                })
                .toList();

        page.put("page", start-1);
        prevLink = getPageLink.apply(page);

        page.put("page", end+1);
        prevLink = getPageLink.apply(page);
    }
}
