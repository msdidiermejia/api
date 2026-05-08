package com.bun.register.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.bun.register.dto.request.DeviceInfoDTO;

class DeviceInfoDTOTest {

    @Test
    void testDeepCopy_CreatesIndependentCopy() {
        // Arrange
        DeviceInfoDTO original = new DeviceInfoDTO();
        original.setIp("191.156.5.107");
        original.setDevice("Android");
        original.setChannel("MOBILE_CHANNEL");
        original.setApplication("PGI_APP");

        // Act
        DeviceInfoDTO copy = original.deepCopy();

        // Assert
        assertNotNull(copy);
        assertEquals(original.getIp(), copy.getIp());
        assertEquals(original.getDevice(), copy.getDevice());
        assertEquals(original.getChannel(), copy.getChannel());
        assertEquals(original.getApplication(), copy.getApplication());
        assertNotSame(original, copy);
    }

    @Test
    void testDeepCopy_ModifyingCopyDoesNotAffectOriginal() {
        // Arrange
        DeviceInfoDTO original = new DeviceInfoDTO();
        original.setIp("191.156.5.107");

        // Act
        DeviceInfoDTO copy = original.deepCopy();
        copy.setIp("192.168.1.1");

        // Assert
        assertEquals("191.156.5.107", original.getIp());
        assertEquals("192.168.1.1", copy.getIp());
    }

    @Test
    void testGettersAndSetters() {
        // Arrange
        DeviceInfoDTO dto = new DeviceInfoDTO();

        // Act
        dto.setIp("191.156.5.107");
        dto.setDevice("Android");
        dto.setChannel("MOBILE_CHANNEL");
        dto.setApplication("PGI_APP");

        // Assert
        assertEquals("191.156.5.107", dto.getIp());
        assertEquals("Android", dto.getDevice());
        assertEquals("MOBILE_CHANNEL", dto.getChannel());
        assertEquals("PGI_APP", dto.getApplication());
    }
}
