package com.example.berzkoder02.services;

import com.example.berzkoder02.models.entities.AppUser;
import com.example.berzkoder02.models.repos.AppUserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AppUserService implements UserDetailsService {

    @Autowired
    private AppUserRepo appUserRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepo.findAppUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Could not find '%s' user", email)));
    }

    public AppUser registerAppUser(AppUser user) {
        boolean userExists = appUserRepo.findAppUserByEmail(user.getEmail()).isPresent();
        if (userExists) {
            throw new RuntimeException(String.format("User '%s' already exists", user.getEmail()));
        }
        String passwordEncoded = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(passwordEncoded);
        return appUserRepo.save(user);
    }
}
