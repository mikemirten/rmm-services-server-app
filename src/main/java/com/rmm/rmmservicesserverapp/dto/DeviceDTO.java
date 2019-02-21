package com.rmm.rmmservicesserverapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rmm.rmmservicesserverapp.action.Create;
import com.rmm.rmmservicesserverapp.action.Update;
import com.rmm.rmmservicesserverapp.validator.DeviceTypeValid;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * Data Transfer Object for an attributes of Device
 */
public class DeviceDTO
{
    /**
     * Name of device
     */
    @NotNull(groups = {Create.class})
    @Length(min = 4, max = 255, groups = { Create.class, Update.class })
    private final String name;

    /**
     * Type of device
     * String representation to validate before converting to DeviceType enum.
     */
    @NotNull(groups = {Create.class})
    @DeviceTypeValid(groups = { Create.class, Update.class })
    private final String type;

    public DeviceDTO(
        @JsonProperty("name") String name,
        @JsonProperty("type") String type
    ) {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public String getType()
    {
        return type;
    }
}
