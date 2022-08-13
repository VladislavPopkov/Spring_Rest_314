package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.repository.RoleRepository;
import web.repository.UserRepository;
import web.model.Role;
import web.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class Init {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Autowired
    public Init(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        Role roleUser = new Role("USER");
        Role roleAdmin = new Role("ADMIN");
        Set<Role> userRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();

        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);


        userRoles.add(roleUser);
        adminRoles.add(roleAdmin);
        adminRoles.add(roleUser);

        User user = new User("User", "User", 22,  "user@mail.ru", "$2a$12$JOGv6KWPsSj/BD.Ta57jvOFsZgdG1Ry2ocBA3VmDB5Diy5eqKbQui", userRoles); //user
        User admin = new User("Admin", "Admin", 21,  "admin@mail.ru", "$2a$12$GW0AKxFcVByNopzjj3cHz.7ztnFXi59eh90ZtKpobkV1t/NquSlVq", adminRoles); //само собой admin


        userRepository.save(user);
        userRepository.save(admin);

    }

}
