package mx.uv.t4is.AgendaBd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import https.t4is_uv_mx.agenda.BorrarRegistroRequest;
import https.t4is_uv_mx.agenda.BorrarRegistroResponse;
import https.t4is_uv_mx.agenda.BuscarRegistrosResponse;
import https.t4is_uv_mx.agenda.ModificarRegistroRequest;
import https.t4is_uv_mx.agenda.ModificarRegistroResponse;
import https.t4is_uv_mx.agenda.RegistarEntradaRequest;
import https.t4is_uv_mx.agenda.RegistarEntradaResponse;

@Endpoint
public class AgendaEndPoint {
    @Autowired
    Iagenda iagenda;

    @PayloadRoot(localPart = "RegistarEntradaRequest",namespace = "https://t4is.uv.mx/agenda")
    @ResponsePayload
    public RegistarEntradaResponse RegistrarEntrada(@RequestPayload RegistarEntradaRequest peticion){
        RegistarEntradaResponse respuesta = new RegistarEntradaResponse();
        respuesta.setRespuesta("Entrada regitrada de: " + peticion.getNombre());
        AgendaRegistro agenda = new AgendaRegistro();
        agenda.setFecha(peticion.getFecha());
        agenda.setNombre(peticion.getNombre());
        agenda.setMotivo(peticion.getMotivo());
        agenda.setHora(peticion.getHora());
        iagenda.save(agenda);
        return respuesta;
    }

    @PayloadRoot(localPart = "BuscarRegistrosRequest",namespace = "https://t4is.uv.mx/agenda")
    @ResponsePayload
    public BuscarRegistrosResponse BuscarRegistro(){
        BuscarRegistrosResponse respuesta = new BuscarRegistrosResponse();
        Iterable<AgendaRegistro> lista = iagenda.findAll();
        for (AgendaRegistro o : lista) {
            BuscarRegistrosResponse.Agenda agendaBuscar = new BuscarRegistrosResponse.Agenda();
            agendaBuscar.setId(o.getId());
            agendaBuscar.setFecha(o.getFecha());
            agendaBuscar.setNombre(o.getNombre());
            agendaBuscar.setMotivo(o.getMotivo());
            agendaBuscar.setHora(o.getHora());
            respuesta.getAgenda().add(agendaBuscar);
        }
        return respuesta;
    }

    @PayloadRoot(localPart = "ModificarRegistroRequest",namespace = "https://t4is.uv.mx/agenda")
    @ResponsePayload
    public ModificarRegistroResponse modificarAgenda(@RequestPayload ModificarRegistroRequest peticion){
        ModificarRegistroResponse respuesta = new ModificarRegistroResponse();
        AgendaRegistro e = new AgendaRegistro();
        e.setId(peticion.getId());
        e.setFecha(peticion.getFecha());
        e.setNombre(peticion.getNombre());
        e.setMotivo(peticion.getMotivo());
        e.setHora(peticion.getHora());
        iagenda.save(e);
        respuesta.setRespuesta(true);
        return respuesta;
    }

    @PayloadRoot(localPart = "BorrarRegistroRequest",namespace = "https://t4is.uv.mx/agenda")
    @ResponsePayload
    public BorrarRegistroResponse borrarRegistro(@RequestPayload BorrarRegistroRequest peticion){
        BorrarRegistroResponse respuesta = new BorrarRegistroResponse();
        iagenda.deleteById(peticion.getId());
        respuesta.setRespuesta(true);
        return respuesta;
    }
}
