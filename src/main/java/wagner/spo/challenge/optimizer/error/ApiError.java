package wagner.spo.challenge.optimizer.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;
    private List<ApiSubError> subErrors;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(String message) {
        this();
        this.message = message;
    }

    public ApiError(String message, List<ApiSubError> subErrors) {
        this(message);
        this.subErrors = subErrors;
    }
}
