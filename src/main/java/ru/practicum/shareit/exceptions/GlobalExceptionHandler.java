package ru.practicum.shareit.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ValidationException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "requested object not found")
    Exception handleNoSuchElementException(final Exception e) {
        return null;
    }

    @ExceptionHandler(NotValidOwnerException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "not valid owner")
    Exception handleNotValidOwnerException(final Exception e) {
        return null;
    }

    @ExceptionHandler(ItemNotAvailableException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "item not available")
    Exception handleItemNotAvailableException(final Exception e) {
        return null;
    }

    @ExceptionHandler({ValidationException.class, HasNotBookingsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad request")
    Exception handleValidationException(final Exception e) {
        return null;
    }
}
