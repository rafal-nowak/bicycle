package com.rafalnowak.bicycle.rental.query.facade;

import com.rafalnowak.bicycle.rental.command.domain.UserRentals;
import lombok.Value;

import java.io.Serializable;
import java.util.List;

@Value
public class PageUserRentals implements Serializable {

    List<UserRentals> rentals;
    Integer currentPage;
    Integer totalPages;
    Long totalElements;
}
