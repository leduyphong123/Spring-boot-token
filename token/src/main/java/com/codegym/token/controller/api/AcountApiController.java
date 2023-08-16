package com.codegym.token.controller.api;

import com.codegym.token.dto.AcountDTO;
import com.codegym.token.service.AcountService;
import lombok.AllArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("user")
@AllArgsConstructor
public class AcountApiController {
    private AcountService acountService;

    @PostMapping("/login")
    public Boolean login (@RequestBody AcountDTO acountDTO,HttpServletResponse response){
        String token = acountService.getToken(acountDTO);
        response.addHeader("token",token);
        if (token.equals("")){
            return false;
        }
        return true;
    }
    @PostMapping("/register")
    public Boolean createAcount(@RequestBody AcountDTO acountDTO){
        return true;
    }
}
