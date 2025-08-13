package dev.lin.views;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.github.stefanb.junit5.system.rules.SystemIn;
import com.github.stefanb.junit5.system.rules.SystemOut;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith({SystemIn.class, SystemOut.class})
class MenuTest {

    @Test
    void testMenuFlow(SystemIn systemIn, SystemOut systemOut) {
        // GIVEN: Una instancia de Menu y la entrada simulada
        Menu menu = new Menu();

        // 1. Elegir Cuenta de Ahorros, 2. Saldo, 3. Tasa, 4. Consignar, 5. Cantidad, 6. Mostrar info, 7. Salir
        systemIn.provideLines("1", "15000", "12", "1", "2000", "4", "5");

        // WHEN: Se inicia el menú
        menu.iniciar();

        // THEN: La salida de la consola debe coincidir con el flujo esperado
        String expectedOutput = """
                Bienvenido al sistema de Cuentas Bancarias.
                Seleccione el tipo de cuenta a crear:
                1. Cuenta de Ahorros
                2. Cuenta Corriente
                Ingrese el saldo inicial: Ingrese la tasa anual (en porcentaje): ✅ Cuenta de Ahorros creada con éxito.

                --- Menú Principal ---
                1. Consignar dinero
                2. Retirar dinero
                3. Realizar extracto mensual
                4. Mostrar información de la cuenta
                5. Salir
                Seleccione una opción: Ingrese la cantidad a consignar: ✅ Consignación realizada.

                --- Menú Principal ---
                1. Consignar dinero
                2. Retirar dinero
                3. Realizar extracto mensual
                4. Mostrar información de la cuenta
                5. Salir
                Seleccione una opción: --- Información de Cuenta de Ahorros ---
                Saldo: 17000.0
                Comisión mensual: 0.0
                Número de transacciones: 1

                --- Menú Principal ---
                1. Consignar dinero
                2. Retirar dinero
                3. Realizar extracto mensual
                4. Mostrar información de la cuenta
                5. Salir
                Seleccione una opción: ¡Gracias por usar nuestro servicio!
                """;

        assertEquals(expectedOutput.strip(), systemOut.getLog().strip());
    }
}