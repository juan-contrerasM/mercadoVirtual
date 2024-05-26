package co.edu.uniquindio.mercado.model;

import co.edu.uniquindio.mercado.model.enums.EstadoSolicitd;

public class SolicitudesAmistad {


        private Vendedor usuarioEnviaSolicitud;
        private Vendedor usuariorecibeSolicitud;
        private EstadoSolicitd estado; // "PENDING", "ACCEPTED", "DECLINED"

    public SolicitudesAmistad() {
    }

    public SolicitudesAmistad(Vendedor usuarioEnviaSolicitud, Vendedor usuariorecibeSolicitud) {
            this.usuarioEnviaSolicitud = usuarioEnviaSolicitud;
            this.usuariorecibeSolicitud = usuariorecibeSolicitud;
            this.estado= EstadoSolicitd.PENDIENTE;
        }

    public Vendedor getUsuarioEnviaSolicitud() {
        return usuarioEnviaSolicitud;
    }

    public void setUsuarioEnviaSolicitud(Vendedor usuarioEnviaSolicitud) {
        this.usuarioEnviaSolicitud = usuarioEnviaSolicitud;
    }

    public Vendedor getUsuariorecibeSolicitud() {
        return usuariorecibeSolicitud;
    }

    public void setUsuariorecibeSolicitud(Vendedor usuariorecibeSolicitud) {
        this.usuariorecibeSolicitud = usuariorecibeSolicitud;
    }

    public EstadoSolicitd getEstado() {
        return estado;
    }

    public void setEstado(EstadoSolicitd estado) {
        this.estado = estado;
    }
}




