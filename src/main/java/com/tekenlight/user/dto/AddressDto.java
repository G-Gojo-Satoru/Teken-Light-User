package com.tekenlight.user.dto;

import lombok.*;

/**
 * address dto
 */
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class AddressDto {
    /** address     */
    private String address;
    /** state   */
    private String state;
    /** pincode */
    private int pincode;
}
