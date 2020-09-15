package ch.stnikolauswohlen.santacloud.errorhandling;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class ApiErrorDTO
{
    private int status;
    private String message;
    private Map<String, Object> errors;

    public ApiErrorDTO(final HttpStatus status, final String message, final Map<String, Object> errors)
    {
        this.status = status.value();
        this.message = message;
        this.errors = errors;
    }

    public ApiErrorDTO(final HttpStatus status, final String message)
    {
        this.status = status.value();
        this.message = message;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(final HttpStatus status)
    {
        this.status = status.value();
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public Map<String, Object> getErrors()
    {
        return errors;
    }

    public void setErrors(final Map<String, Object> errors)
    {
        this.errors = errors;
    }

    public void addError(final String key, final String value)
    {
        if (errors == null) {
            errors = new HashMap<>();
        }

        errors.put(key, value);
    }
}
