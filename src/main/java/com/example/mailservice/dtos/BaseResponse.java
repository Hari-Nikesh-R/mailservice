package com.example.mailservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String result;
    private Integer statusCode;
    private String error;
    private Boolean success;
    private T data;
}
