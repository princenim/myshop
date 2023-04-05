package shop.shop.exception.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import shop.shop.exception.BadRequestException;
import shop.shop.model.BaseModel;

/**
 * @author hazel
 */

//모든 rest controller에서 발생하는 exception을 처리
@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
//ResponseEntityExceptionHandler는 Spring MVC에서 발생할 수잇는 예외들에 대해 미리 핸들링 해놓은 클래스
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    //()안에 들어가는 즉, BadRequestException이 발생하면 처리하는 메소드를 작성한다는 뜻.
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody //Json형태로 리턴
    //Exception이 발생햇을 때 응답코드와 메세지를 지정해 리턴 여기서는 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handle(Exception exception) {
        String defaultCode = "ER-9999";
        BaseModel baseModel = new BaseModel();
        String erCode;

        //에러를 캐치해서 가져온 메세지
        if (exception.getMessage().length() <= 0) {
            erCode = defaultCode;
        } else {
            erCode = exception.getMessage();
        }

        try {
            baseModel.setCode(erCode);
            baseModel.setMessage("test");
        } catch (Exception e) {
            baseModel.setCode(defaultCode);
            baseModel.setCode("test");
        }
        baseModel.setStatus(HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.badRequest().body(baseModel);
    }
}
