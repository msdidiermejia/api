package com.bun.register.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TIPO_PERSONALIZACION")
@Setter
@Getter
public class TipoPersonalizacion implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String codigo;
  
  @Column(name = "DESCRIPCION")
  private String descripcion;
  
  @Column(name = "MONTO_MAXIMO")
  private BigDecimal montoMaximo;
  
  @Column(name = "MONTO_MAXIMO_DEFAULT")
  private BigDecimal montoMaximoDefault;
  
  @Column(name = "MONTO_MINIMO")
  private BigDecimal montoMinimo;
  
  @Column(name = "NUMERO_MAXIMO")
  private BigDecimal numeroMaximo;
  
  @Column(name = "NUMERO_MAXIMO_DEFAULT")
  private BigDecimal numeroMaximoDefault;
  
  @Column(name = "NUMERO_MINIMO")
  private BigDecimal numeroMinimo;
  
  @OneToMany(mappedBy = "tipoPersonalizacionBean")
  private List<PersonalizacionUsuario> personalizacionUsuarios;
}
