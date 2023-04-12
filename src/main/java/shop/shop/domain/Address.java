package shop.shop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

/**
 * @author hazel
 */
//jpa의 내장타입이라는 의미w
@Embeddable
@Getter
public class Address {
    private String city;
    private String street;
    private String zipcode;


    //값타입은 생성할때만 처음 값이 들어가고, 값이 변하지 못하게 설계하는게 좋은 방식임.
    //따라서 setter을 제공하지 않아야함.
    //즉, 생성자에서 값을 모두 초기화해 변경 불가능한 클래스로 만들어야함.
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    //하지만! Jpa 스펙상에 엔티티나 임베디드 타입은 객체 생성시 리플렉션 같은 기술을 사용 하기 때문에 자바 기본 생성자가 필요하다.
    // protected나 public으로 설정해 만들어야함.
    protected Address() {
    }

}
