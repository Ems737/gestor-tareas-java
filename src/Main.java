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
    private final String archivo = "tareas.txt";

    public GestorTareas() {
        tareas = new ArrayList<>();
	cargarDesdeArchivo();
    }

    public void agregarTarea(String descripcion) {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            System.out.println("La descripción no puede estar vacía.");
            return;
        }
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

    public void guardarEnArchivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivo))) {
            for (Tarea tarea : tareas) {
                writer.write(tarea.getDescripcion() + ";" + tarea.isCompletada());
                writer.newLine();
            }
            System.out.println("Tareas guardadas en archivo.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }

    public void cargarDesdeArchivo() {
        File f = new File(archivo);
        if (!f.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 2) {
                    Tarea t = new Tarea(partes[0]);
                    if (Boolean.parseBoolean(partes[1])) {
                        t.marcarCompletada();
                    }
                    tareas.add(t);
                }
            }
            System.out.println("Tareas cargadas desde archivo.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
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
	    System.out.println("5. Guardar tareas en archivo");
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
                case 5:
                    gestor.guardarEnArchivo();
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
