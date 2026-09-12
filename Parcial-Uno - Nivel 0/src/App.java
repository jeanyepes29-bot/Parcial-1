import java.util.List;
import java.util.Scanner;

import domain.Ahorro;
import domain.Corriente;
import domain.Cuenta;
import service.ServiceCuenta;

public class App {
    public static void main(String[] args) throws Exception {
        ServiceCuenta serviceCuenta = new ServiceCuenta();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("\n===== MENU CUENTAS =====");
            System.out.println("a. Listar todas las cuentas Ahorro");
            System.out.println("b. Listar todas las cuentas Corriente");
            System.out.println("c. Crear cuenta de Ahorro");
            System.out.println("d. Crear cuenta Corriente");
            System.out.println("e. Obtener informacion de una cuenta por numero");
            System.out.println("f. Retirar dinero");
            System.out.println("g. Depositar dinero");
            System.out.println("h. Salir");
            System.out.print("Seleccione una opcion: ");
            String opcion = sc.nextLine().trim().toLowerCase();

            switch (opcion) {
                case "a":
                    listarAhorros(serviceCuenta);
                    break;

                case "b":
                    listarCorrientes(serviceCuenta);
                    break;

                case "c":
                    crearAhorro(sc, serviceCuenta);
                    break;

                case "d":
                    crearCorriente(sc, serviceCuenta);
                    break;

                case "e":
                    obtenerPorNumero(sc, serviceCuenta);
                    break;

                case "f":
                    retirarDinero(sc, serviceCuenta);
                    break;

                case "g":
                    depositarDinero(sc, serviceCuenta);
                    break;

                case "h":
                    salir = true;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opcion invalida.");
            }
        }

        sc.close();
    }

    private static void listarAhorros(ServiceCuenta serviceCuenta) {
        List<Cuenta> cuentas = serviceCuenta.obtenerCuentas();
        System.out.println("\n--- Cuentas de Ahorro ---");
        for (Cuenta c : cuentas) {
            if (c instanceof Ahorro) {
                System.out.println(c);
            }
        }
    }

    private static void listarCorrientes(ServiceCuenta serviceCuenta) {
        List<Cuenta> cuentas = serviceCuenta.obtenerCuentas();
        System.out.println("\n--- Cuentas Corriente ---");
        for (Cuenta c : cuentas) {
            if (c instanceof Corriente) {
                System.out.println(c);
            }
        }
    }

    private static void crearAhorro(Scanner sc, ServiceCuenta serviceCuenta) {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("DNI cliente: ");
        long dni = Long.parseLong(sc.nextLine());
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine());
        System.out.print("Fecha de creacion: ");
        String fecha = sc.nextLine();

        Ahorro ahorro = new Ahorro(numeroCuenta, dni, saldo, fecha);
        boolean creada = serviceCuenta.crearCuenta(ahorro);
        System.out.println(creada ? "Cuenta creada con exito." : "Ya existe una cuenta con ese numero.");
    }

    private static void crearCorriente(Scanner sc, ServiceCuenta serviceCuenta) {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("DNI cliente: ");
        long dni = Long.parseLong(sc.nextLine());
        System.out.print("Saldo inicial: ");
        double saldo = Double.parseDouble(sc.nextLine());
        System.out.print("Impuesto: ");
        double impuesto = Double.parseDouble(sc.nextLine());

        Corriente corriente = new Corriente(numeroCuenta, dni, saldo, impuesto);
        boolean creada = serviceCuenta.crearCuenta(corriente);
        System.out.println(creada ? "Cuenta creada con exito." : "Ya existe una cuenta con ese numero.");
    }

    private static void obtenerPorNumero(Scanner sc, ServiceCuenta serviceCuenta) {
        System.out.print("Numero de cuenta a buscar: ");
        String numeroCuenta = sc.nextLine();
        Cuenta cuenta = serviceCuenta.obtenernumeroCuenta(numeroCuenta);
        System.out.println(cuenta != null ? cuenta : "No existe una cuenta con ese numero.");
    }

    private static void retirarDinero(Scanner sc, ServiceCuenta serviceCuenta) {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("Monto a retirar: ");
        double monto = Double.parseDouble(sc.nextLine());
        boolean exito = serviceCuenta.retirarDinero(numeroCuenta, monto);
        System.out.println(exito ? "Retiro exitoso." : "No se pudo retirar (cuenta inexistente o saldo insuficiente).");
    }

    private static void depositarDinero(Scanner sc, ServiceCuenta serviceCuenta) {
        System.out.print("Numero de cuenta: ");
        String numeroCuenta = sc.nextLine();
        System.out.print("Monto a depositar: ");
        double monto = Double.parseDouble(sc.nextLine());
        boolean exito = serviceCuenta.ingresarDinero(numeroCuenta, monto);
        System.out.println(exito ? "Deposito exitoso." : "No se pudo depositar (cuenta inexistente).");
    }
}
