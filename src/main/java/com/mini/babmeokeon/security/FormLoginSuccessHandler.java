package com.mini.babmeokeon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.babmeokeon.dto.ResponseDto;
import com.mini.babmeokeon.dto.UserInfoDto;
import com.mini.babmeokeon.model.User;
import com.mini.babmeokeon.security.jwt.JwtTokenUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_TYPE = "BEARER";

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
                                        final Authentication authentication) throws IOException {
        final UserDetailsImpl userDetails = ((UserDetailsImpl) authentication.getPrincipal());
        // Token 생성

        User user  = userDetails.getUser();
        String nickname = user.getNickname();
        String icon_url = user.getIcon_url();
        Long id = user.getId();

        UserInfoDto userInfoDto = new UserInfoDto(id,nickname,icon_url);

        final String token = JwtTokenUtils.generateJwtToken(userDetails);
        response.addHeader(AUTH_HEADER, TOKEN_TYPE + " " + token);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto<Object> responseDto = new ResponseDto<>(true, "로그인 성공", userInfoDto);
        String result =mapper.writeValueAsString(responseDto);
        response.getWriter().write(result);
    }

}
