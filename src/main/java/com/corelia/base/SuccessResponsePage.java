package com.corelia.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

@Getter @Setter
public class SuccessResponsePage<T> extends BaseResponse {

    private Page<T> data;

    public SuccessResponsePage(Page<T> data) {
        super(HttpStatus.OK.value(), Boolean.TRUE);
        this.data = data;
    }
}
