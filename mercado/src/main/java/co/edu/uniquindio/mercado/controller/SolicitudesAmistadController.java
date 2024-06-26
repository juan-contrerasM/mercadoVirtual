package co.edu.uniquindio.mercado.controller;

import co.edu.uniquindio.mercado.estructuraDeDatos.listaEnlazada.ListaSimple;
import co.edu.uniquindio.mercado.model.SolicitudesAmistad;
import co.edu.uniquindio.mercado.model.Vendedor;
import co.edu.uniquindio.mercado.model.enums.EstadoSolicitd;
import co.edu.uniquindio.mercado.utils.Persistencia;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class SolicitudesAmistadController {
    Persistencia persistencia = new Persistencia();
    ListaSimple<SolicitudesAmistad> todasLasSolicitudesEnviadas;

    /**
     * Envía una solicitud de amistad de un usuario a otro.
     *
     * @param vendedorEnviaSolicitud  El usuario que envía la solicitud.
     * @param vendedorRecibeSolicitud El usuario que recibe la solicitud.
     * @throws IllegalArgumentException si los usuarios ya son amigos.
     */
    public void enviarSolicitudesAmistad(Vendedor vendedorEnviaSolicitud, Vendedor vendedorRecibeSolicitud) {
        // Verifica si los usuarios ya son amigos
        if (vendedorEnviaSolicitud.getRedAmigos().contains(vendedorRecibeSolicitud)) {
            throw new IllegalArgumentException("Ya son amigos");
        }
        // Crea una nueva solicitud de amistad
        SolicitudesAmistad solicitudAmistad = new SolicitudesAmistad(vendedorEnviaSolicitud, vendedorRecibeSolicitud);
        // Añade la solicitud a la lista de solicitudes enviadas del remitente
        vendedorEnviaSolicitud.agregarSolicitudesEnviadas(solicitudAmistad);
        // Añade la solicitud a la lista de solicitudes recibidas del receptor
        vendedorRecibeSolicitud.agregarSolicitudesRecibidas(solicitudAmistad);

        try {
            Persistencia.guardasSolicitudesEnviadas2(vendedorEnviaSolicitud.getSolicitudesEnviadas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            Persistencia.guardarSolicitudesRecibidas2(vendedorRecibeSolicitud.getSolicitudesRecibidas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Obtiene todas las solicitudes de amistad pendientes para un usuario receptor.
     *
     * @param vendedorRecibeSolicitud El usuario que recibe las solicitudes.
     * @return Una lista de solicitudes de amistad pendientes.
     */
    public List<SolicitudesAmistad> solicitudesAmistadPendientes(Vendedor vendedorRecibeSolicitud) {
        List<SolicitudesAmistad> solicitud = new ArrayList<>();
        for (SolicitudesAmistad estado : vendedorRecibeSolicitud.getSolicitudesRecibidas()) {
            if (estado.getEstado() == EstadoSolicitd.PENDIENTE) {
                solicitud.add(estado);
            }
        }
        return solicitud;
    }

    /**
     * Responde a una solicitud de amistad.
     *
     * @param solicitudAmistad La solicitud de amistad a la que se responde.
     * @param respuesta        La respuesta a la solicitud ("ACCEPTED" o "DECLINED").
     */
    public void responderSolicitudAmistad(Vendedor vendedorRecibeSolicitud, Vendedor vendedorEnviaSolicitud, SolicitudesAmistad solicitudAmistad, EstadoSolicitd respuesta) {
        // Establece el estado de la solicitud de amistad según la respuesta
        solicitudAmistad.setEstado(respuesta);
        if (respuesta == EstadoSolicitd.ACEPTADA) {


            try {
                todasLasSolicitudesEnviadas = Persistencia.cargarSolicitudesEnviadas2();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            //cambia el estado de la solicitud
            for (int i = 0; i <todasLasSolicitudesEnviadas.getTamanio() ; i++) {
                SolicitudesAmistad solicitudesAmistad= todasLasSolicitudesEnviadas.obtenerValorNodo(i);

                //envia
                System.out.println("envia");
                System.out.println(vendedorEnviaSolicitud.getNombreUsuario());
                System.out.println(solicitudAmistad.getUsuarioEnviaSolicitud());
                //recibe
                System.out.println("recibe");
                System.out.println(vendedorRecibeSolicitud.getNombreUsuario());
                System.out.println(solicitudAmistad.getUsuariorecibeSolicitud());

                if(vendedorEnviaSolicitud.getNombreUsuario().equals(solicitudesAmistad.getUsuarioEnviaSolicitud().getNombreUsuario())&&vendedorRecibeSolicitud.getNombreUsuario().equals(solicitudesAmistad.getUsuariorecibeSolicitud().getNombreUsuario())){
                    solicitudesAmistad.setEstado(EstadoSolicitd.ACEPTADA);
                    //s eguarda d enuevo la lista
                    try {
                        Persistencia.guardasSolicitudesEnviadas2(todasLasSolicitudesEnviadas);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }


                    break;
                }
            }

/*
            System.out.println("solicitud actual");

            System.out.println(solicitudAmistad.getUsuariorecibeSolicitud().getNombreUsuario());
            System.out.println(solicitudAmistad.getUsuarioEnviaSolicitud().getNombreUsuario());
            System.out.println(solicitudAmistad.getEstado());

 */





            // Si la solicitud es aceptada, añade los usuarios como amigos mutuamente
           // vendedorEnviaSolicitud = solicitudAmistad.getUsuarioEnviaSolicitud();
            //vendedorRecibeSolicitud = solicitudAmistad.getUsuariorecibeSolicitud();
            System.out.println("agregar amigos ");
            System.out.println("agregar amigos vendedor envia solicitd");
            vendedorEnviaSolicitud.agregarAmigos(vendedorRecibeSolicitud);
            System.out.println("agregar amigos vendedor recibe solicitd");
            vendedorRecibeSolicitud.agregarAmigos(vendedorEnviaSolicitud);


            //


        }
    }
}
