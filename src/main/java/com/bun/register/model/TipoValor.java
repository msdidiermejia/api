package com.bun.register.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TIPO_VALOR")
@Setter
@Getter
public class TipoValor implements Serializable {
  private static final long serialVersionUID = 1L;
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  private String codigo;
  
  private String descripcion;
  
  private String estado;
  
  @OneToMany(mappedBy = "tipoValor")
  private List<Valor> valors;
}
