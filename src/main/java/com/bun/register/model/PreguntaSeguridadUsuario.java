package com.bun.register.model;

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PREGUNTA_SEGURIDAD_USUARIO")
@Setter
@Getter
public class PreguntaSeguridadUsuario implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @SequenceGenerator(name = "PREGUNTA_USUARIO_GENERATOR_ID", sequenceName = "PREG_SEG_USU_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PREGUNTA_USUARIO_GENERATOR_ID")
  private BigInteger id;
  
  @Column(name = "NUMERO_DOCUMENTO")
  private String numeroDocumento;
  
  private String respuesta;
  
  @Column(name = "TIPO_DOCUMENTO")
  private String tipoDocumento;
  
  @ManyToOne
  @JoinColumn(name = "ID_PREGUNTA")
  private Valor valor;
  
}