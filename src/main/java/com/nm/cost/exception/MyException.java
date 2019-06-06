package com.nm.cost.exception;
import lombok.Getter;
import java.io.Serializable;

/**
 * 构造异常参数
 */
@Getter
public class MyException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -6934422547075319699L;

    private String code;
    private String message;

    public MyException(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
