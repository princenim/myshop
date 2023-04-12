package shop.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hazel
 */
@Entity
@Getter
@Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;


    //1대 1관계는 fk를 아무데나 넣어도됨.
    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY)
    private Order order;

    @Embedded
    private Address address;

    //Enum타입일때
    //디폴특사 ORDINAL인데 ,1,2,3이런식으로 숫자가 들어감
    // 따라서  만약 중간에 값이 생기면 망하므로 쓰지 말기!
    @Enumerated(EnumType.STRING)
    private DeliveryStatus status; //enum(READY, CAMP)


}
