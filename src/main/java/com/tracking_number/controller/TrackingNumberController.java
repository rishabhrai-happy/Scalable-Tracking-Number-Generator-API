package com.tracking_number.controller;

import com.tracking_number.response.TrackingNumberResponse;
import com.tracking_number.service.TrackingNumberService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/next-tracking-number")
@RequiredArgsConstructor
public class TrackingNumberController {

    private final TrackingNumberService service;

    @GetMapping
    public TrackingNumberResponse getNextTrackingNumber(
            @RequestParam String origin_country_id,
            @RequestParam String destination_country_id,
            @RequestParam Double weight,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) OffsetDateTime created_at,
            @RequestParam UUID customer_id,
            @RequestParam String customer_name,
            @RequestParam String customer_slug
    ) {
        return service.generateTrackingNumber(
                origin_country_id.toUpperCase(),
                destination_country_id.toUpperCase(),
                weight,
                created_at,
                customer_id,
                customer_name,
                customer_slug
        );
    }
}
