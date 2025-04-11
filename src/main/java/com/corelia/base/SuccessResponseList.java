package com.corelia.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter @Setter
public class SuccessResponseList<T> extends BaseResponse {

    private List<T> data;

    public SuccessResponseList(List<T> data) {
        super(HttpStatus.OK.value(),Boolean.TRUE);
        this.data = data;
    }
}
