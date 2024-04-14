package org.example.technologie_sieciowe_1.controllers.dto.get;
import java.util.List;
public class GetLoansPageDto {
    private List<GetLoanDto> loans;
    private int currentPage;
    private long totalItem;
    private int totalPages;
    private boolean hasMore;

    public GetLoansPageDto(List<GetLoanDto> loans, int currentPage, long totalItem, int totalPages, boolean hasMore) {
        this.loans = loans;
        this.currentPage = currentPage;
        this.totalItem = totalItem;
        this.totalPages = totalPages;
        this.hasMore = hasMore;
    }

    public List<GetLoanDto> getLoans() {
        return loans;
    }

    public void setLoans(List<GetLoanDto> loans) {
        this.loans = loans;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(long totalItem) {
        this.totalItem = totalItem;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isHasMore() {
        return hasMore;
    }

    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }
}
