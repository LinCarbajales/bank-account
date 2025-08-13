package dev.lin.models;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CuentaAhorrosTest {

    @Test
    void testConsignarConCuentaActiva() {
        // GIVEN: Una cuenta de ahorros activa (saldo > 10000)
        CuentaAhorros cuenta = new CuentaAhorros(15000, 12);
        // WHEN: Se consignan 2000
        cuenta.consignar(2000);
        // THEN: El saldo debe aumentar y el contador debe incrementarse
        assertEquals(17000, cuenta.getSaldo(), 0.01);
        assertEquals(1, cuenta.getNumeroConsignaciones());
    }

    @Test
    void testConsignarConCuentaInactiva() {
        // GIVEN: Una cuenta de ahorros inactiva (saldo < 10000)
        CuentaAhorros cuenta = new CuentaAhorros(5000, 12);
        // WHEN: Se intenta consignar
        cuenta.consignar(2000);
        // THEN: El saldo no debe cambiar ni el contador
        assertEquals(5000, cuenta.getSaldo(), 0.01);
        assertEquals(0, cuenta.getNumeroConsignaciones());
    }

    @Test
    void testRetirarConCuentaActiva() {
        // GIVEN: Una cuenta de ahorros activa (saldo > 10000)
        CuentaAhorros cuenta = new CuentaAhorros(15000, 12);
        // WHEN: Se retiran 5000
        cuenta.retirar(5000);
        // THEN: El saldo debe disminuir y el contador de retiros debe incrementarse
        assertEquals(10000, cuenta.getSaldo(), 0.01);
        assertEquals(1, cuenta.getNumeroRetiros());
    }

    @Test
    void testExtractoMensualConComision() {
        // GIVEN: Una cuenta con 5 retiros o más
        CuentaAhorros cuenta = new CuentaAhorros(20000, 12);
        for (int i = 0; i < 5; i++) {
            cuenta.retirar(1000);
        }
        // WHEN: Se realiza el extracto mensual
        cuenta.extractoMensual();
        // THEN: Se debe aplicar una comisión de 1000 y el interés
        // Saldo después de retiros: 15000
        // Comisión: (5 - 4) * 1000 = 1000
        // Saldo - comisión: 14000
        // Interés mensual: (12/12) / 100 * 14000 = 140
        // Saldo final: 14140
        assertEquals(14140, cuenta.getSaldo(), 0.01);
        assertEquals(1000, cuenta.getComisionMensual());
    }
}