package tipit.tipit.domain.user.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tipit.tipit.domain.user.repository.UserRepository;
import tipit.tipit.global.jwt.TokenService;
import tipit.tipit.domain.user.entity.User;
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final TokenService tokenService;

}

