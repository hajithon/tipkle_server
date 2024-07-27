package tipit.tipit.global.jwt;

import lombok.RequiredArgsConstructor;
import tipit.tipit.domain.user.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import tipit.tipit.domain.user.repository.UserRepository;
import tipit.tipit.global.error.error.exception.CustomException;

import static tipit.tipit.global.error.error.exception.ErrorCode.USER_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String providerId) {
        User user = userRepository.findByProviderId(providerId).orElseThrow(
                () -> new CustomException(USER_NOT_FOUND)
        );
        return new UserDetailsImpl(user);
    }
}