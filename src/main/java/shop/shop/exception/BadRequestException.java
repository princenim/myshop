package shop.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author hazel
 */



@ResponseStatus(HttpStatus.BAD_REQUEST)
//사용자 정의 에러 클래스 만들기
public class BadRequestException  extends RuntimeException{

    public BadRequestException(String message){
        super(message);
    }

}
