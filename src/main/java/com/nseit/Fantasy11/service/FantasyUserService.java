package com.nseit.Fantasy11.service;

import com.nseit.Fantasy11.controller.ResourceNotFoundException;
import com.nseit.Fantasy11.exception.ResourceAlreadyExistException;
import com.nseit.Fantasy11.model.FantasyUser;
import com.nseit.Fantasy11.model.Role;
import com.nseit.Fantasy11.repository.FantasyUserRepository;
import com.nseit.Fantasy11.repository.MatchRepository;
import com.nseit.Fantasy11.repository.RoleRepository;
import com.nseit.Fantasy11.repository.SelectedPlayersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class FantasyUserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FantasyUserRepository fantasyUserRepository;

//    @Autowired
//    private MatchRepository matchRepository;

//    @Autowired
//    private SelectedPlayerRepository selectedPlayerRepository;

    @Autowired
    private RoleRepository roleRepository;


    public FantasyUser registerAsCustomer(FantasyUser fantasyUser) {
        FantasyUser user = fantasyUserRepository.findByUserName(fantasyUser.getUserName());
        if (user != null) {
            throw new ResourceAlreadyExistException("User Already Exception");
        }
        Role role = roleRepository.findByName(Role.USER);
        fantasyUser.setRoles(Set.of(role));
        fantasyUser.setPassword(bCryptPasswordEncoder.encode(fantasyUser.getPassword()));
        fantasyUser = fantasyUserRepository.save(fantasyUser);
        return fantasyUser;
    }

    public FantasyUser loginAsCustomer(FantasyUser fantasyUser) {
        FantasyUser user = fantasyUserRepository.findByUserName(fantasyUser.getUserName());
        if (user != null && bCryptPasswordEncoder.matches(fantasyUser.getPassword(), user.getPassword())) {
            return user;
        } else {
            throw new ResourceNotFoundException("Invalid username or password.");
        }
    }

}



