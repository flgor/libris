package xyz.libris.api.google.book.dto;

import xyz.libris.api.google.book.finder.data.*;

import java.util.Optional;
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

        dto.setTitle(volumeInfo.getTitle());
        dto.setAuthors(volumeInfo.getAuthors().stream().collect(Collectors.joining(",")));
        dto.setPublisher(volumeInfo.getPublisher());
        dto.setPublishedDate(volumeInfo.getPublishedDate());
        dto.setDescription(volumeInfo.getDescription());
        dto.setIsbn10(getIsbn10(volumeInfo));
        dto.setIsbn13(getIsbn13(volumeInfo));

        GoogleImageLink imageLinks = volumeInfo.getImageLinks();
        if (imageLinks != null) {
            dto.setSmallThumbnail(imageLinks.getSmallThumbnail());
            dto.setThumbnail(imageLinks.getThumbnail());
        }


        return dto;
    }

    private String getIsbn10(GoogleVolumeInfo volumeInfo) {
        return getIsbn(volumeInfo, "ISBN_10");
    }

    private String getIsbn13(GoogleVolumeInfo volumeInfo) {
        return getIsbn(volumeInfo, "ISBN_13");
    }

    private String getIsbn(GoogleVolumeInfo volumeInfo, String identifierType) {

        Optional<GoogleIndustryIdentifier> optionalIsbn = volumeInfo.getIndustryIdentifiers()
                .stream()
                .filter(identifier -> identifier.getType().equals(identifierType))
                .findAny();

        if (optionalIsbn.isPresent()) {
            return optionalIsbn.get().getIdentifier();
        }

        return "";
    }

}
