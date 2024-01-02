package com.tekenlight.user.repository;

import com.tekenlight.user.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * user repository interface
 */
@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Integer> {
    /** get max serial number   */
    @Query(value = "SELECT MAX(serial_no) FROM user",nativeQuery = true)
    int getMaxSerialNumber();


    /** find by email query     */
    @Query(value="SELECT * FROM user WHERE email=:email", nativeQuery = true)
    User findByEmail(String email);

    /** find by email query     */
    @Query(value="SELECT * FROM user WHERE phone_number =:phone_number", nativeQuery = true)
    User findByPhoneNumber(long phone_number);

    /** find serial number by email query     */
    @Query(value="SELECT serial_no FROM user WHERE email =:email", nativeQuery = true)
    int findSerialNumberByEmail(String email);

//    @Query(value = "UPDATE user SET state =:state, address =:address, pincode =:pincode WHERE email =:email ",nativeQuery = true)
//    void updateRecord(String address,int pincode,String state, String email);

    @Modifying
    @Query(value = "UPDATE user SET state = :state, address = :address, pincode = :pincode WHERE email = :email", nativeQuery = true)
    void updateRecord(@Param("address") String address, @Param("pincode") int pincode, @Param("state") String state, @Param("email") String email);


}