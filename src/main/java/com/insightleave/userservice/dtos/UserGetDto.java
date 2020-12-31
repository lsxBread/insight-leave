package com.insightleave.userservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserGetDto {
    private String title;
    private String email;
    private String firstname;
    private String lastname;
    private String startDate;
    private String endDate;
}
