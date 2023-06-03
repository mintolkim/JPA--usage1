package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Adress { //data 타입은 값이 변경되면 안되기 때문에 setter 설정 X
    private String city;
    private String street;
    private String zipcode;

    protected Adress() {
    }

    public Adress(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
