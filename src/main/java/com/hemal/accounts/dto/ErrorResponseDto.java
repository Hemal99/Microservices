package com.hemal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(
        name = "ErrorResponse",
        description = "Error Response details"
)
@Data
@AllArgsConstructor
public class ErrorResponseDto {

    @Schema(
            description = "API Path" , example = "/api/v1/accounts"
    )
    private String apiPath;

    @Schema(
            description = "Error Code"
    )
    private HttpStatus errorCode;

    @Schema(
            description = "Error Message"
    )
    private String errorMessage;

    @Schema(
            description = "Error Time"
    )
    private LocalDateTime errorTime;

}
