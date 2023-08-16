package com.codegym.token.converter;

import com.codegym.token.dto.AcountDTO;
import com.codegym.token.entity.Acount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AcountConverterAcountDTO implements Function<Acount, AcountDTO> {
    @Override
    public AcountDTO apply(Acount acount) {
        AcountDTO acountDTO = new AcountDTO();
        BeanUtils.copyProperties(acount,acountDTO);
        return acountDTO;
    }
}
