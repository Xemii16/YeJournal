package com.balamut.yejournal.school.request;

public record SchoolAddressRequest(
        String region,
        String area,
        String street,
        String zipCode
) {
}
