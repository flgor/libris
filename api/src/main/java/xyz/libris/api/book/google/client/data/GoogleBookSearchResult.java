package xyz.libris.api.book.google.client.data;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class GoogleBookSearchResult {

    private String kind;
    private Integer totalItems = 0;
    private List<GoogleBook> items = ImmutableList.of();

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public List<GoogleBook> getItems() {
        return items;
    }

    public void setItems(List<GoogleBook> items) {
        this.items = items;
    }
}
