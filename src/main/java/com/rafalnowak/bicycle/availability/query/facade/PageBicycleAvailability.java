package com.rafalnowak.bicycle.availability.query.facade;

import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageBicycleAvailability implements Serializable {

    List<BicycleAvailability> availabilities;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}