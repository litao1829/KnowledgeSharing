package com.litao.share.user.domain.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @Schema(defaultValue = "手机号",requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "[手机号]不能为空")
    private String phone;

    @Schema(defaultValue = "密码",requiredMode = Schema.RequiredMode.REQUIRED)
    private String password;

}
