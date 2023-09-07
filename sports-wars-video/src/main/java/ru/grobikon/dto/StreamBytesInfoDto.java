package ru.grobikon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StreamBytesInfoDto {

    private StreamingResponseBody responseBody;

    private long fileSize;

    private long rangeStart;

    private long rangeEnd;

    private String contentType;
}
