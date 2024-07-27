package tipit.tipit.global.oauth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import tipit.tipit.domain.user.entity.OAuthProvider;
import tipit.tipit.domain.user.entity.User;
import org.springframework.security.core.Authentication;
import tipit.tipit.domain.user.repository.UserRepository;
import tipit.tipit.domain.user.dto.NaverUserDto;
import org.apache.commons.lang3.tuple.Pair;
import tipit.tipit.global.jwt.SecurityService;
import tipit.tipit.global.jwt.TokenResponse;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverUserService {
    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final ObjectMapper objectMapper;
    @Value("${oauth.naver.client-id}")
    private String clientId;
    @Value("${oauth.naver.client-secret}")
    private String clientSecret;

    public Pair<TokenResponse, Boolean> naverLogin(String accessToken) throws JsonProcessingException {
        NaverUserDto naverUserInfo = getNaverUserInfo(accessToken);
        Pair<User, Boolean> naverUser = registerNaverUserIfNeed(naverUserInfo);
        Authentication authentication = securityService.forceLogin(naverUser.getLeft());
        return Pair.of(securityService.usersAuthorizationInput(authentication), naverUser.getRight());
    }

    private NaverUserDto getNaverUserInfo(String accessToken) throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);
        HttpEntity<MultiValueMap<String, String>> naverUserInfoRequest = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://openapi.naver.com/v1/nid/me",
                HttpMethod.GET,
                naverUserInfoRequest,
                String.class
        );
        return objectMapper.readValue(response.getBody(), NaverUserDto.class);
    }

    private Pair<User, Boolean> registerNaverUserIfNeed(NaverUserDto naverUserInfo) {
        String providerId = naverUserInfo.getId();
        User naverUser = userRepository.findByProviderId(providerId).orElse(null);

        if (naverUser == null) {
            // UUID를 사용하여 랜덤 패스워드 생성
            String password = UUID.randomUUID().toString();

            // User 엔티티를 생성하여 DB에 저장
            naverUser = User.builder()
                    .oAuthProvider(OAuthProvider.NAVER)
                    .nickname(naverUserInfo.getNickname())
                    .build();
            userRepository.save(naverUser);
            return Pair.of(naverUser, true);
        }
        return Pair.of(naverUser, false);
    }
}