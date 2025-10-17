package org.fve.springbootprojects.springbootstarterapp.security.jwt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.fve.springbootprojects.springbootstarterapp.security.dtos.LoginRequestDto;
import org.fve.springbootprojects.springbootstarterapp.security.dtos.RegisterRequestDto;
import org.fve.springbootprojects.springbootstarterapp.security.models.Permission;
import org.fve.springbootprojects.springbootstarterapp.security.models.Role;
import org.fve.springbootprojects.springbootstarterapp.security.models.User;
import org.fve.springbootprojects.springbootstarterapp.security.repositories.PermissionRepository;
import org.fve.springbootprojects.springbootstarterapp.security.repositories.RoleRepository;
import org.fve.springbootprojects.springbootstarterapp.security.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class JwtAuthServiceImpl implements JwtAuthService {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostConstruct
    public void saveRolesInDb() {
        Role userRole = roleRepository.findByName("ROLE_USER");
        if (userRole == null) {
            Role role = new Role();
            role.setName("ROLE_USER");
            roleRepository.save(role);
        }
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }

        Permission permission1 = permissionRepository.findByName("CAN:WRITE");
        if (permission1 == null) {
            Permission permission = new Permission();
            permission.setName("CAN:WRITE");
            permissionRepository.save(permission);
        }


    }


    @Override
    public String register(RegisterRequestDto registerDto) {
        // check username is already exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {
//            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
//            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Email already exists");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        //first save, then assign role entity, then save again for the linking in DB
        userRepository.save(user);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER");
        roles.add(userRole);

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public JwtAuthResponse login(LoginRequestDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        Optional<User> userOptional = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(),
                loginDto.getUsernameOrEmail());

        String role = null;

        if (userOptional.isPresent()) {
            User loggedInUser = userOptional.get();
            Optional<Role> optionalRole = loggedInUser.getRoles().stream().findFirst();

            if (optionalRole.isPresent()) {
                Role userRole = optionalRole.get();
                role = userRole.getName();
            }
        }

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setRole(role);
        jwtAuthResponse.setAccessToken(token);

        return jwtAuthResponse;
    }
}
