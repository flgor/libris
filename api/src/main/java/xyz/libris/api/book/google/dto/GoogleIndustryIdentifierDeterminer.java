package xyz.libris.api.book.google.dto;

import xyz.libris.api.book.google.client.data.GoogleIndustryIdentifier;

import java.util.List;
import java.util.Optional;

public class GoogleIndustryIdentifierDeterminer {

    private final List<GoogleIndustryIdentifier> industryIdentifiers;

    public GoogleIndustryIdentifierDeterminer(List<GoogleIndustryIdentifier> industryIdentifiers) {
        this.industryIdentifiers = industryIdentifiers;
    }

    public String determinIsbn10() {
        return getIsbn("ISBN_10");
    }

    public String determinIsbn13() {
        return getIsbn("ISBN_13");
    }

    private String getIsbn(String identifierType) {

        Optional<GoogleIndustryIdentifier> optionalIsbn = industryIdentifiers
                .stream()
                .filter(identifier -> identifier.getType().equals(identifierType))
                .findAny();

        if (optionalIsbn.isPresent()) {
            return optionalIsbn.get().getIdentifier();
        }

        return "";
    }
}
