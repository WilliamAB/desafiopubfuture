package com.williamab.desafiopubfuture.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.williamab.desafiopubfuture.model.BasicEntity;

/**
 * Repositório base para os repositórios da aplicação.
 * 
 * @author William Alberto Bertoldi (william.bertoldi@gmail.com)
 * 
 * @param <E> uma entidade que estenda {@link BasicEntity}
 */
@NoRepositoryBean
public interface BasicRepository<E extends BasicEntity> extends JpaRepository<E, Long> {

}
