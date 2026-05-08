package com.bun.register.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bun.register.dto.request.ContextTransactionDTO;

class ContextTransactionDTOTest {

    @Test
    void testDeepCopy_CreatesIndependentCopy() {
        // Arrange
        ContextTransactionDTO original = new ContextTransactionDTO();
        original.setIdTx("tx-123");
        original.setIdUser("CC123456789");
        original.setIdConsumer("APPOMNI");
        original.setIdService("REGISTER");
        original.setCodTypeTx("REG");
        original.setDateTx("2024-08-08T10:08:25-05:00");

        // Act
        ContextTransactionDTO copy = original.deepCopy();

        // Assert
        assertNotNull(copy);
        assertEquals(original.getIdTx(), copy.getIdTx());
        assertEquals(original.getIdUser(), copy.getIdUser());
        assertEquals(original.getIdConsumer(), copy.getIdConsumer());
        assertEquals(original.getIdService(), copy.getIdService());
        assertEquals(original.getCodTypeTx(), copy.getCodTypeTx());
        assertEquals(original.getDateTx(), copy.getDateTx());
        assertNotSame(original, copy);
    }

    @Test
    void testDeepCopy_ModifyingCopyDoesNotAffectOriginal() {
        // Arrange
        ContextTransactionDTO original = new ContextTransactionDTO();
        original.setIdTx("tx-original");

        // Act
        ContextTransactionDTO copy = original.deepCopy();
        copy.setIdTx("tx-modified");

        // Assert
        assertEquals("tx-original", original.getIdTx());
        assertEquals("tx-modified", copy.getIdTx());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        ContextTransactionDTO dto = new ContextTransactionDTO();

        // Act
        dto.setIdTx("tx-123");
        dto.setIdUser("CC123456789");
        dto.setIdConsumer("APPOMNI");
        dto.setIdService("REGISTER");
        dto.setCodTypeTx("REG");
        dto.setDateTx("2024-08-08T10:08:25-05:00");

        // Assert
        assertEquals("tx-123", dto.getIdTx());
        assertEquals("CC123456789", dto.getIdUser());
        assertEquals("APPOMNI", dto.getIdConsumer());
        assertEquals("REGISTER", dto.getIdService());
        assertEquals("REG", dto.getCodTypeTx());
        assertEquals("2024-08-08T10:08:25-05:00", dto.getDateTx());
    }
}
