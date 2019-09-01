package wagner.spo.challenge.optimizer.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.validation.FieldError;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiSubError {

    private String field;
    private String message;

    public static ApiSubError fromFieldError(FieldError error) {
        return new ApiSubError(error.getField(), error.getDefaultMessage());
    }
}
