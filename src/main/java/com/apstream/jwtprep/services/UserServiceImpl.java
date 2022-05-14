package com.apstream.jwtprep.services;

import com.apstream.jwtprep.domain.*;
import com.apstream.jwtprep.repository.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Service @Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;
    private final ImageRepository imageRepository;

    private final CountryRepo countryRepo;

    private final StateRepo stateRepo;

    private final CitiRepo citiRepo;

    private AppUser user;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder, ImageRepository imageRepository, CountryRepo countryRepo, StateRepo stateRepo, CitiRepo citiRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
        this.imageRepository = imageRepository;
        this.countryRepo = countryRepo;
        this.stateRepo = stateRepo;
        this.citiRepo = citiRepo;
    }

    @Override
    public AppUser saveUser(AppUser user) {
      
        Role role = roleRepo.findByName("ROLE_USER");
        user.getRoles().add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("saving role {} to database", role.getName());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("adding role {} to user {}", roleName, username);
          user = userRepo.findByEmail(username);
         
    }

    @Override
    public AppUser getUser(String username) {
        log.info("getting user {} ", username);
        return userRepo.findByUsername(username);
    }

    @Override
    public List<AppUser> getUsers() {
        log.info("fetching all users in the database");
        return userRepo.findAll();
    }

    @Override
    public String checkUserByEmail(String email) {

         user = userRepo.findByEmail(email);
        if (user != null){
            return "exist";
        }else {
            return "no";
        }

    }

    @Override
    public String checkUserByUsername(String username) {

         user = userRepo.findByUsername(username);
        if (user != null){
            return "exist";
        } else {
            return "no";
        }
    }


    @Override
    public String saveImage(ImageUrls imageUrl){

        if (imageUrl != null){
            imageRepository.save(imageUrl);
            return "image saved";
        } else {
            return "error";
        }

    }

    @Override
    public Set<ImageUrls> getUserImages(String username) {
        user = userRepo.findByUsername(username);
        Set<ImageUrls> imageUrls = user.getImagesUrls();
        return imageUrls;
    }

    @Override
    public List<AppUser> getAllUsersInState(String state) {
        List<AppUser> usersByStates = userRepo.findAllByState(state);
        return usersByStates;
    }

    @Override
    public List<AppUser> getAllUsersInCity(String city) {

        List<AppUser> usersByCity = userRepo.findALlByCity(city);
        return usersByCity;
    }

    @Override
    public List<States> getAllStateInCountry(String countryId) {

        List<States> states = stateRepo.findByCountryId(countryId);
        return states;
    }

    @Override
    public List<Cities> getAllCitiesInState(String stateId) {
        List<Cities> cities  = citiRepo.findAllByStateId(stateId);
        return cities;
    }

    @Override
    public List<Countries> getAllCountries() {

        List<Countries> countries = countryRepo.findAll();
        return countries;
    }

    @Override
    public AppUserInfo getUserInfo() {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         user = userRepo.findByEmail(username);

        if (user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not fond in the database");
        } else {
            log.info("User found in the database: {}", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });

        return new User(
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }
}
