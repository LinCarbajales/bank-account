## Sistema de Cuentas Bancarias

Este proyecto es una implementación de un sistema de gestión de cuentas bancarias en un lenguaje de programación orientado a objetos. El objetivo principal es modelar una jerarquía de clases para cuentas bancarias, incluyendo una clase madre (Cuenta) y dos clases que heredan de ella (CuentaAhorros y CuentaCorriente).

## Clases

### Models

Cuenta (Clase Base): Atributos protegidos como saldo, num_consignaciones, num_retiros, tasa_anual y comision_mensual. Incluye métodos para consignar, retirar, calcular interés mensual, generar extracto mensual e imprimir los atributos.

CuentaAhorros (Clase Hija): Posee un atributo activa (booleano). La cuenta se considera activa si el saldo es mayor o igual a $10000. Redefine los métodos de la clase base para consignar y retirar solo si la cuenta está activa. El método de extracto mensual cobra una comisión de $1000 por cada retiro adicional después del cuarto. El método imprimir muestra el saldo, la comisión y el total de transacciones.

CuentaCorriente (Clase Hija): Incluye un atributo sobregiro que se inicializa en cero. Redefine el método retirar permitiendo retiros que superen el saldo, registrando el exceso como sobregiro. El método consignar también se redefine para reducir el sobregiro existente. El método imprimir muestra el saldo, la comisión, el total de transacciones y el valor del sobregiro.

### Views

Menu: La vista a través de la que el ususario recibe solicitudes de inputs y la información en consola.

<img width="611" height="310" alt="DiagramaCuentaBancaria" src="https://github.com/user-attachments/assets/d2e48161-6931-4c32-ac29-321d2cd667d6" />

## Testing

94,76% de cobertura de testing.

<img width="221" height="151" alt="TestCoverageCuentaBancaria" src="https://github.com/user-attachments/assets/005574b3-2ca7-48f6-a863-c72df0d8899f" />



