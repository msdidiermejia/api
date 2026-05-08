package com.bun.register.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bun.register.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	Usuario findByIdAndTipoDocumento(Long id, String typeDocument);

	boolean existsByNumeroDocumento(String numeroDocumento);

	Usuario findByNumeroDocumento(String numeroDocumento);

	Usuario findByNumeroDocumentoAndTipoDocumento(String numeroDocumento, String tipoDocumento);

}