package com.tekenlight.user.service;

import com.tekenlight.user.dto.AddressDto;
import com.tekenlight.user.dto.UserDto;
import com.tekenlight.user.entity.User;
import com.tekenlight.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServeice {

    private final UserRepository repository;

    public String saveUserDetails(UserDto userdto) {
        String respose;
        if (repository.findByEmail(userdto.getEmail()) == null) {
            int SERIALNUMBER = repository.getMaxSerialNumber() + 1;
            User user = User.builder()
                    .firstName(userdto.getFirstName())
                    .lastname(userdto.getLastname())
                    .dateOfBirth(userdto.getDateOfBirth())
                    .address(userdto.getAddress())
                    .state(userdto.getState())
                    .pincode(userdto.getPincode())
                    .phoneNumber(userdto.getPhoneNumber())
                    .email(userdto.getEmail())
                    .serialNo(SERIALNUMBER).build();
            repository.save(user);
            respose = "saved";
        } else respose = "PRESENT";
        return respose;

    }

    public UserDto findByPhoneNumber(long phone) {
        UserDto userDto=new UserDto();
        if (repository.findByPhoneNumber(phone)!=null) {
            User user = repository.findByPhoneNumber(phone);
            userDto = UserDto.builder()
                    .firstName(user.getFirstName())
                    .lastname(user.getLastname())
                    .dateOfBirth(user.getDateOfBirth())
                    .address(user.getAddress())
                    .state(user.getState())
                    .pincode(user.getPincode())
                    .phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail())
                    .build();
            return userDto;
        }else {
            userDto=null;
        }
        return userDto;
    }

    public UserDto getByEmail(String email) {
        if (repository.findByEmail(email) != null) {
            User user = repository.findByEmail(email);
            UserDto userDto = UserDto.builder()
                    .firstName(user.getFirstName())
                    .lastname(user.getLastname())
                    .dateOfBirth(user.getDateOfBirth())
                    .address(user.getAddress())
                    .state(user.getState())
                    .pincode(user.getPincode())
                    .phoneNumber(user.getPhoneNumber())
                    .email(user.getEmail())
                    .build();
            return userDto;
        }
        return null;
    }

    public String updateAddressOfUser(String email, AddressDto addressDto) {
        String respose = null;
        if (repository.findByEmail(email) != null) {
            String address=(addressDto.getAddress());
            int pincode=(addressDto.getPincode());
            String state=(addressDto.getState());
            repository.updateRecord(address,pincode,state,email);
            respose = "update";
        } else respose = "NOT PRESENT";
        return respose;
    }

    public String deleteRecordByEmail(String email) {
        String respose = null;
        if (repository.findByEmail(email) != null) {
            int SERIALNUMBER = repository.findSerialNumberByEmail(email);
            repository.deleteById(SERIALNUMBER);
            respose="deleted";
        }else respose="NOT FOUND";
        return respose;
    }

}
