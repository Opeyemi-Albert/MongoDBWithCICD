package com.springDataMongoDB.springDataMongoDB;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Address {
    private String country;
    private String postCode;
    private String city;

}
