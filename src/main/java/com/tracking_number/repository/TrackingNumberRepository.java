package com.tracking_number.repository;

import com.tracking_number.entity.TrackingNumber;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TrackingNumberRepository extends JpaRepository<TrackingNumber, UUID> {
    Optional<TrackingNumber> findByTrackingNumber(String trackingNumber);
}
