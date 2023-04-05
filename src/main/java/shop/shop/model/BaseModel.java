package shop.shop.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @author hazel
 */
@Getter
@Setter
public class BaseModel {
    //에러 발생시 응답하는 에러 정보 기본 클래스 작성
    private int status = 200;
    private String code = "0";
    private String message = "OK";

}
