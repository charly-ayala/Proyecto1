package app;

import sistema.SistemaReservas;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaReservas sistema = new SistemaReservas();
        Scanner sc = new Scanner(System.in);

        int opcion;
        do {
            System.out.println("\n--- Bienvenido a Aerolínea Patito Volador ---");
            System.out.println("1. Iniciar sesión como Empleado");
            System.out.println("2. Iniciar sesión como Pasajero");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> menuLoginEmpleado(sistema, sc);
                case 2 -> menuLoginPasajero(sistema, sc);
                case 3 -> System.out.println("Gracias por usar el sistema.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 3);
    }

    private static void menuLoginEmpleado(SistemaReservas sistema, Scanner sc) {
        System.out.println("\n1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt(); sc.nextLine();

        if (opcion == 1) {
            sistema.iniciarSesionEmpleado(sc);
        } else if (opcion == 2) {
            sistema.registrarEmpleado(sc);
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private static void menuLoginPasajero(SistemaReservas sistema, Scanner sc) {
        System.out.println("\n1. Iniciar sesión");
        System.out.println("2. Registrarse");
        System.out.print("Seleccione una opción: ");
        int opcion = sc.nextInt(); sc.nextLine();

        if (opcion == 1) {
            sistema.iniciarSesionPasajero(sc);
        } else if (opcion == 2) {
            sistema.registrarPasajero(sc);
        } else {
            System.out.println("Opción inválida.");
        }
    }
}
