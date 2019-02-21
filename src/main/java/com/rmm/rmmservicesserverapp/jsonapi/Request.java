package com.rmm.rmmservicesserverapp.jsonapi;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;

/**
 * JSON-API request
 *
 * {
 *     "data": { ... }
 * }
 *
 * @param <T> Type of "data"-field
 */
public class Request<T>
{
    @Valid
    private final T data;

    public Request(@JsonProperty("data") T data)
    {
        this.data = data;
    }

    public T getData()
    {
        return data;
    }
}
