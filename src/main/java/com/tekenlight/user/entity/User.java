package com.tekenlight.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import java.sql.Date;

/**
 * user entity class
 */
@Entity
@Table(name="user")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Getter
@Setter
public class User {
    /** serial number   */
    @Id
    private int serialNo;
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