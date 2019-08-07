package com.example.demo.controller;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public DatabaseLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role admin = new Role("ROLE_ADMIN");
        Role user = new Role("ROLE_USER");

        String  secretAdminUser="{bcrypt}"+bCryptPasswordEncoder.encode("kyaw");
        String  secretUser="{bcrypt}"+bCryptPasswordEncoder.encode("soe");

        User adminUser = new User("Kyaw","Soe",secretAdminUser,"kyaw@gmial.com");
        User userUser = new User("Soe","User",secretUser,"user@gmial.com");

        adminUser.addRole(admin);
        admin.getUsers().add(adminUser);

        userUser.addRole(user);
        user.getUsers().add(userUser);

//        roleRepository.save(admin);
//        roleRepository.save(user);
//
//        userRepository.save(adminUser);
//        userRepository.save(userUser);


    }
}
