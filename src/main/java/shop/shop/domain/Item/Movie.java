package shop.shop.domain.Item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

/**
 * @author hazel
 */

@Entity
@Getter
@Setter
//엔티티 저장 시 구분 칼럼에 입력할 값을 지정,
// M이라면 부모 클래스 Item의 dtype에 M이 저장됨.
@DiscriminatorValue("M")
public class Movie extends Item{
    private String director;
    private String actor;


}
