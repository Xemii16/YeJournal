package com.balamut.yejournal.school.request;

import java.util.UUID;

public record RegisterAdminRequest (UUID userId, UUID schoolId){
}
