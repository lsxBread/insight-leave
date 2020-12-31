package com.insightleave.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPutDto {
    private String title;
    private String firstname;
    private String lastname;
    private String startDate;
    private String endDate;
}
