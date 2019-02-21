package com.rmm.rmmservicesserverapp.repository;

import com.rmm.rmmservicesserverapp.domain.model.Device;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<Device, Integer> {
}
