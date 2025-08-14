package dev.lin.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CuentaTest {

    private Cuenta cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new Cuenta(10000, 12); // Saldo inicial: 10000, Tasa anual: 12%
    }

    @Test
    void testConsignar() {
        // GIVEN: Una cuenta con 10000 de saldo
        float cantidad = 5000;
        // WHEN: Se consignan 5000
        cuenta.consignar(cantidad);
        // THEN: El saldo debe ser 15000
        assertEquals(15000, cuenta.getSaldo(), 0.01);
        assertEquals(1, cuenta.getNumeroConsignaciones());
    }

    @Test
    void testRetirarConSaldoSuficiente() {
        // GIVEN: Una cuenta con 10000 de saldo
        float cantidad = 2000;
        // WHEN: Se retiran 2000
        cuenta.retirar(cantidad);
        // THEN: El saldo debe ser 8000
        assertEquals(8000, cuenta.getSaldo(), 0.01);
        assertEquals(1, cuenta.getNumeroRetiros());
    }

    @Test
    void testRetirarSinSaldoSuficiente() {
        // GIVEN: Una cuenta con 10000 de saldo
        float cantidad = 15000;
        // WHEN: Se intentan retirar 15000
        cuenta.retirar(cantidad);
        // THEN: El saldo no debe cambiar y el número de retiros debe ser 0
        assertEquals(10000, cuenta.getSaldo(), 0.01);
        assertEquals(0, cuenta.getNumeroRetiros());
    }

    @Test
    void testExtractoMensual() {
        // GIVEN: Una cuenta con 10000 de saldo y 12% de tasa anual
        // Interés mensual = (12/12) / 100 * 10000 = 100
        // WHEN: Se calcula el extracto mensual
        cuenta.extractoMensual();
        // THEN: El saldo debe aumentar 100
        assertEquals(10100, cuenta.getSaldo(), 0.01);
    }

    @Test
    void testObtenerInformacion() {
        // GIVEN: Una cuenta con ciertos valores
        cuenta.consignar(500);
        cuenta.retirar(200);
        // WHEN: Se obtiene la información
        String info = cuenta.imprimir();
        // THEN: La información debe coincidir con los valores esperados
        String expected = "Saldo: 10300.0\n" +
                          "Número de consignaciones: 1\n" +
                          "Número de retiros: 1\n" +
                          "Tasa anual: 12.0\n" +
                          "Comisión mensual: 0.0";
        assertEquals(expected, info);
    }
}