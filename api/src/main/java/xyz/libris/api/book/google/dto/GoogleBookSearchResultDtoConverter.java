package xyz.libris.api.book.google.dto;

import xyz.libris.api.book.google.client.data.GoogleBook;
import xyz.libris.api.book.google.client.data.GoogleBookSearchResult;
import xyz.libris.api.book.google.client.data.GoogleImageLink;
import xyz.libris.api.book.google.client.data.GoogleVolumeInfo;

import java.util.stream.Collectors;

public class GoogleBookSearchResultDtoConverter {

    private final GoogleBookSearchResult searchResult;


    public GoogleBookSearchResultDtoConverter(GoogleBookSearchResult searchResult) {
        this.searchResult = searchResult;
    }


    public GoogleBookSearchResultDto convert() {
        GoogleBookSearchResultDto dto = new GoogleBookSearchResultDto();

        dto.setTotalItems(searchResult.getTotalItems());
        dto.setItems(
                searchResult.getItems()
                        .stream()
                        .map(this::toDto)
                        .collect(Collectors.toList())
        );

        return dto;
    }

    private GoogleBookDto toDto(GoogleBook googleBook) {
        GoogleBookDto dto = new GoogleBookDto();

        GoogleVolumeInfo volumeInfo = googleBook.getVolumeInfo();

        dto.setGoogleId(googleBook.getGoogleId());
        dto.setTitle(volumeInfo.getTitle());

        dto.setAuthors(new GoogleAuthorsTransformers(volumeInfo.getAuthors())
                .transform());

        dto.setPublisher(volumeInfo.getPublisher());
        dto.setPublishedDate(volumeInfo.getPublishedDate());
        dto.setDescription(volumeInfo.getDescription());

        GoogleIndustryIdentifierDeterminer identifierDeterminer = new GoogleIndustryIdentifierDeterminer(volumeInfo.getIndustryIdentifiers());
        dto.setIsbn10(identifierDeterminer.determinIsbn10());
        dto.setIsbn13(identifierDeterminer.determinIsbn13());

        GoogleImageLink imageLinks = volumeInfo.getImageLinks();
        if (imageLinks != null) {
            dto.setSmallThumbnail(imageLinks.getSmallThumbnail());
            dto.setThumbnail(imageLinks.getThumbnail());
        }


        return dto;
    }
}
