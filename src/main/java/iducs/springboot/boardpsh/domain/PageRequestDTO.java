package iducs.springboot.boardpsh.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Map;
import java.util.function.Function;

@Builder
@AllArgsConstructor
@Data
public class PageRequestDTO {
    private int page;
    private int size;
    private int perPagination;
    private String type;
    private String keyword;
    private Function<Map<String, Integer>, String> getPageLink;

    public PageRequestDTO() {
        this.page = 1;
        this.size = 8;
        this.perPagination = 5;
        type = keyword = "";
        getPageLink = this::getPageLinkDefault;
    }

    public PageRequestDTO(int page, int size, int perPagination, String type, String keyword) {
        this.page = page;
        this.size = size;
        this.perPagination = perPagination;
        this.type = type;
        this.keyword = keyword;
        getPageLink = this::getPageLinkDefault;
    }

    private String getPageLinkDefault(Map<String, Integer> map) {
        return "/" + map.get("page") + "/" + map.get("perPage") + "/" + map.get("perPagination");
    }

    public Pageable getPageable(Sort sort) {
        return PageRequest.of(page - 1, size, sort);
    }
    public Pageable getPageable() {
        return PageRequest.of(page - 1, size);
    }
}
