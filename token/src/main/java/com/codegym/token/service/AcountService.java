package com.codegym.token.service;

import com.codegym.token.dto.AcountDTO;

public interface AcountService {
    String getToken(AcountDTO acountDTO);
}
