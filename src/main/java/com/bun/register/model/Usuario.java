package com.bun.register.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Usuario")
@Getter
@Setter
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "VALOR_GENERATOR_ID", sequenceName = "USUARIO_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALOR_GENERATOR_ID")
    private BigInteger id;
    
    private String estado;
    
    @Column(name = "FECHA_CONEXION_ACTUAL")
    private Date fechaConexionActual;
    
    @Column(name = "FECHA_REGISTRO")
    private Date fechaRegistro;
    
    @Column(name = "FECHA_ULTIMA_CONEXION")
    private Date fechaUltimaConexion;
    
    @Column(name = "IP_CONEXION_ACTUAL")
    private String ipConexionActual;
    
    @Column(name = "IP_ULTIMA_CONEXION")
    private String ipUltimaConexion;
    
    @Column(name = "MEDIO_CONFIRMACION_OTP")
    private String medioConfirmacionOtp;
    
    @Column(name = "NUMERO_DOCUMENTO")
    private String numeroDocumento;
    
    @Column(name = "PORTAL_REGISTRO")
    private String portalRegistro;
    
    @Column(name = "SESSION_ID")
    private String sessionId;
    
    @Column(name = "TIPO_DOCUMENTO")
    private String tipoDocumento;

}
