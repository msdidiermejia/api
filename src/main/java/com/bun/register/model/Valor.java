package com.bun.register.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VALOR")
@Setter
@Getter
public class Valor implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private BigInteger id;
  
  private String codigo;
  
  private String descripcion;
  
  private String estado;
  
  @OneToMany(mappedBy = "valor")
  private List<PreguntaSeguridadUsuario> preguntaSeguridadUsuarios;
  
  @ManyToOne
  @JoinColumn(name = "TIPO")
  private TipoValor tipoValor;

}
