package xyz.libris.api.google.book.finder.data;

import com.google.common.collect.ImmutableList;

import java.util.List;

public class GoogleVolumeInfo {

    private String title;
    private List<String> authors = ImmutableList.of();
    private String publisher;
    private String publishedDate;
    private String description;
    private List<GoogleIndustryIdentifier> industryIdentifiers = ImmutableList.of();
    private GoogleImageLink imageLinks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<GoogleIndustryIdentifier> getIndustryIdentifiers() {
        return industryIdentifiers;
    }

    public void setIndustryIdentifiers(List<GoogleIndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public GoogleImageLink getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(GoogleImageLink imageLinks) {
        this.imageLinks = imageLinks;
    }
}
