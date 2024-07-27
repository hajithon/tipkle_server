package tipit.tipit.domain.user.dto;

import tipit.tipit.domain.user.entity.User;

public record GetUserInfo(
        String nickname
        ) {
    public static GetUserInfo of(User user) {
        return new GetUserInfo(
                user.getNickname()
        );
    }
}
