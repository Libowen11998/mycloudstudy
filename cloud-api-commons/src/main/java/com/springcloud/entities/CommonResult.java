package com.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private Integer code;  //返回的信息:例如 200 404
    private String message;//返回的信息提示:例如:成功,失败
    private T data;        //返回的数据

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }
}
