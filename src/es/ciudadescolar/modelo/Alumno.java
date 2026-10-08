package es.ciudadescolar.modelo;

public class Alumno {

    private String nombre;
    private Integer edad;
    private String expediente;
    public Alumno(String nombre, Integer edad, String expediente) {
        this.nombre = nombre;
        this.edad = edad;
        this.expediente = expediente;
    }
    public Alumno() {
    }
    public String getNombre() {
        return nombre;
    }
    public Integer getEdad() {
        return edad;
    }
    public String getExpediente() {
        return expediente;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public void setExpediente(String expediente) {
        this.expediente = expediente;
    }
    @Override
    public String toString() {
        return "Alumno [nombre=" + nombre + ", edad=" + edad + ", expediente=" + expediente + "]";
    }
    

}

