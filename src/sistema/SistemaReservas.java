package sistema;

import modelo.*;

import java.util.*;

public class SistemaReservas {
    private List<Empleado> empleados;
    private List<Pasajero> pasajeros;
    private List<Vuelo> vuelos;

    public SistemaReservas() {
        empleados = new ArrayList<>();
        pasajeros = new ArrayList<>();
        vuelos = new ArrayList<>();
    }

    public void registrarEmpleado(Scanner sc) {
        System.out.println("Registro de Empleado");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        empleados.add(new Empleado(nombre, usuario, contrasena));
        System.out.println("Empleado registrado exitosamente.");
    }

    public void iniciarSesionEmpleado(Scanner sc) {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        for (Empleado e : empleados) {
            if (e.iniciarSesion(usuario, contrasena)) {
                System.out.println("Bienvenido " + e.getNombre());
                menuEmpleado(sc, e);
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
    }

    public void registrarPasajero(Scanner sc) {
        System.out.println("Registro de Pasajero");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();
        pasajeros.add(new Pasajero(nombre, usuario, contrasena));
        System.out.println("Pasajero registrado exitosamente.");
    }

    public void iniciarSesionPasajero(Scanner sc) {
        System.out.print("Usuario: ");
        String usuario = sc.nextLine();
        System.out.print("Contraseña: ");
        String contrasena = sc.nextLine();

        for (Pasajero p : pasajeros) {
            if (p.iniciarSesion(usuario, contrasena)) {
                System.out.println("Bienvenido " + p.getNombre());
                menuPasajero(sc, p);
                return;
            }
        }
        System.out.println("Credenciales incorrectas.");
    }

    private void menuEmpleado(Scanner sc, Empleado empleado) {
        int opcion;
        do {
            System.out.println("\n--- Menú Empleado ---");
            System.out.println("1. Crear vuelo");
            System.out.println("2. Cancelar vuelo");
            System.out.println("3. Ver todos los vuelos");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> crearVuelo(sc);
                case 2 -> cancelarVuelo(sc);
                case 3 -> mostrarVuelos();
                case 4 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private void menuPasajero(Scanner sc, Pasajero pasajero) {
        int opcion;
        do {
            System.out.println("\n--- Menú Pasajero ---");
            System.out.println("1. Ver vuelos disponibles");
            System.out.println("2. Buscar vuelos por origen y destino");
            System.out.println("3. Reservar vuelo");
            System.out.println("4. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt(); sc.nextLine();

            switch (opcion) {
                case 1 -> mostrarVuelosDisponibles();
                case 2 -> buscarVuelos(sc);
                case 3 -> reservarVuelo(sc, pasajero);
                case 4 -> System.out.println("Sesión cerrada.");
                default -> System.out.println("Opción inválida.");
            }
        } while (opcion != 4);
    }

    private void crearVuelo(Scanner sc) {
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = sc.nextInt(); sc.nextLine();
        vuelos.add(new Vuelo(origen, destino, capacidad));
        System.out.println("Vuelo creado exitosamente.");
    }

    private void cancelarVuelo(Scanner sc) {
        mostrarVuelos();
        System.out.print("Ingrese ID del vuelo a cancelar: ");
        int id = sc.nextInt(); sc.nextLine();

        for (Vuelo v : vuelos) {
            if (v.getId() == id) {
                System.out.print("Motivo de la cancelación: ");
                String motivo = sc.nextLine();
                v.cancelar(motivo);
                System.out.println("Vuelo cancelado con motivo: " + motivo);
                return;
            }
        }
        System.out.println("ID de vuelo no encontrado.");
    }

    private void mostrarVuelos() {
        System.out.println("\n--- Todos los Vuelos ---");
        for (Vuelo v : vuelos) {
            System.out.println(v);
        }
    }

    private void mostrarVuelosDisponibles() {
        System.out.println("\n--- Vuelos (incluye cancelados) ---");
        for (Vuelo v : vuelos) {
            System.out.println(v);
        }
    }

    private void buscarVuelos(Scanner sc) {
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();

        System.out.println("\n--- Resultados de búsqueda ---");
        for (Vuelo v : vuelos) {
            if (v.getOrigen().equalsIgnoreCase(origen) &&
                v.getDestino().equalsIgnoreCase(destino)) {
                System.out.println(v);
            }
        }
    }

    private void reservarVuelo(Scanner sc, Pasajero pasajero) {
        mostrarVuelosDisponibles();
        System.out.print("Ingrese ID del vuelo a reservar: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("¿Cuántos lugares desea reservar?: ");
        int cantidad = sc.nextInt(); sc.nextLine();

        for (Vuelo v : vuelos) {
            if (v.getId() == id) {
                Ticket ticket = v.reservar(pasajero, cantidad);
                if (ticket != null) {
                    System.out.println("Reserva exitosa:");
                    ticket.imprimir();
                } else {
                    System.out.println("No se pudo realizar la reserva (vuelo lleno o cancelado).");
                }
                return;
            }
        }
        System.out.println("Vuelo no encontrado.");
    }
}
