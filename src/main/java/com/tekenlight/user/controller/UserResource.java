package com.tekenlight.user.controller;

import com.tekenlight.user.dto.AddressDto;
import com.tekenlight.user.dto.Response;
import com.tekenlight.user.dto.UserDto;
import com.tekenlight.user.service.UserServeice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * user resource
 */
@RestController
@RequestMapping(value = "/user/")
@Slf4j
public class UserResource {
    /**
     * user service class instance
     */
    @Autowired
    private UserServeice service;

    /**
     * save user details
     *
     * @param userdto UserDto
     * @return response Response
     */
    @PostMapping(value = "save")
    public ResponseEntity<Response> saveUserDetails(@RequestBody UserDto userdto) {
        String serviceResponse = service.saveUserDetails(userdto);
        Response response;
        if (serviceResponse.equalsIgnoreCase("saved")) {
            response = new Response(Boolean.TRUE, HttpStatus.CREATED, "saved successfully");
            log.info("DATA {} ", serviceResponse);
            return ResponseEntity.ok(response);
        } else {
            response = new Response(Boolean.FALSE, HttpStatus.CONFLICT, "already exists");
            log.error("FAILED");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    /**
     * get by phone number
     *
     * @param phone Phone
     * @return response Response
     */
    @GetMapping(value = "getByPhone/{phone}")
    public ResponseEntity<Response> getByPhoneNumber(@PathVariable long phone) {
        UserDto userDto = service.findByPhoneNumber(phone);
        Response response;
        if (userDto != null) {
            response = new Response(Boolean.TRUE, HttpStatus.FOUND, userDto);
            log.info("DATA {} ", userDto);
            return ResponseEntity.ok(response);
        } else {
            response = new Response(Boolean.FALSE, HttpStatus.NOT_FOUND, "WRONG");
            log.error("FAILED");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * get by email
     *
     * @param email Email
     * @return response Response
     */
    @GetMapping(value = "getByEmail/{email}")
    public ResponseEntity<Response> getByEmail(@PathVariable String email) {
        UserDto userDto = service.getByEmail(email);
        Response response;
        if (userDto != null) {
            response = new Response(Boolean.TRUE, HttpStatus.FOUND, userDto);
            log.info("DATA {}", userDto);
            return ResponseEntity.ok(response);
        } else {
            response = new Response(Boolean.FALSE, HttpStatus.NOT_FOUND, "WRONG");
            log.error("FAILED");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    // I am taking address, state, and pincode for update api
    // not whole record

    /**
     * update address of user by email
     *
     * @param addressDto AddressDto
     * @return response Response
     */
    @PutMapping(value = "updateRecord")
    public ResponseEntity<Response> updateAddressOfUser(@RequestBody AddressDto addressDto, @RequestParam String email) {
        String serviceResponse = service.updateAddressOfUser(email, addressDto);
        Response response;
        if (serviceResponse.equalsIgnoreCase("update")) {
            response = new Response(Boolean.TRUE, HttpStatus.FOUND, "UPDATED");
            log.info("DATA {}", serviceResponse);
            return ResponseEntity.ok(response);
        } else {
            response = new Response(Boolean.FALSE, HttpStatus.NOT_FOUND, "WRONG");
            log.error("FAILED");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    //I am taking delete using unique email only

    /**
     * delete record by email
     *
     * @param email Email
     * @return response Response
     */
    @DeleteMapping(value = "deleteRecord/{email}")
    public ResponseEntity<Response> deleteRecordByEmail(@PathVariable String email) {
        String serviceResponse = service.deleteRecordByEmail(email);
        Response response;
        if (serviceResponse.equalsIgnoreCase("deleted")) {
            response = new Response(Boolean.TRUE, HttpStatus.FOUND, "deleted successfully");
            log.info("DATA {}", serviceResponse);
            return ResponseEntity.ok(response);
        } else {
            response = new Response(Boolean.FALSE, HttpStatus.NOT_FOUND, "NOT EXISTS");
            log.error("FAILED");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }


}