package com.codegym.token.converter;

import com.codegym.token.dto.AcountDTO;
import com.codegym.token.entity.Acount;
import org.springframework.beans.BeanUtils;

import java.util.function.Function;

public class AcountDTOConverterAcount implements Function<AcountDTO, Acount> {
    @Override
    public Acount apply(AcountDTO acountDTO) {
        Acount acount = new Acount();
        BeanUtils.copyProperties(acountDTO,acount);
        return acount;
    }
}
