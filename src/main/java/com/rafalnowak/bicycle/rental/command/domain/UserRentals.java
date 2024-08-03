package com.rafalnowak.bicycle.rental.command.domain;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "user_id_unique",
                        columnNames = "userId"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRentals {
    @Id
    @SequenceGenerator(
            name = "user_rentals_id_seq",
            sequenceName = "user_rentals_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_rentals_id_seq"
    )
    Integer id;

    @Column
    Integer userId;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "bicycles", joinColumns = @JoinColumn(name = "user_rentals_id"))
    @Column(name = "bicycle", nullable = true)
    List<String> bicycles = new ArrayList<>();

    @Version
    private Integer version;

    @Transient
    RentingPolicy rentingPolicy;

    @Transient
    ReturningPolicy returningPolicy;

    UserRentals(final Integer userId) {
        this.userId = userId;
    }

    public void rentBike(String bicycleId) {
        if (rentingPolicy == null) {
            throw new IllegalStateException("Renting policy not set");
        }

        rentingPolicy.rentBicycle(this, bicycleId);
    }

    public void returnBike(String bicycleId) {
        if (returningPolicy == null) {
            throw new IllegalStateException("Renting policy not set");
        }

        returningPolicy.returnBicycle(this, bicycleId);
    }

}
