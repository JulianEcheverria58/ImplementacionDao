import dao.ActorDAO;
import model.Actor;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ActorDAO actorDAO = new ActorDAO();

    public static void main(String[] args) {
        int opcion;
        do {
            System.out.println("== Menú ==");
            System.out.println("1. Agregar Actor");
            System.out.println("2. Listar Actores");
            System.out.println("3. Eliminar Actor");
            System.out.println("4. Descargar Actores a Archivo TXT");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer del scanner

            switch (opcion) {
                case 1:
                    agregarActor();
                    break;
                case 2:
                    listarActores();
                    break;
                case 3:
                    eliminarActor();
                    break;
                case 4:
                    descargarActores();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 5);
    }

    private static void agregarActor() {
        System.out.print("Ingrese ID del Actor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese nombre del Actor: ");
        String nombre = scanner.nextLine();

        Actor nuevoActor = new Actor(id, nombre); // Crear Actor del modelo
        actorDAO.insertar(nuevoActor);
        System.out.println("Actor agregado correctamente.");
    }

    private static void listarActores() {
        List<Actor> actores = actorDAO.listar();
        if (actores.isEmpty()) {
            System.out.println("No hay actores en la lista.");
        } else {
            System.out.println("== Lista de Actores ==");
            for (Actor actor : actores) {
                System.out.println(actor);
            }
        }
    }

    private static void eliminarActor() {
        System.out.print("Ingrese el ID del Actor a eliminar: ");
        int id = scanner.nextInt();

        List<Actor> actores = actorDAO.listar();
        Actor actorAEliminar = null;
        for (Actor actor : actores) {
            if (actor.getId() == id) {
                actorAEliminar = actor;
                break;
            }
        }

        if (actorAEliminar != null) {
            actorDAO.eliminar(actorAEliminar);
            System.out.println("Actor eliminado correctamente.");
        } else {
            System.out.println("No se encontró un actor con ese ID.");
        }
    }

    private static void descargarActores() {
        List<Actor> actores = actorDAO.listar();
        if (actores.isEmpty()) {
            System.out.println("No hay actores para descargar.");
            return;
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Actores");
        fileChooser.setFileFilter(new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt"));
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            if (!filePath.toLowerCase().endsWith(".txt")) {
                filePath += ".txt";
            }

            try (FileWriter writer = new FileWriter(filePath)) {
                for (Actor actor : actores) {
                    writer.write(actor.getId() + "," + actor.getNombre() + "\n");
                }
                System.out.println("Actores descargados en: " + filePath);
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo.");
            }
        }
    }
}
