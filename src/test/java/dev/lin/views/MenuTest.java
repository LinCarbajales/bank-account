package dev.lin.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class MenuTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;

    @BeforeEach
    void setUp() {
        // Redirigir System.out para capturar la salida
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        // Restaurar System.out y System.in originales
        System.setOut(originalOut);
        System.setIn(originalIn);
    }

    private void setInput(String input) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));
    }

    private String getOutput() {
        return outContent.toString();
    }

    @Test
    void testCrearCuentaAhorros() {
        // Simular entrada: opción 1 (Cuenta Ahorros), saldo 15000, tasa 5%, salir
        setInput("1\n15000\n5\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("Cuenta de Ahorros creada con éxito"));
        assertTrue(output.contains("bienvenida"));
    }

    @Test
    void testCrearCuentaCorriente() {
        // Simular entrada: opción 2 (Cuenta Corriente), saldo 20000, tasa 4%, salir
        setInput("2\n20000\n4\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("Cuenta Corriente creada con éxito"));
    }

    @Test
    void testCrearCuentaOpcionInvalida() {
        // Simular entrada: opción inválida (3), saldo 10000, tasa 3%, salir
        setInput("3\n10000\n3\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("no válida") || output.contains("defecto"));
    }

    @Test
    void testConsignarDinero() {
        // Crear cuenta de ahorros, consignar 5000, mostrar info, salir
        setInput("1\n15000\n5\n1\n5000\n4\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("realizada") || output.contains("Consignación"));
        assertTrue(output.contains("20000")); // 15000 + 5000
    }

    @Test
    void testRetirarDinero() {
        // Crear cuenta corriente, retirar 3000, mostrar info, salir
        setInput("2\n10000\n4\n2\n3000\n4\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("realizado") || output.contains("etiro"));
        assertTrue(output.contains("7000")); // 10000 - 3000
    }

    @Test
    void testExtractoMensual() {
        // Crear cuenta de ahorros, hacer extracto mensual, salir
        setInput("1\n15000\n12\n3\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("Extracto mensual realizado"));
    }

    @Test
    void testMostrarInformacionCuenta() {
        // Crear cuenta de ahorros y mostrar información
        setInput("1\n15000\n5\n4\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("Información") || output.contains("Saldo"));
        assertTrue(output.contains("15000"));
    }

    @Test
    void testOpcionInvalidaEnMenuPrincipal() {
        // Crear cuenta, seleccionar opción inválida, luego salir
        setInput("1\n10000\n5\n9\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("no válida") || output.contains("inválida"));
    }

    @Test
    void testSalirDelPrograma() {
        // Crear cuenta y salir inmediatamente
        setInput("1\n10000\n5\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("Gracias") || output.contains("banco"));
    }

    @Test
    void testEntradaInvalidaEnOpcion() {
        // Simular entrada no numérica y luego salir
        setInput("1\n10000\n5\nabc\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("no válida") || output.contains("inválida"));
    }

    @Test
    void testCuentaAhorrosInactiva() {
        // Crear cuenta con saldo menor a 10000 (inactiva), intentar consignar
        setInput("1\n5000\n5\n1\n1000\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        // Verificar que se creó la cuenta correctamente
        assertTrue(output.contains("éxito") || output.contains("creada"));
    }

    @Test
    void testSobregiroCuentaCorriente() {
        // Crear cuenta corriente y retirar más del saldo disponible
        setInput("2\n5000\n4\n2\n7000\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("realizado") || output.contains("etiro"));
    }

    @Test
    void testFlujoBasico() {
        // Prueba básica: crear cuenta, hacer una operación y salir
        setInput("1\n15000\n12\n1\n2000\n5\n");
        
        Menu menu = new Menu();
        menu.iniciar();
        
        String output = getOutput();
        assertTrue(output.contains("bienvenida") || output.contains("Bienvenida"));
        assertTrue(output.contains("éxito") || output.contains("creada"));
        assertTrue(output.contains("realizada") || output.contains("Consignación"));
        assertTrue(output.contains("Gracias") || output.contains("banco"));
    }
}