package com.sorawee.fileUpload.auth.repository;
import com.sorawee.fileUpload.auth.model.Role;
import com.sorawee.fileUpload.auth.model.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    public User findByEmail(String email){
        User user = new User();
        user.setId(1);
        user.setEmail(email);
        user.setRole(Role.ADMIN);
        user.setFirstName("File");
        user.setLastName("Upload");
        return user;
    }
}