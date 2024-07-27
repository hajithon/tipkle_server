package tipit.tipit.domain.user.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.web.bind.annotation.*;
import tipit.tipit.domain.user.service.UserService;
import tipit.tipit.global.jwt.TokenResponse;
import tipit.tipit.global.oauth.NaverUserService;
import tipit.tipit.global.response.SuccessResponse;
import tipit.tipit.domain.user.dto.GetUserInfo;
import tipit.tipit.domain.user.entity.User;
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final NaverUserService naverUserService;

    @GetMapping("/oauth/naver")
    public SuccessResponse<TokenResponse> naverLogin(@RequestParam(name = "accessToken", required = true) String accessToken) throws JsonProcessingException {
        Pair<TokenResponse, Boolean> pair = naverUserService.naverLogin(accessToken);
        if (pair.getRight()) {
            return SuccessResponse.createSuccess(pair.getLeft());
        } else {
            return SuccessResponse.success(pair.getLeft());
        }
    }

    @GetMapping("/userInfo")
    public SuccessResponse<GetUserInfo> getUserInfo(@RequestParam Long userId) {
        User user = userService.getUserById(userId);
        return SuccessResponse.success(GetUserInfo.of(user));
    }


}
