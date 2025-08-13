package dev.lin.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CuentaCorrienteTest {

    @Test
    void testRetirarConSobregiro() {
        // GIVEN: Una cuenta con 5000 de saldo
        CuentaCorriente cuenta = new CuentaCorriente(5000, 12);
        // WHEN: Se retiran 7000 (más que el saldo)
        cuenta.retirar(7000);
        // THEN: El saldo debe ser 0 y el sobregiro debe ser 2000
        assertEquals(0, cuenta.getSaldo(), 0.01);
        assertEquals(2000, cuenta.getSobregiro(), 0.01);
        assertEquals(1, cuenta.getNumeroRetiros());
    }

    @Test
    void testConsignarConSobregiroExistente() {
        // GIVEN: Una cuenta con 2000 de sobregiro
        CuentaCorriente cuenta = new CuentaCorriente(0, 12);
        cuenta.retirar(2000); // Para generar el sobregiro
        // WHEN: Se consignan 1000
        cuenta.consignar(1000);
        // THEN: El sobregiro debe reducirse a 1000 y el saldo debe ser 0
        assertEquals(0, cuenta.getSaldo(), 0.01);
        assertEquals(1000, cuenta.getSobregiro(), 0.01);
    }

    @Test
    void testConsignarSaldandoSobregiro() {
        // GIVEN: Una cuenta con 1000 de sobregiro
        CuentaCorriente cuenta = new CuentaCorriente(0, 12);
        cuenta.retirar(1000);
        // WHEN: Se consignan 2000 (saldando el sobregiro y depositando el resto)
        cuenta.consignar(2000);
        // THEN: El sobregiro debe ser 0 y el saldo debe ser 1000
        assertEquals(1000, cuenta.getSaldo(), 0.01);
        assertEquals(0, cuenta.getSobregiro(), 0.01);
    }
    
    @Test
    void testObtenerInformacion() {
        // GIVEN: Una cuenta corriente con sobregiro
        CuentaCorriente cuenta = new CuentaCorriente(5000, 12);
        cuenta.retirar(7000);
        // WHEN: Se obtiene la información
        String info = cuenta.imprimir();
        // THEN: La información debe coincidir con los valores esperados
        String expected = "--- Información de Cuenta Corriente ---\n" +
                          "Saldo: 0.0\n" +
                          "Comisión mensual: 0.0\n" +
                          "Número de transacciones: 1\n" +
                          "Valor de sobregiro: 2000.0";
        assertEquals(expected, info);
    }
}