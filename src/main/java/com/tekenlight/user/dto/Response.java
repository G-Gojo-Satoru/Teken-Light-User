package com.tekenlight.user.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class Response {
    /** is valid    */
    private Boolean isValid;
    /** message */
    private HttpStatus httpStatus;
    /** data    */
    private Object data;
}