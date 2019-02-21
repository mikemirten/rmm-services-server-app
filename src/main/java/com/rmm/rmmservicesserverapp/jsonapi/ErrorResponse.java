package com.rmm.rmmservicesserverapp.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * JSON-API Response with error(s)
 *
 * {
 *     "errors": [
 *         { ... },
 *         { ... }
 *     ]
 * }
 */
@ResponseBody
public class ErrorResponse
{
    @JsonProperty
    private final List<Error> errors;

    public ErrorResponse(List<Error> errors)
    {
        this.errors = errors;
    }
}
