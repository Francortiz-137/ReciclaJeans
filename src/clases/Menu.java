package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private ArchivoServicio archivoServicio;
    private ProductoServicio productoServicio;

    public Menu() {
        scanner = new Scanner(System.in);
        archivoServicio = new ArchivoServicio();
        productoServicio = new ProductoServicio();
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("1 Listar Producto");
            System.out.println("2 Editar Datos");
            System.out.println("3 Importar Datos");
            System.out.println("4 Salir");
            System.out.print("Ingrese una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea

            switch (opcion) {
                case 1:
                    listarProductos();
                    break;
                case 2:
                    editarDatos();
                    break;
                case 3:
                    importarDatos();
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
        scanner.close();
    }

    private void listarProductos() {
        Utilidad.limpiarPantalla();
        if (productoServicio.getListaProductos().isEmpty()) {
            System.out.println("No hay productos en la lista.");
        } else {
            System.out.println("Lista de Productos:");
            productoServicio.listarProductos();
        }
    }

    private void editarDatos() {
        Utilidad.limpiarPantalla();
        System.out.print("Ingrese el código del producto a editar: ");
        String codigo = scanner.nextLine();
        Producto producto = buscarProductoPorCodigo(codigo);

        if (producto == null) {
            Utilidad.limpiarPantalla();
            System.out.println("Producto no encontrado.");
        } else {
            System.out.print("Ingrese el nuevo artículo (deje en blanco para mantener el actual): ");
            String nuevoArticulo = scanner.nextLine();
            if (!nuevoArticulo.isEmpty()) {
                producto.setArticulo(nuevoArticulo);
            }

            System.out.print("Ingrese el nuevo precio (deje en blanco para mantener el actual): ");
            String nuevoPrecio = scanner.nextLine();
            if (!nuevoPrecio.isEmpty()) {
                producto.setPrecio(nuevoPrecio);
            }

            System.out.print("Ingrese la nueva descripción (deje en blanco para mantener el actual): ");
            String nuevaDescripcion = scanner.nextLine();
            if (!nuevaDescripcion.isEmpty()) {
                producto.setDescripcion(nuevaDescripcion);
            }

            System.out.print("Ingrese el nuevo código (deje en blanco para mantener el actual): ");
            String nuevoCodigo = scanner.nextLine();
            if (!nuevoCodigo.isEmpty()) {
                producto.setCodigo(nuevoCodigo);
            }

            System.out.print("Ingrese la nueva talla (deje en blanco para mantener la actual): ");
            String nuevaTalla = scanner.nextLine();
            if (!nuevaTalla.isEmpty()) {
                producto.setTalla(nuevaTalla);
            }

            System.out.print("Ingrese la nueva marca (deje en blanco para mantener la actual): ");
            String nuevaMarca = scanner.nextLine();
            if (!nuevaMarca.isEmpty()) {
                producto.setMarca(nuevaMarca);
            }

            System.out.print("Ingrese el nuevo color (deje en blanco para mantener el actual): ");
            String nuevoColor = scanner.nextLine();
            if (!nuevoColor.isEmpty()) {
                producto.setColor(nuevoColor);
            }

            System.out.println("Producto actualizado con éxito.");
        }
    }

    private Producto buscarProductoPorCodigo(String codigo) {
        Producto producto = productoServicio.getListaProductos().stream().filter(p -> p.getCodigo().equals(codigo)).findFirst().orElse(null);
        return producto;
    }

    private Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productoServicio.getListaProductos()) {
            if (producto.getArticulo().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    private void importarDatos() {
        Utilidad.limpiarPantalla();
        System.out.print("Ingrese el nombre del archivo CSV: ");
        String nombreArchivo =  "src/archivos/" + scanner.nextLine();
        productoServicio.setListaProductos(archivoServicio.cargarDatos(nombreArchivo));
        System.out.println("Datos importados desde " + nombreArchivo);
        productoServicio.listarProductos();
    }

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.mostrarMenu();
    }
}
