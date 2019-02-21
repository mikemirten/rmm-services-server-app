package com.rmm.rmmservicesserverapp.exceptionhandler;

import com.rmm.rmmservicesserverapp.jsonapi.Error;
import com.rmm.rmmservicesserverapp.jsonapi.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Errors customization service.
 *
 * TODO: Add handlers for other exceptions to represent them as a JSON=API documents.
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler
{
    /**
     * Handler for validation of method's argument exception
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (
        MethodArgumentNotValidException exception,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        List<Error> errors = new ArrayList<>(fieldErrors.size());

        for (FieldError fieldError: fieldErrors)
        {
            errors.add(new Error(
                "Constraint violation",
                fieldError.getDefaultMessage(),
                fieldError.getField()
            ));
        }

        return handleExceptionInternal(
            exception,
            new ErrorResponse(errors),
            headers,
            HttpStatus.BAD_REQUEST,
            request
        );
    }
}
