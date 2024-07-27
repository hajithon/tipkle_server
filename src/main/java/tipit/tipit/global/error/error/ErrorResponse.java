package tipit.tipit.global.error.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tipit.tipit.global.error.error.exception.ErrorCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ErrorResponse {

    private int statusCode;
    private String code;
    private String message;

    public ErrorResponse(final ErrorCode errorCode) {
        this.statusCode = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}
