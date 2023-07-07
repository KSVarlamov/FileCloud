package ru.netology.filecloud.security.services.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import ru.netology.filecloud.dto.Error;

import java.io.IOException;

@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {

    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {
        logger.error(" Unauthorized error: {}", authException.getMessage());
        if (authException.getMessage().startsWith("Bad credentials")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            var writer = response.getWriter();
            var error = new Error("Bad credentials", 1);
            var mapper = new ObjectMapper();

            writer.write(mapper.writeValueAsString(error));
            writer.flush();
            return;
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "ha-ha");
    }
}