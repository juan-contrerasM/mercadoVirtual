package co.edu.uniquindio.mercado.utils;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/

import co.edu.uniquindio.mercado.controller.ModelFactoryController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.*;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class Persistencia {


    //--------------------------------------RUTAS----------------------------------------
    public static final String RUTA_ARCHIVO_VENDEDORES = "mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/vendedores";
    public static final String RUTA_ARCHIVO_ADMINISTRADOR = "mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/administrador";
    public static final String RUTA_ARCHIVO_LOG = "mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/log";
    public static  final  String RUTA_ARCHIVO_PRODUCTO="mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/productos";
    private static final String RUTA_ARCHIVO_PUBLICACIONES ="mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/publicaciones";

    private static  final String RUTA_ARCHVIO_CODIGOPRODCUTO="src/main/resources/co/edu/uniquindio/mercado/archivos/codigoProducto";
    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param
     * @throws IOException
     */
    ModelFactoryController modelFactoryController = new ModelFactoryController();
//-------------------------------------------GUARDAR ARCHIVOS------------------------------
    //prodcutos
    public static void guardarVendedores(ListaSimple<Vendedor> listaVendedores) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        Vendedor vendedor = new Vendedor();
        for (int i = 0; i < listaVendedores.getTamanio(); i++) {
            vendedor=listaVendedores.obtenerValorNodo(i);
            contenido += vendedor.getNombreUsuario() + "--" + vendedor.getContrasenia() + "--" + vendedor.getNombre() + "--" +
                    vendedor.getEdad() + "--" + vendedor.getCorreo() + "--" + vendedor.getNumeroCelular() + "--" + vendedor.getTipoUsuario() +
                    "--" + vendedor.getCedula() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_VENDEDORES, contenido, false);
    }
    public static void guardarAdministrador(ListaDoble<Administrador> listaAdministrador) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        Administrador administrador = new Administrador();
        for (int i = 0; i < listaAdministrador.getTamanio(); i++) {
            administrador=listaAdministrador.obtenerValorNodo(i);
            contenido += administrador.getNombreUsuario() + "--" + administrador.getContrasenia() + "--" + administrador.getNombre() + "--" +
                    administrador.getEdad() + "--" + administrador.getCorreo() + "--" + administrador.getNumeroCelular() + "--" + administrador.getTipoUsuario() +
                    "--" + administrador.getCedula() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ADMINISTRADOR, contenido, false);
    }
    public static void guardarProductos(ListaDoble<Administrador> listaAdministrador) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        Administrador administrador = new Administrador();
        for (int i = 0; i < listaAdministrador.getTamanio(); i++) {
            administrador=listaAdministrador.obtenerValorNodo(i);
            contenido += administrador.getNombreUsuario() + "--" + administrador.getContrasenia() + "--" + administrador.getNombre() + "--" +
                    administrador.getEdad() + "--" + administrador.getCorreo() + "--" + administrador.getNumeroCelular() + "--" + administrador.getTipoUsuario() +
                    "--" + administrador.getCedula() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ADMINISTRADOR, contenido, false);
    }

    public  static  void guadrarCodigoProducto(int codigo) throws IOException {
        StringBuilder contenido= new StringBuilder();
        contenido.append(codigo);
        ArchivoUtil.guardarArchivo(RUTA_ARCHVIO_CODIGOPRODCUTO,contenido.toString(),false);
    }



    // Método para guardar publicaciones en un archivo de texto
    public static void guardarPublicaciones(TreeMap<String, Publicacion> publicaciones) throws IOException {
        StringBuilder contenido = new StringBuilder();

        for (Publicacion publicacion : publicaciones.values()) {
            contenido.append(publicacion.getTitulo()).append("--")
                    .append(publicacion.getDescripcion()).append("--")
                    .append(publicacion.getProducto().getNombre()).append("--")
                    .append(publicacion.getProducto().getId()).append("--")
                    .append(publicacion.getProducto().getPrecio()).append("--")
                    .append(publicacion.getProducto().getUrlImagen()).append("--")
                    .append(publicacion.getVendedor().getNombreUsuario()).append("--")
                    .append(publicacion.getHoraPublicacion()).append("--")
                    .append(publicacion.getDiaPublicado()).append("--")
                    .append(publicacion.getContadorComentarios()).append("--")
                    .append(publicacion.getVisualizacion()).append("--")
                    .append(publicacion.getContadorMegusta()).append("\n");
        }

        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PUBLICACIONES, contenido.toString(), false);
    }


