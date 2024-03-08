package com.craftbay.crafts.dto.report;

import lombok.*;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileExportResponseDto {
    private String fileName;
    private MediaType mediaType;
    private InputStreamResource resource;
}