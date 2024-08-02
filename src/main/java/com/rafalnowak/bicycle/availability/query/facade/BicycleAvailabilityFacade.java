package com.rafalnowak.bicycle.availability.query.facade;


import com.rafalnowak.bicycle.availability.command.domain.BicycleAvailability;
import com.rafalnowak.bicycle.availability.command.domain.BicycleId;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log
public
class BicycleAvailabilityFacade {

    private final JpaQueryBicycleAvailabilityRepository bicycleAvailabilityRepository;
    private final BicycleAvailabilityDtoMapper bicycleAvailabilityDtoMapper;
    private final PageBicycleAvailabilityDtoMapper pageBicycleAvailabilityDtoMapper;

    public BicycleAvailabilityDto findByBicycleId(final String bicycleId) {
        final Optional<BicycleAvailability> maybeBicycleAvailability = bicycleAvailabilityRepository.findByBicycleId(BicycleId.of(bicycleId));
        return bicycleAvailabilityDtoMapper.toDto(maybeBicycleAvailability.orElseThrow(BicycleAvailabilityDtoNotFoundException::new));
    }

    public PageBicycleAvailabilityDto findAll(final Pageable pageable) {
        Page<BicycleAvailability> pageOfBicycleAvailabilitiesEntity = bicycleAvailabilityRepository.findAll(pageable);
        List<BicycleAvailability> availabilitiesOnCurrentPage = new ArrayList<>(pageOfBicycleAvailabilitiesEntity.getContent());

        final PageBicycleAvailability pageReservation = new PageBicycleAvailability(
                availabilitiesOnCurrentPage,
                pageable.getPageNumber() + 1,
                pageOfBicycleAvailabilitiesEntity.getTotalPages(),
                pageOfBicycleAvailabilitiesEntity.getTotalElements()
        );
        return pageBicycleAvailabilityDtoMapper.toPageDto(pageReservation);
    }
}
