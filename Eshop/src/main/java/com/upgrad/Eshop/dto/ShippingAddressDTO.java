package com.upgrad.Eshop.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.upgrad.Eshop.entities.EshopUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ShippingAddressDTO {

    @JsonProperty("Id")
    int id;

    @JsonProperty("Name")
    String Name;

    @JsonProperty("Phone No.")
    String PhoneNumber;

    @JsonProperty("City")
    String City;

    @JsonProperty("Landmark")
    String Landmark;

    @JsonProperty("Street")
    String Street;

    @JsonProperty("State")
    String State;

    @JsonProperty("Zip code")
    String ZipCode;

    @JsonProperty("userId")
    int userId;
}
