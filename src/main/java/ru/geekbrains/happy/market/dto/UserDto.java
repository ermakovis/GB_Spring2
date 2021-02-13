package ru.geekbrains.happy.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
   private String userName;
   private String userPassword;
   private String email;
}
