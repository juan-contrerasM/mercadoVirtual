package co.edu.uniquindio.mercado.utils;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/

import co.edu.uniquindio.mercado.controller.ModelFactoryController;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.Pila;
import co.edu.uniquindio.mercado.model.*;
import co.edu.uniquindio.mercado.model.enums.EstadoSolicitd;
import co.edu.uniquindio.mercado.model.enums.TipoCategoria;
import co.edu.uniquindio.mercado.model.enums.TipoEstado;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;


public class Persistencia {


    //--------------------------------------RUTAS----------------------------------------
    public static final String RUTA_ARCHIVO_VENDEDORES = "src/main/resources/co/edu/uniquindio/mercado/archivos/vendedores";
    public static final String RUTA_ARCHIVO_ADMINISTRADOR = "src/main/resources/co/edu/uniquindio/mercado/archivos/administrador";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/co/edu/uniquindio/mercado/archivos/log";
    public static  final  String RUTA_ARCHIVO_PRODUCTO="src/main/resources/co/edu/uniquindio/mercado/archivos/productos";
    private static final String RUTA_ARCHIVO_PUBLICACIONES ="src/main/resources/co/edu/uniquindio/mercado/archivos/publicaciones";
    private static  final String RUTA_ARCHVIO_Megustas="src/main/resources/co/edu/uniquindio/mercado/archivos/megustas";
    private static  final String RUTA_ARCHVIO_solicitudesEnviadas="src/main/resources/co/edu/uniquindio/mercado/archivos/solicitudesEnviadas.txt";
    private static  final String RUTA_ARCHVIO_solicitudesRecibidas="src/main/resources/co/edu/uniquindio/mercado/archivos/solicitudesRecibidas.txt";

    private static  final String RUTA_ARCHVIO_CODIGOPRODCUTO="src/main/resources/co/edu/uniquindio/mercado/archivos/codigoProducto";

    private static  final String  RUTA_ARCHIVO_COMENTARIO="src/main/resources/co/edu/uniquindio/mercado/archivos/comentarios";

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
                    "--" + vendedor.getCedula() + "--" + vendedor.getUrlImg()+ "--" +vendedor.getSolicitudesEnviadas()+"\n";
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
    public static void guardarComentario(Pila<Comentario> listaComentarios) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
       Comentario comentario =new Comentario();
        int tamanio= listaComentarios.getTamanio();
        for (int i = 0; i < tamanio; i++) {
            comentario=listaComentarios.desapilar();
            contenido += comentario.getVendedor().getNombreUsuario()+ "--" + comentario.getPublicacion().getProducto().getId()+ "--" + comentario.getMensaje() + "--" +
                    comentario.getFecha() + "--" + comentario.getHora() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_COMENTARIO, contenido, false);
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


    public static void guardarMegustas(ArrayList<Megusta> listaMeGusta) throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        Megusta meGusta = new Megusta();
        for (int i = 0; i < listaMeGusta.size(); i++) {
            meGusta=listaMeGusta.get(i);
            contenido += meGusta.getVendedor().getNombreUsuario() + "--" + meGusta.getPublicacion().getProducto().getId() + "--" + meGusta.getHora() + "--" +
                   meGusta.getFecha()+ "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHVIO_Megustas, contenido, false);
    }




//	--------------------------------------------CARGAR ARCHIVOS----------------------------------------------------------
public static ArrayList<Megusta> cargarMegustas() throws FileNotFoundException, IOException {
    ArrayList<Megusta> listaMegustas = new ArrayList<>();
    ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHVIO_Megustas);
    String linea = "";
    for (int i = 0; i < contenido.size(); i++) {
        linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
        Megusta megusta = new Megusta();
        Vendedor vendedor=new Vendedor();
        Publicacion publicacion= new Publicacion();
        Producto producto= new Producto();
        megusta.setVendedor(vendedor);
        megusta.setPublicacion(publicacion);
        megusta.getVendedor().setNombreUsuario(linea.split("--")[0]);
        megusta.setPublicacion(publicacion);
        megusta.getPublicacion().setProducto(producto);
        megusta.getPublicacion().getProducto().setId(Integer.parseInt(linea.split("--")[1]));
        megusta.setHora(LocalTime.parse(linea.split("--")[2]));
        megusta.setFecha(LocalDate.parse(linea.split("--")[3]));
        listaMegustas.add(megusta);
    }


