package modelo;

public class Ticket {
    private Vuelo vuelo;
    private Pasajero pasajero;
    private int cantidadLugares;

    public Ticket(Vuelo vuelo, Pasajero pasajero, int cantidadLugares) {
        this.vuelo = vuelo;
        this.pasajero = pasajero;
        this.cantidadLugares = cantidadLugares;
    }

    public void imprimir() {
        System.out.println("----- Ticket de Reserva -----");
        System.out.println("Pasajero: " + pasajero.getNombre());
        System.out.println("Vuelo: " + vuelo.getOrigen() + " → " + vuelo.getDestino());
        System.out.println("Lugares reservados: " + cantidadLugares);
        System.out.println("-----------------------------");
    }
}
