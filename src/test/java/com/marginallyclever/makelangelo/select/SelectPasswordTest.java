package com.marginallyclever.makelangelo.select;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SelectPasswordTest {
    private static final Logger logger = LoggerFactory.getLogger(SelectPasswordTest.class);

    @Test
    public void testInitialPassword() {
        SelectPassword selectPassword = new SelectPassword("testPassword", "Test Label", "defaultPass");
        assertEquals("defaultPass", selectPassword.getPassword(), "Initial password should be 'defaultPass'");
    }

    @Test
    public void testSetPassword() {
        SelectPassword selectPassword = new SelectPassword("testPassword", "Test Label", "defaultPass");
        selectPassword.setPassword("newPass");
        assertEquals("newPass", selectPassword.getPassword(), "Password should be 'newPass' after setting");

        selectPassword.setPassword("anotherPass");
        assertEquals("anotherPass", selectPassword.getPassword(), "Password should be 'anotherPass' after setting");
    }
}