package ru.grobikon.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoInfoDto {

    /**
     * Идентификатор
     */
    @JsonProperty("id")
    private UUID id;

    /**
     * Описание
     */
    @JsonProperty("description")
    private String description;

    /**
     * Тип
     */
    @JsonProperty("contentType")
    private String contentType;

    /**
     * Адрес предварительного просмотра
     */
    @JsonProperty("previewUrl")
    private String previewUrl;

    /**
     * Идентификатор
     */
    @JsonProperty("streamUrl")
    private String streamUrl;
}
