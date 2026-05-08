package com.bun.register.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bun.register.model.PersonalizacionUsuario;

public interface PersonalizacionUsuarioRepository extends JpaRepository<PersonalizacionUsuario, BigInteger> {

    void deleteByNumeroDocumento(String numeroDocumento);
}
