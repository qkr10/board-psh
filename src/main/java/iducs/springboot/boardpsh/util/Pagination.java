package iducs.springboot.boardpsh.util;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pagination {
    private int totalRows;
    private int perPage;
    private int perPagination;
    private int firstRow;
    private int endRow;

    private int totalPages;
    private int curPageNo;
    private int beginPageNo;
    private int endPageNo;

    public Pagination(int curPageNo, int perPage, int perPagination, int totalRows) {
        firstRow = (curPageNo - 1) * perPage * 1;
        endRow = firstRow + perPage - 1;

        totalPages = totalRows / perPage;

        if ((totalRows % perPage) > 0)
            totalPages++;

        beginPageNo = 0;
        endPageNo = 0;

        if (totalPages > 0) {
            beginPageNo = (curPageNo - 1) / perPagination * perPagination + 1;
            endPageNo = beginPageNo + perPagination - 1;
            if (endPageNo > totalPages)
                endPageNo = totalPages;
        }
        setPerPagination(perPage);
        setBeginPageNo(beginPageNo);
        setEndPageNo(endPageNo);
        setCurPageNo(curPageNo);
        setTotalPages(totalPages);
        setPerPagination(perPagination);
    }
}