    return listaMegustas;
}



    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static Pila<Comentario> cargarComentarios ()throws FileNotFoundException, IOException {
        Pila<Comentario> listaComentarios = new Pila<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_COMENTARIO);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);
            Comentario comentario = new Comentario();
            Vendedor vendedor=new Vendedor();
            Publicacion publicacion= new Publicacion();
            Producto producto= new Producto();
            comentario.setVendedor(vendedor);
            comentario.setPublicacion(publicacion);
            comentario.getVendedor().setNombreUsuario(linea.split("--")[0]);
            comentario.setPublicacion(publicacion);
            comentario.getPublicacion().setProducto(producto);
            comentario.getPublicacion().getProducto().setId(Integer.parseInt(linea.split("--")[1]));
            comentario.setMensaje(linea.split("--")[2]);
            comentario.setFecha(LocalDate.parse(linea.split("--")[3]));
            comentario.setHora(LocalTime.parse(linea.split("--")[4]));
            listaComentarios.apilar(comentario);
        }


        return listaComentarios;
    }





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
            ArrayList<Megusta> listMegusta=new ArrayList<>();
            publicacion.setListMegusta(listMegusta);
            Pila<Comentario>listaComentarios=new Pila<>();
            publicacion.setListComentario(listaComentarios);
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
            vendedor.setUrlImg(linea.split("--")[8]);

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


    //--------------------------------------------------------------------------------






    public static void guardasSolicitudesEnviadas2(ListaSimple<SolicitudesAmistad> listaSolicitudes)throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        SolicitudesAmistad solicitudesAmistad ;

        for (int i = 0; i < listaSolicitudes.getTamanio(); i++) {
            solicitudesAmistad=listaSolicitudes.obtenerValorNodo(i);
            contenido +=
                    solicitudesAmistad.getUsuarioEnviaSolicitud().getNombreUsuario() + "--" +
                            solicitudesAmistad.getUsuarioEnviaSolicitud().getUrlImg() + "--" +

                            solicitudesAmistad.getUsuariorecibeSolicitud().getNombreUsuario()+ "--" +
                            solicitudesAmistad.getUsuariorecibeSolicitud().getUrlImg()+ "--" +

                            solicitudesAmistad.getEstado() +"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHVIO_solicitudesEnviadas, contenido, false);
    }






    public static void guardarSolicitudesRecibidas2(ListaSimple<SolicitudesAmistad> listaSolicitudes)throws IOException {
        // TODO Auto-generated method stub
        String contenido = "";
        SolicitudesAmistad solicitudesAmistad ;

        for (int i = 0; i < listaSolicitudes.getTamanio(); i++) {
            solicitudesAmistad=listaSolicitudes.obtenerValorNodo(i);
            contenido +=
                    solicitudesAmistad.getUsuarioEnviaSolicitud().getNombreUsuario() + "--" +
                            solicitudesAmistad.getUsuarioEnviaSolicitud().getUrlImg() + "--" +

                            solicitudesAmistad.getUsuariorecibeSolicitud().getNombreUsuario()+ "--" +
                            solicitudesAmistad.getUsuariorecibeSolicitud().getUrlImg()+ "--" +

                            solicitudesAmistad.getEstado() +"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHVIO_solicitudesRecibidas, contenido, true);
    }






    public static ListaSimple<SolicitudesAmistad> cargarSolicitudesEnviadas2() throws FileNotFoundException, IOException {
        ListaSimple<SolicitudesAmistad> listaSolicitudes = new ListaSimple<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHVIO_solicitudesEnviadas);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            SolicitudesAmistad solicitudesAmistad = new SolicitudesAmistad();
            Vendedor vendedor = new Vendedor();
            vendedor.setNombreUsuario(linea.split("--")[0]);
            vendedor.setUrlImg(linea.split("--")[1]);

            Vendedor vendedor2 = new Vendedor();
            vendedor2.setNombreUsuario(linea.split("--")[2]);
            vendedor2.setUrlImg(linea.split("--")[3]);


            solicitudesAmistad.setUsuarioEnviaSolicitud(vendedor);
            solicitudesAmistad.setUsuariorecibeSolicitud(vendedor2);
            solicitudesAmistad.setEstado(EstadoSolicitd.valueOf(linea.split("--")[4]));


            listaSolicitudes.agregarInicio(solicitudesAmistad);
        }


        return listaSolicitudes;
    }




    public static ListaSimple<SolicitudesAmistad> cargarSolicitudesRecibidas2() throws FileNotFoundException, IOException {
        ListaSimple<SolicitudesAmistad> listaSolicitudes = new ListaSimple<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHVIO_solicitudesRecibidas);
        String linea = "";
        for (int i = 0; i < contenido.size(); i++) {
            linea = contenido.get(i);//juan,arias,125454,Armenia,uni1@,12454,125444
            SolicitudesAmistad solicitudesAmistad = new SolicitudesAmistad();
            Vendedor vendedor = new Vendedor();
            vendedor.setNombreUsuario(linea.split("--")[0]);
            vendedor.setUrlImg(linea.split("--")[1]);

            Vendedor vendedor2 = new Vendedor();
            vendedor2.setNombreUsuario(linea.split("--")[2]);
            vendedor2.setUrlImg(linea.split("--")[3]);


            solicitudesAmistad.setUsuarioEnviaSolicitud(vendedor);
            solicitudesAmistad.setUsuariorecibeSolicitud(vendedor2);
            solicitudesAmistad.setEstado(EstadoSolicitd.valueOf(linea.split("--")[4]));


            listaSolicitudes.agregarfinal(solicitudesAmistad);
        }


        return listaSolicitudes;
    }

    }







