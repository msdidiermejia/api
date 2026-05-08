package com.bun.register.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bun.register.model.PreguntaSeguridadUsuario;

public interface PreguntaSeguridadUsuarioRepository extends JpaRepository<PreguntaSeguridadUsuario, BigInteger> {

    void deleteByNumeroDocumento(String numeroDocumento);
}
