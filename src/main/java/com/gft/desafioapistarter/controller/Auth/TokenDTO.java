package com.gft.desafioapistarter.controller.Auth;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TokenDTO {

    private String type;
    private String token;

}
