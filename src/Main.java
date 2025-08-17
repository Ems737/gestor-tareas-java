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

class GestorTareas {
    private ArrayList<Tarea> tareas;

    public GestorTareas() {
        tareas = new ArrayList<>();
    }

    public void agregarTarea(String descripcion) {
        tareas.add(new Tarea(descripcion));
        System.out.println("Tarea agregada correctamente.");
    }

    public void listarTareas() {
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        for (int i = 0; i < tareas.size(); i++) {
            System.out.println((i + 1) + ". " + tareas.get(i));
        }
    }

    public void marcarCompletada(int indice) {
        if (indice >= 0 && indice < tareas.size()) {
            tareas.get(indice).marcarCompletada();
            System.out.println("Tarea marcada como completada.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    public void eliminarCompletadas() {
        tareas.removeIf(Tarea::isCompletada);
        System.out.println("Tareas completadas eliminadas.");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        int opcion;

        do {
            System.out.println("\n--- MENÚ DE GESTIÓN DE TAREAS ---");
            System.out.println("1. Agregar tarea");
            System.out.println("2. Listar tareas");
            System.out.println("3. Marcar tarea como completada");
            System.out.println("4. Eliminar tareas completadas");
            System.out.println("0. Salir");
            System.out.print("Elige una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Descripción de la tarea: ");
                    String desc = scanner.nextLine();
                    gestor.agregarTarea(desc);
                    break;
                case 2:
                    gestor.listarTareas();
                    break;
                case 3:
                    gestor.listarTareas();
                    System.out.print("Número de tarea a completar: ");
                    int num = scanner.nextInt();
                    gestor.marcarCompletada(num - 1);
                    break;
                case 4:
                    gestor.eliminarCompletadas();
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);

        scanner.close();
    }
}
	
