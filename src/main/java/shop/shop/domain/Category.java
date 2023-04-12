package shop.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import shop.shop.domain.Item.Item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hazel
 */

@Getter
@Setter
@Entity
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;
    private String name;

    //예제를 위한 ManyToMany
    //실무에서는 매니투매니를 사용하지 않음!
    @ManyToMany
    @JoinTable(name = "category_item",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    //카테고리도 리스트로 아이템을 가지고 , 아이템도 카테고리를 리스트로 가진다.
    private List<Item> items= new ArrayList();

    //계층 구조 연관 관계
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    //카테고리는 자식을 여러개 가질 수 있음.
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    //todo 연관관계 편의 메서드
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }

}
