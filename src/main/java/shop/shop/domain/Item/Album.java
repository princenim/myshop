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
@DiscriminatorValue("A")
public class Album extends Item{
    private String artist;
    private String etc;
}
