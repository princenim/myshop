package shop.shop.model;

import lombok.Getter;

/**
 * @author hazel
 */


@Getter
public class JsonResult {

    private final Integer status;
    private final String message;
    private final Object data;


    public JsonResult(Object data) {
        this.status = 200;
        this.message = "OK";
        this.data = data;
    }

    public JsonResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;

    }

    public static JsonResult ok(Object data) {
        return new JsonResult(data);
    }

    public static JsonResult ok() {
        return new JsonResult(null);
    }


}
