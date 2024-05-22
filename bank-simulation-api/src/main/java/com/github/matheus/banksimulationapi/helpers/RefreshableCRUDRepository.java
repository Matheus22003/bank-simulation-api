package com.github.matheus.banksimulationapi.helpers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;

@NoRepositoryBean
public interface RefreshableCRUDRepository<T, ID> extends CrudRepository<T, ID> {

    public void refresh(T t);

    public void refresh(Collection<T> s);

    public void flush();
}