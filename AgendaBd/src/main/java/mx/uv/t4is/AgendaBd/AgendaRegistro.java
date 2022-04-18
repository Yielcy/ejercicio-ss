package mx.uv.t4is.AgendaBd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AgendaRegistro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String fecha;
    private String nombre;
    private String motivo;
    private String hora;

    public void setId(int id){
        this.id = id;
    }

    public void setFecha(String fecha){
        this.fecha = fecha;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public void setMotivo(String motivo){
        this.motivo = motivo;
    }

    public void setHora(String hora){
        this.hora = hora;
    }

    public int getId(){
        return id;
    }

    public String getFecha(){
        return fecha;
    }

    public String getNombre(){
        return nombre;
    }

    public String getMotivo(){
        return motivo;
    }

    public String getHora(){
        return hora;
    }
}
