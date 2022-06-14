package com.mini.babmeokeon.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mini.babmeokeon.dto.ResponseDto;
import lombok.Data;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
public class FormLoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String errorMsg = "";

        if (exception instanceof UsernameNotFoundException) {
            errorMsg = "존재하지 않는 아이디입니다.";
        } else if (exception instanceof BadCredentialsException) {
            errorMsg = "아이디 또는 비밀번호가 잘못 입력 되었습니다.";
        }

        if (!StringUtils.isEmpty(errorMsg)) {
            request.setAttribute("errorMsg", errorMsg);
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ObjectMapper mapper = new ObjectMapper();
        ResponseDto<Object> responseDto = new ResponseDto<>(false, errorMsg);
        String result =mapper.writeValueAsString(responseDto);
        response.getWriter().write(result);
    }


}
