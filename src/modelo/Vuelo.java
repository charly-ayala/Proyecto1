package modelo;

import java.util.ArrayList;
import java.util.List;

public class Vuelo {
    private static int contador = 1;
    private int id;
    private String origen;
    private String destino;
    private int capacidad;
    private int ocupados;
    private boolean cancelado;
    private String motivoCancelacion;
    private List<Ticket> tickets;

    public Vuelo(String origen, String destino, int capacidad) {
        this.id = contador++;
        this.origen = origen;
        this.destino = destino;
        this.capacidad = capacidad;
        this.ocupados = 0;
        this.cancelado = false;
        this.motivoCancelacion = null;
        this.tickets = new ArrayList<>();
    }

    public boolean estaDisponible(int lugares) {
        return !cancelado && (ocupados + lugares <= capacidad);
    }

    public Ticket reservar(Pasajero pasajero, int lugares) {
        if (!estaDisponible(lugares)) return null;
        ocupados += lugares;
        Ticket ticket = new Ticket(this, pasajero, lugares);
        tickets.add(ticket);
        return ticket;
    }

    public void cancelar(String motivo) {
        this.cancelado = true;
        this.motivoCancelacion = motivo;
    }

    public int getId() {
        return id;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getOcupados() {
        return ocupados;
    }

    public boolean isCancelado() {
        return cancelado;
    }

    public String getMotivoCancelacion() {
        return motivoCancelacion;
    }

    @Override
    public String toString() {
        String info = "Vuelo #" + id + " - " + origen + " → " + destino + 
                      " | Lugares: " + (capacidad - ocupados) + "/" + capacidad;
        if (cancelado) {
            info += " (CANCELADO - Motivo: " + motivoCancelacion + ")";
        }
        return info;
    }
}
