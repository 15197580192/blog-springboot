package com.hj.common.dto;

import lombok.Data;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class GetCodeDto {
    @NotBlank(message = "用户名不能为空")
    private String userId;

    public static String code;

    public static Date date;
}
