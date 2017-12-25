package xyz.libris.api.google.books.data;

import java.util.List;

public class GoogleBookSearchResponse {

    private String kind;
    private Integer totalItems;
    private List<GoogleBook> items;

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
