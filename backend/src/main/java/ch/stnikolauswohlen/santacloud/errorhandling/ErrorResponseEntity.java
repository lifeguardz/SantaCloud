package ch.stnikolauswohlen.santacloud.errorhandling;

import java.util.HashMap;
import java.util.Map;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

public class ErrorResponseEntity extends ResponseEntity<Object>
{
    private ErrorResponseEntity(@NonNull final ApiErrorDTO body, @NonNull final HttpStatus status)
    {
        super(body, status);
    }

    public static ErrorResponseEntityBuilder builder()
    {
        return new ErrorResponseEntityBuilder();
    }

    public static class ErrorResponseEntityBuilder
    {
        private HttpStatus status;
        private String message;
        private Map<String, Object> errors = new HashMap<>();

        private ErrorResponseEntityBuilder()
        {
        }

        public ErrorResponseEntityBuilder status(@NonNull final HttpStatus status)
        {
            this.status = status;
            return this;
        }

        public ErrorResponseEntityBuilder message(@NonNull final String message)
        {
            this.message = message;
            return this;
        }

        public ErrorResponseEntityBuilder error(@NonNull final String key, @NonNull final String value)
        {
            this.errors.put(key, value);
            return this;
        }

        public ErrorResponseEntityBuilder errors(@NonNull final Map<String, Object> errors)
        {
            this.errors = errors;
            return this;
        }

        public ErrorResponseEntityBuilder clearErrors()
        {
            this.errors = new HashMap<>();
            return this;
        }

        public ErrorResponseEntity build()
        {
            if (StringUtils.isEmpty(message)) {
                message = status.getReasonPhrase();
            }

            final ApiErrorDTO apiErrorDTO = new ApiErrorDTO(status, message, errors);
            return new ErrorResponseEntity(apiErrorDTO, status);
        }
    }
}
