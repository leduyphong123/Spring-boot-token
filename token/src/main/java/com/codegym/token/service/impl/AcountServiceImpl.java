package com.codegym.token.service.impl;

import com.codegym.token.config.JwtTokenUtil;
import com.codegym.token.dto.AcountDTO;
import com.codegym.token.entity.Acount;
import com.codegym.token.repository.AcountRepository;
import com.codegym.token.service.AcountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
@AllArgsConstructor
public class AcountServiceImpl implements AcountService {

    private AcountRepository acountRepository;
    private PasswordEncoder passwordEncoder;
//    private Function<AcountDTO,Acount> function;
    private JwtTokenUtil jwtTokenUtil;
    @Override
    public String getToken(AcountDTO acountDTO) {
        String token="";
        Acount acount = acountRepository.findByUserName(acountDTO.getUserName());
      if(passwordEncoder.matches(acountDTO.getPassword(),acount.getPassword())){
          token= jwtTokenUtil.generateToken(acount);
          return token;
      }
        return token;
    }
}
