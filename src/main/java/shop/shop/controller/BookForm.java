package shop.shop.controller;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hazel
 */
@Getter
@Setter
public class BookForm {
    private Long id;
    private String name;
    private int price;
    private int stockQuantity;
    private String author;
    private String isbn;
}
