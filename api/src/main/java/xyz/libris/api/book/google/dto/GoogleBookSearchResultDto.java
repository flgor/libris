package xyz.libris.api.book.google.dto;

import java.util.List;

public class GoogleBookSearchResultDto {

    private Integer totalItems;
    private List<GoogleBookDto> items;

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleBookDto> getItems() {
        return items;
    }

    public void setItems(List<GoogleBookDto> items) {
        this.items = items;
    }
}
