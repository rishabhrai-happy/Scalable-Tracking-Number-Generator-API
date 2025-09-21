package com.tracking_number.service;

import com.tracking_number.entity.TrackingNumber;
import com.tracking_number.repository.TrackingNumberRepository;
import com.tracking_number.response.TrackingNumberResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrackingNumberService {

    private final TrackingNumberRepository repository;
    private final Random random = new Random();

    private static final int MAX_ATTEMPTS = 5;

    @Transactional
    public TrackingNumberResponse generateTrackingNumber(String originCountryId,
                                                         String destinationCountryId,
                                                         Double weight,
                                                         OffsetDateTime createdAt,
                                                         UUID customerId,
                                                         String customerName,
                                                         String customerSlug) {


        String trackingNumber = "";
        trackingNumber = generateUniqueCode(originCountryId, destinationCountryId);

        TrackingNumber entity = null;
        int attempt = 0;

        while (attempt < MAX_ATTEMPTS) {

            try {
                entity = TrackingNumber.builder()
                        .id(UUID.randomUUID())
                        .trackingNumber(trackingNumber)
                        .originCountryId(originCountryId)
                        .destinationCountryId(destinationCountryId)
                        .weight(weight)
                        .createdAt(createdAt)
                        .customerId(customerId)
                        .customerName(customerName)
                        .customerSlug(customerSlug)
                        .build();

                repository.save(entity);
                break;
            } catch (DataIntegrityViolationException e) {
                attempt++;
            }
        }

        if (entity == null) {
            throw new RuntimeException("Failed to generate unique tracking number after " + MAX_ATTEMPTS + " attempts.");
        }

        return new TrackingNumberResponse(entity.getTrackingNumber(), entity.getCreatedAt().toString());
    }

    private String generateUniqueCode(String origin, String destination) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        sb.append(origin.charAt(0));
        sb.append(destination.charAt(0));
        for (int i = 0; i < 12; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
