package tipit.tipit.global.response;


public record SuccessResponse<T> (
        int statusCode,
        String message, T result) {

    public static <T> SuccessResponse<T> success(T result) {
        return new SuccessResponse<>(200, "요청에 성공하였습니다.", result);
    }

    public static <T> SuccessResponse<T> createSuccess(T result) {
        return new SuccessResponse<>(201, "생성에 성공하였습니다.", result);
    }

}