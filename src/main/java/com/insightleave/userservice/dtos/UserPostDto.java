package com.insightleave.userservice.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserPostDto {
    private String email;
    private String password;
}
