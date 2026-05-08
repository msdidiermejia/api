package com.bun.register.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.OffsetDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import com.bun.register.dto.request.RegistrationData;
import com.bun.register.dto.request.RegistrationHabit;
import com.bun.register.dto.request.RegistrationQuestion;
import com.bun.register.dto.response.InfoAccessCustomer;
import com.bun.register.model.PersonalizacionUsuario;
import com.bun.register.model.PreguntaSeguridadUsuario;
import com.bun.register.model.TipoPersonalizacion;
import com.bun.register.model.Usuario;
import com.bun.register.model.Valor;

@Component
public class UserMapper {

    public Usuario toModel(RegistrationData data) {
        Usuario user = new Usuario();
        user.setEstado(data.getStateAccount());
        user.setFechaConexionActual(toDate(data.getCurrentConnectionDate()));
        user.setFechaRegistro(toDate(data.getRegistrationDate()));
        user.setFechaUltimaConexion(toDate(data.getLastConnectionDate()));
        user.setIpConexionActual(data.getCurrentConnectionIp());
        user.setIpUltimaConexion(data.getIpLastConnection());
        user.setMedioConfirmacionOtp("N/A");
        user.setNumeroDocumento(data.getId());
        user.setPortalRegistro(data.getRegistrationPortal());
        user.setSessionId(data.getSessionId());
        user.setTipoDocumento(data.getCodTypeIdentification());
        return user;
    }

    public InfoAccessCustomer toInfoAccessCustomer(Usuario usuario) {
        InfoAccessCustomer info = new InfoAccessCustomer();
        info.setCodTypeIdentification(usuario.getTipoDocumento());
        info.setId(usuario.getNumeroDocumento());
        info.setStateAccount(usuario.getEstado());
        info.setIpLastConnection(usuario.getIpUltimaConexion());
        info.setLastConnectionDate(usuario.getFechaUltimaConexion().toString());
        info.setCurrentConnectionIp(usuario.getIpConexionActual());
        info.setCurrentConnectionDate(usuario.getFechaConexionActual().toString());
        info.setSessionId(usuario.getSessionId());
        info.setRegistrationPortal(usuario.getPortalRegistro());
        info.setRegistrationDate(usuario.getFechaRegistro() != null ? usuario.getFechaRegistro().toString() : null);
        return info;
    }

    private Date toDate(String dateIn) {
        return Date.from(OffsetDateTime.parse(dateIn).toInstant());
    }


    public static PersonalizacionUsuario map(RegistrationHabit habit, RegistrationData customer) {
        PersonalizacionUsuario out = new PersonalizacionUsuario();
        out.setTipoDocumento(customer.getCodTypeIdentification());
        out.setNumeroDocumento(customer.getId());
        TipoPersonalizacion tipoPersonalizacion = new TipoPersonalizacion();
        tipoPersonalizacion.setCodigo(String.valueOf(habit.getIdHabits()));
        out.setTipoPersonalizacionBean(tipoPersonalizacion);
        out.setMontoMaximo((habit.getMaximumAmountTrx() != null && NumberUtils.isCreatable(String.valueOf(habit.getMaximumAmountTrx()))) ?
            new BigDecimal(habit.getMaximumAmountTrx()) : null);
        out.setNumeroMaximo((habit.getMaximumNumberTrx() != null && NumberUtils.isCreatable(String.valueOf(habit.getMaximumNumberTrx()))) ?
            new BigDecimal(habit.getMaximumNumberTrx()) : null);
        return out;
    }

    public static PreguntaSeguridadUsuario map(RegistrationQuestion question, RegistrationData customer) {
        PreguntaSeguridadUsuario out = new PreguntaSeguridadUsuario();
        out.setNumeroDocumento(customer.getId());
        out.setTipoDocumento(customer.getCodTypeIdentification());
        out.setRespuesta(question.getAnswer());
        Valor valor = new Valor();
        valor.setId((question.getId() != null && NumberUtils.isCreatable(question.getId())) ?
            new BigInteger(question.getId()) : null);
        out.setValor(valor);
        return out;
    }

}
