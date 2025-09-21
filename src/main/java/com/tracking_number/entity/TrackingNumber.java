package com.tracking_number.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "tracking_numbers", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"tracking_number"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrackingNumber {

    @Id
    private UUID id;

    @Column(name = "tracking_number", length = 16, nullable = false, unique = true)
    private String trackingNumber;

    @Column(name = "origin_country_id", length = 2, nullable = false)
    private String originCountryId;

    @Column(name = "destination_country_id", length = 2, nullable = false)
    private String destinationCountryId;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "created_at", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "customer_id", nullable = false)
    private UUID customerId;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @Column(name = "customer_slug", nullable = false)
    private String customerSlug;
}
