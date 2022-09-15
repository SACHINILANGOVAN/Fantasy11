package com.nseit.Fantasy11.dataloader;

import com.nseit.Fantasy11.model.FantasyUser;
import com.nseit.Fantasy11.model.Role;
import com.nseit.Fantasy11.repository.FantasyUserRepository;
import com.nseit.Fantasy11.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private FantasyUserRepository fantasyuserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

//         Create user roles
        Role userRole = createRoleIfNotFound(new String(Role.ROLE_USER));
        Role adminRole = createRoleIfNotFound(new String(Role.ROLE_ADMIN));

        // Create users
        createUserIfNotFound("admin", adminRole);

        alreadySetup = true;
    }

    @Transactional
    private Role createRoleIfNotFound(final String userName) {
        Role role = roleRepository.findByName(userName);
        if (role == null) {
            role = new Role();
            role.setName(String.valueOf(userName));
            role = roleRepository.save(role);
        }
        return role;
    }

    @Transactional
    private FantasyUser createUserIfNotFound(final String name, Role role) {
        FantasyUser user = fantasyuserRepository.findByUserName(name);
        if (user == null) {
            user = new FantasyUser(name, bCryptPasswordEncoder.encode("admin"));
            user.setRoles(Set.of(role));
            user = fantasyuserRepository.save(user);
        }
        return user;
    }
}
