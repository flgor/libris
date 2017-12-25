package xyz.libris.api.google.book.dto;

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
