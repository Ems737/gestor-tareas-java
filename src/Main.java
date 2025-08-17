import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Tarea implements Serializable {
    private String descripcion;
    private boolean completada;

    public Tarea(String descripcion) {
        this.descripcion = descripcion;
        this.completada = false;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public boolean isCompletada() {
        return completada;
    }

    public void marcarCompletada() {
        this.completada = true;
    }

    @Override
    public String toString() {
        return descripcion + " [" + (completada ? "Completada" : "Pendiente") + "]";
    }
}

public class Main {
    public static void main(String[] args) {
//Creando una tarea
	Tarea uno = new Tarea("Primera descripcion");
//Devolviendo la tarea
    String descripcion = uno.getDescripcion();
	System.out.println("Muestro descripcion del objeto:");
    System.out.println(descripcion);
    }
}
	
