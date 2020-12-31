package com.insightleave.userservice.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Long id;

  @Column(name = "title")
  private String title;

  @Column(name="email", unique = true, nullable = false)
  private String email;

  @Column(name = "firstname")
  private String firstname;

  @Column(name = "lastname")
  private String lastname;

  @Column(name="start_date")
  private String startDate;

  @Column(name = "end_date")
  private String endDate;

  @Column(name="encoded_password", nullable = false)
  private String password;

  @Column(name = "role")
  private String role;
}
