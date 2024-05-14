package com.uma.example.springuma.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryInforme extends JpaRepository<Informe, Long>{

    List<Informe> findByImagenId(Long id);

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation
	// Además de los metodos de consulta basicos, especifico un metodo que busque una cuenta por el CCC
	// A través del nombre del metodo, spring sabe que tiene que hacer una consulta y devolver la cuenta con el ccc especificado
	// Utilizamos el nombre del metodo findBy"propiedad"  propiedad debe existir en la clase Cuenta
}
