package com.corelia.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class SuccessResponse<T> extends BaseResponse {

    final private T data;

    public SuccessResponse(T data) {
        super(HttpStatus.OK.value(),Boolean.TRUE);
        this.data = data;
    }
}