//	--------------------------------------------CARGAR ARCHIVOS----------------------------------------------------------

    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

    // Método para cargar publicaciones desde un archivo de texto y devolver un TreeMap
    public static TreeMap<String, Publicacion> cargarPublicaciones() throws IOException {
        TreeMap<String, Publicacion> publicaciones = new TreeMap<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PUBLICACIONES);

        for (String linea : contenido) {
            String[] partes = linea.split("--");
            Publicacion publicacion = new Publicacion();
            publicacion.setTitulo(partes[0]);
            publicacion.setDescripcion(partes[1]);

            Producto producto = new Producto();
            producto.setNombre(partes[2]);
            producto.setId(Integer.parseInt(partes[3]));
            producto.setPrecio(partes[4]);
            producto.setUrlImagen(partes[5]);

            publicacion.setProducto(producto);

            // Crear e inicializar el vendedor asociado a la publicación
            Vendedor vendedor = new Vendedor();
            vendedor.setNombreUsuario(partes[6]);
            publicacion.setVendedor(vendedor);

            publicacion.setHoraPublicacion(LocalTime.parse(partes[7]));
            publicacion.setDiaPublicado(LocalDate.parse(partes[8]));
            publicacion.setContadorComentarios(Integer.parseInt(partes[9]));
            publicacion.setVisualizacion(Integer.parseInt(partes[10]));
            publicacion.setContadorMegusta(Integer.parseInt(partes[11]));
            HashSet<Megusta> listMegusta=new HashSet<>();
            publicacion.setListMegusta(listMegusta);

            publicaciones.put(partes[0], publicacion);
        }

        return publicaciones;
    }
    public static int cargarCodigo() throws IOException {
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHVIO_CODIGOPRODCUTO);
        if (null != contenido){
            return Integer.parseInt(contenido.get(0));
        }
        else {
            return 0;
        }
    }

    public static ListaSimple<Vendedor> cargarVendedores() throws FileNotFoundException, IOException {
        ListaSimple<Vendedor> listaVendedores = new ListaSimple<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_VENDEDORES);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Vendedor vendedor = new Vendedor();
            vendedor.setNombreUsuario(linea.split("--")[0]);
            vendedor.setContrasenia(linea.split("--")[1]);
            vendedor.setNombre(linea.split("--")[2]);
            vendedor.setEdad(Integer.parseInt(linea.split("--")[3]));
            vendedor.setCorreo(linea.split("--")[4]);
            vendedor.setNumeroCelular(linea.split("--")[5]);
            vendedor.setTipoUsuario(TipoUsuario.valueOf(linea.split("--")[6]));
            vendedor.setCedula(linea.split("--")[7]);

            listaVendedores.agregarfinal(vendedor);
        }


        return listaVendedores;
    }

    public static ListaDoble<Administrador> cargarAdministradores() throws FileNotFoundException, IOException {
        ListaDoble<Administrador> listaVendedores = new ListaDoble<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ADMINISTRADOR);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            Administrador administrador = new Administrador();
            administrador.setNombreUsuario(linea.split("--")[0]);
            administrador.setContrasenia(linea.split("--")[1]);
            administrador.setNombre(linea.split("--")[2]);
            administrador.setEdad(Integer.parseInt(linea.split("--")[3]));
            administrador.setCorreo(linea.split("--")[4]);
            administrador.setNumeroCelular(linea.split("--")[5]);
            administrador.setTipoUsuario(TipoUsuario.valueOf(linea.split("--")[6]));
            administrador.setCedula(linea.split("--")[7]);

            listaVendedores.agregarfinal(administrador);
        }
        return listaVendedores;
    }
    public static void guardarProductos(HashMap<String, Producto> productos) throws IOException {
        StringBuilder contenido = new StringBuilder();

        for (Map.Entry<String, Producto> entry : productos.entrySet()) {
            Producto producto = entry.getValue();
            contenido.append(producto.getNombre()).append("--")
                    .append(producto.getUrlImagen()).append("--")
                    .append(producto.getPrecio()).append("--")
                    .append(producto.getTipoCategoria()).append("--")
                    .append(producto.getTipoEstado()).append("--")
                    .append(producto.getId()).append("\n");
        }

        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTO, contenido.toString(), false);
    }

    // Método para cargar productos desde un archivo de texto y devolver un HashMap
    public static HashMap<String, Producto> cargarProductos() throws IOException {
        HashMap<String, Producto> productos = new HashMap<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTO);

        for (String linea : contenido) {
            String[] partes = linea.split("--");
            Producto producto = new Producto(partes[0], partes[1], partes[2],
                    TipoCategoria.valueOf(partes[3]), TipoEstado.valueOf(partes[4]), Integer.parseInt(partes[5]));
            productos.put(partes[0], producto);
        }

        return productos;
    }








}
