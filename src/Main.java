import java.util.Map;

public class Main {
    public static void main(String[] args) {

        GestionAlumnos gestor = new GestionAlumnos();

        Map<String, Alumno> alumnos = gestor.leerFichero("alumnos.txt");

        gestor.guardarResultados("resultado.txt", alumnos);

        System.out.println("Proceso completado.");
    }
}