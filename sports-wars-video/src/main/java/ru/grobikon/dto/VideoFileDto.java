package ru.grobikon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoFileDto {

    /**
     * Описание к видео
     */
    @JsonProperty("description")
    private String description;

    /**
     * Файл с видео
     */
    @JsonProperty("file")
    private MultipartFile file;
}
