package shop.shop.domain.Item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.shop.domain.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hazel
 */


//추상클래스
@Entity
@Getter
@Setter
//상속관계 타입을 지정해줘야함 - 싱글 테이블 전략 선택
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//부모 클래스에 선언해서 하위 클래스를 구분하는 용도의 칼럼
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    //공통 속성들
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;



    //중간 테이블 매핑이 필요함
    //객체는 다대다가 가능하지만, 관계형 디비는 1대다 , 다대1의 중간 테이블이 필요
    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();



}
