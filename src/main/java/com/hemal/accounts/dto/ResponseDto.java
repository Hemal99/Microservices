package com.hemal.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(
        name = "Response",
        description = "Response details"
)
@Data@AllArgsConstructor
public class ResponseDto {

        @Schema(
                description = "Status Code"
        )
        private String statusCode;

        @Schema(
                description = "Status Message"
        )
        private String statusMsg;


}
