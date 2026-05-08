package com.bun.register.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "PERSONALIZACION_USUARIO")
@Setter
@Getter
public class PersonalizacionUsuario implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @SequenceGenerator(name = "PERSONALIZACION_USUARIO_GENERATOR_ID", sequenceName = "PERSONALIZACION_USUARIO_ID_SEQ", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONALIZACION_USUARIO_GENERATOR_ID")
  private BigInteger id;
  
  @Column(name = "MONTO_MAXIMO")
  private BigDecimal montoMaximo;
  
  @Column(name = "NUMERO_DOCUMENTO")
  private String numeroDocumento;
  
  @Column(name = "NUMERO_MAXIMO")
  private BigDecimal numeroMaximo;
  
  @Column(name = "TIPO_DOCUMENTO")
  private String tipoDocumento;
  
  @ManyToOne
  @JoinColumn(name = "TIPO_PERSONALIZACION")
  private TipoPersonalizacion tipoPersonalizacionBean;

}
