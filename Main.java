import dao.ActorDAO;
import Model.Actor;

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
            System.out.println("4. Salir");
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
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 4);
    }

    private static void agregarActor() {
        System.out.print("Ingrese ID del Actor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer del scanner
        System.out.print("Ingrese nombre del Actor: ");
        String nombre = scanner.nextLine();

        Actor nuevoActor = new Actor(id, nombre);
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
}
