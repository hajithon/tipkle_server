package tipit.tipit.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NaverUserDto {
    private String id;
    private String nickname;

    @JsonProperty("response")
    private void unpackNested(Response response) {
        this.id = response.id;
        this.nickname = response.nickname;
    }

    private static class Response {
        public String id;
        public String nickname;

    }
}
