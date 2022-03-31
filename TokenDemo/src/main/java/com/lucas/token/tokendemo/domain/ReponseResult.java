package com.lucas.token.tokendemo.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ReponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public ReponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ReponseResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public ReponseResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
