package com.devsuperior.dsmeta.dto;

import java.time.Instant;

public record ErrorResponseDTO(Instant timestamp, Integer status, String error, String path) {
}
