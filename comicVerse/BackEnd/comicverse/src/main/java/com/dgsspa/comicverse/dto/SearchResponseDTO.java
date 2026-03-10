package com.dgsspa.comicverse.dto;

import java.util.List;

public class SearchResponseDTO<T> {

    private List<T> items;
    private int total;
    private String message;

    public SearchResponseDTO() {
    }

    public SearchResponseDTO(List<T> items, int total, String message) {
        this.items = items;
        this.total = total;
        this.message = message;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
