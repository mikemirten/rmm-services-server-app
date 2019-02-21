package com.rmm.rmmservicesserverapp.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * JSON-API Response with data
 *
 * {
 *     "data": { ... }
 * }
 *
 * @param <T> Type of data
 */
@ResponseBody
public class DataResponse<T>
{
    @JsonProperty
    private final T data;

    public DataResponse(T data)
    {
        this.data = data;
    }
}
