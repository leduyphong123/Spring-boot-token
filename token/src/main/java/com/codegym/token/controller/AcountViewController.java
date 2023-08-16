package com.codegym.token.controller;


import com.codegym.token.dto.AcountDTO;
import com.codegym.token.service.AcountService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
public class AcountViewController {

    private AcountService acountService;
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public ModelAndView login (){
      return new ModelAndView("login","acountDTO",new AcountDTO());
    }
}
