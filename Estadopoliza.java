package modelo;

public class Estadopoliza {
    private String descripcion;
    private String nombre;
    private String activa;
    private String cancelada;
    private String vencida;

    // Constructor con los campos exactos
    public Estadopoliza(String descripcion, String nombre, String activa, String cancelada, String vencida) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.activa = activa;
        this.cancelada = cancelada;
        this.vencida = vencida;
    }

    // dt atrib cls 
    public String getDescripcion() { return descripcion; }
    public String getNombre() { return nombre; }
    public String getActiva() { return activa; }
    public String getCancelada() { return cancelada; }
    public String getVencida() { return vencida; }
}


