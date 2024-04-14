package org.example.technologie_sieciowe_1.controllers.dto.respone;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDetailsDto;
import org.example.technologie_sieciowe_1.controllers.dto.get.GetBookDto;
import org.example.technologie_sieciowe_1.infrastructure.entity.BookEntity;

public class CreateBookDetailsResponseDto {
    @Schema(description = "The ID of the book details", example = "32")
    private Integer id;
    @Schema(description = "The genre of the book", example = "fantasy")
    private String genre;
    @Schema(description = "A summary of the book", example =  "Worlds collide in this captivating novel that blends elements of fantasy with science fiction. ")
    private String summary;
    @Schema(description = "The URL of the cover image of the book")
    private  String CoverImageURL;
    private Integer book; // change to GetBookDto
    public CreateBookDetailsResponseDto( String genre, String summary, String coverImageURL, Integer book) {
        this.genre = genre;
        this.summary = summary;
        this.CoverImageURL = coverImageURL;
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCoverImageURL() {
        return CoverImageURL;
    }

    public void setCoverImageURL(String coverImageURL) {
        CoverImageURL = coverImageURL;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }
}
