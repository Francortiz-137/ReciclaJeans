package clases;

import java.io.*;
import java.util.ArrayList;

public class ArchivoServicio {
    public ArrayList<Producto> cargarDatos(String nombreArchivo){
        //importar lista de productos
        ArrayList<Producto> listaProductos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                String articulo = datos[0];
                String precio = datos[1];
                String descripcion = datos[2];
                String codigo = datos[3];
                String talla = datos[4];
                String marca = datos[5];
                String color = datos[6];
                Producto producto = new Producto(articulo,precio,descripcion,codigo,talla,marca,color);
                listaProductos.add(producto);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaProductos;
    }
}
