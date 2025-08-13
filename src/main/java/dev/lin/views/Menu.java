package dev.lin.views;

import dev.lin.models.Cuenta;
import dev.lin.models.CuentaAhorros;
import dev.lin.models.CuentaCorriente;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Cuenta cuenta;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("Le damos la bienvenida a nuestro sistema de cuentas bancarias.");
        crearCuenta();
        mostrarMenuPrincipal();
    }

    private void crearCuenta() {
        System.out.println("Seleccione el tipo de cuenta a crear:");
        System.out.println("1. Cuenta de Ahorros");
        System.out.println("2. Cuenta Corriente");

        int opcion = leerOpcion();
        System.out.print("Ingrese el saldo inicial: ");
        float saldo = scanner.nextFloat();
        System.out.print("Ingrese la tasa anual (en porcentaje): ");
        float tasaAnual = scanner.nextFloat();

        if (opcion == 1) {
            this.cuenta = new CuentaAhorros(saldo, tasaAnual);
            System.out.println("Cuenta de Ahorros creada con éxito.");
        } else if (opcion == 2) {
            this.cuenta = new CuentaCorriente(saldo, tasaAnual);
            System.out.println("Cuenta Corriente creada con éxito.");
        } else {
            System.out.println("Opción no válida. Se creará una cuenta de Ahorros por defecto.");
            this.cuenta = new CuentaAhorros(saldo, tasaAnual);
        }
        scanner.nextLine(); // Consumir el salto de línea pendiente
    }

    private void mostrarMenuPrincipal() {
        int opcion = 0;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Consignar dinero");
            System.out.println("2. Retirar dinero");
            System.out.println("3. Realizar extracto mensual");
            System.out.println("4. Mostrar información de la cuenta");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = leerOpcion();
            procesarOpcion(opcion);

        } while (opcion != 5);

        System.out.println("Gracias por confiar en nuestro banco.");
    }

    private int leerOpcion() {
        try {
            int opcion = scanner.nextInt();
            return opcion;
        } catch (InputMismatchException e) {
            scanner.nextLine(); // Limpiar el buffer del scanner
            return -1; // Valor no válido
        }
    }

    private void procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                System.out.print("Ingrese la cantidad a consignar: ");
                float cantidadConsignar = scanner.nextFloat();
                cuenta.consignar(cantidadConsignar);
                System.out.println("Consignación realizada.");
                break;
            case 2:
                System.out.print("Ingrese la cantidad a retirar: ");
                float cantidadRetirar = scanner.nextFloat();
                cuenta.retirar(cantidadRetirar);
                System.out.println("etiro realizado.");
                break;
            case 3:
                cuenta.extractoMensual();
                System.out.println("Extracto mensual realizado.");
                break;
            case 4:
                System.out.println(cuenta.imprimir());
                break;
            case 5:
                break;
            default:
                System.out.println("Opción no válida, inténtelo de nuevo.");
                break;
        }
    }
}
