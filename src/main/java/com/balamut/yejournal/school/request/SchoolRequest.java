package com.balamut.yejournal.school.request;

public record SchoolRequest(
        String name,
        String fullName,
        SchoolAddressRequest address,
        String telephones,
        String email,
        String site
        ) {
}
