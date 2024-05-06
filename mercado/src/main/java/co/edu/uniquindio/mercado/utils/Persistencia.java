package co.edu.uniquindio.mercado.utils;

/*import co.edu.uniquindio.banco.bancouq.exceptions.UsuarioExcepcion;
import co.edu.uniquindio.banco.bancouq.model.*;*/

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaDoble;
import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.Administrador;
import co.edu.uniquindio.mercado.model.TipoUsuario;
import co.edu.uniquindio.mercado.model.Vendedor;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Persistencia {


    //--------------------------------------RUTAS----------------------------------------
    public static final String RUTA_ARCHIVO_VENDEDORES = "src/main/resources/co/edu/uniquindio/mercado/archivos/vendedores";
    public static final String RUTA_ARCHIVO_ADMINISTRADOR = "src/main/resources/co/edu/uniquindio/mercado/archivos/administrador";
    public static final String RUTA_ARCHIVO_LOG = "mercado/src/main/resources/co/edu/uniquindio/mercado/archivos/log";


    /**
     * Guarda en un archivo de texto todos la información de las personas almacenadas en el ArrayList
     *
     * @param
     * @param
     * @throws IOException
     */
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


//	--------------------------------------------CARGAR ARCHIVOS----------------------------------------------------------

    /**
     * @param
     * @param
     * @return un Arraylist de personas con los datos obtenidos del archivo de texto indicado
     * @throws FileNotFoundException
     * @throws IOException
     */

/*
vendedor.getNombreUsuario() + "--" + vendedor.getContrasenia() + "--" + vendedor.getNombre() + "--" +
                    vendedor.getEdad() + "--" + vendedor.getCorreo() + "--" + vendedor.getNumeroCelular() + "--" + vendedor.getTipoUsuario() +
                    "--" + vendedor.getCedula() + "\n";
 */
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




    /*


    //------------------------------------SERIALIZACIÓN  y XML

//BINARIO
    public static SubastaQuindio cargarRecursoBancoBinario() {

        SubastaQuindio subastaQuindio= null;

        try {
            subastaQuindio = (SubastaQuindio) ArchivoUtil.cargarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return subastaQuindio;
    }

    public static void guardarRecursoBancoBinario(SubastaQuindio subastaQuindio) {
        try {
            ArchivoUtil.salvarRecursoSerializado(RUTA_ARCHIVO_MODELO_BANCO_BINARIO, subastaQuindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


//XML
    public static void guardarRecursoBancoXML(SubastaQuindio subastaQuindio) {

        try {
            ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_MODELO_PRODCUTOS_XML, subastaQuindio);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    */


}
