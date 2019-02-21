package com.rmm.rmmservicesserverapp.jsonapi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * JSON-API Error-object
 */
public class Error
{
    /**
     * Summary
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String title;

    /**
     * Explanation
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String details;

    /**
     * Path to the invalid attribute
     */
    @JsonProperty
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final String path;

    public Error(String title, String details, String path)
    {
        this.title   = title;
        this.details = details;
        this.path    = path;
    }
}
