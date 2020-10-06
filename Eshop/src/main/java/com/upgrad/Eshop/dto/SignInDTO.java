package com.upgrad.Eshop.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class SignInDTO {
    @JsonProperty("Username")
    String Username;

    String Message;

    String token;

    String password;
}
