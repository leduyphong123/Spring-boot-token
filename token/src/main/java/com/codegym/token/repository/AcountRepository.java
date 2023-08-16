package com.codegym.token.repository;

import com.codegym.token.entity.Acount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;


@Repository
public interface AcountRepository extends CrudRepository<Acount,Long> {
//    Acount findByUserName (String userName);
    Acount findByUserName (@NotNull String userName);
}
