package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Adress {
    private String city;
    private String street;
    private String zipcode;

}
