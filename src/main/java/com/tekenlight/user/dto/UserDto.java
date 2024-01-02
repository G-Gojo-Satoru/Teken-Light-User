package com.tekenlight.user.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class UserDto {
    /** first name  */
    private String firstName;
    /** last name   */
    private String lastname;
    /** date of birth   */
    private Date dateOfBirth;
    /** address     */
    private String address;
    /** state   */
    private String state;
    /** pincode */
    private int pincode;
    /** phone number    */
    @Column(unique = true)
    private long phoneNumber;
    /** email */
    @Column(unique = true)
    @Email
    private String email;
}