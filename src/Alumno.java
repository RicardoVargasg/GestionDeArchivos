public class Alumno {

    private String nombre;
    private double sumaNotas;
    private int contadorNotas;

    public Alumno(String nombre) {
        this.nombre = nombre;
        this.sumaNotas = 0;
        this.contadorNotas = 0;
    }

    public void agregarNota(double nota) {
        sumaNotas += nota;
        contadorNotas++;
    }

    public double getMedia() {
        if (contadorNotas == 0) return 0;
        return sumaNotas / contadorNotas;
    }

    public String getNombre() {
        return nombre;
    }
}
