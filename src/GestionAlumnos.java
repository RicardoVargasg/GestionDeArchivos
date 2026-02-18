import java.io.*;
import java.util.*;

public class GestionAlumnos {

    public Map<String, Alumno> leerFichero(String ruta) {
        Map<String, Alumno> mapa = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length != 2) continue;

                String nombre = partes[0];
                double nota = Double.parseDouble(partes[1]);

                mapa.putIfAbsent(nombre, new Alumno(nombre));
                mapa.get(nombre).agregarNota(nota);
            }

        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }

        return mapa;
    }

    public List<Alumno> obtenerAprobados(Map<String, Alumno> mapa) {
        List<Alumno> aprobados = new ArrayList<>();

        for (Alumno a : mapa.values()) {
            if (a.getMedia() >= 5) {
                aprobados.add(a);
            }
        }

        return aprobados;
    }

    public List<Alumno> ordenarPorMedia(Map<String, Alumno> mapa) {
        List<Alumno> lista = new ArrayList<>(mapa.values());
        lista.sort(Comparator.comparingDouble(Alumno::getMedia).reversed());
        return lista;
    }

    public Alumno mejorAlumno(Map<String, Alumno> mapa) {
        return Collections.max(mapa.values(), Comparator.comparingDouble(Alumno::getMedia));
    }

    public void guardarResultados (String ruta, Map<String, Alumno> mapa) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta))) {

            bw.write("NOTA MEDIA DE CADA ALUMNO:\n");
            for (Alumno a : mapa.values()) {
                bw.write(a.getNombre() + ": " + a.getMedia() + "\n");
            }

            bw.write("\nALUMNOS APROBADOS:\n");
            for (Alumno a : obtenerAprobados(mapa)) {
                bw.write(a.getNombre() + " (" + a.getMedia() + ")\n");
            }

            bw.write("\nALUMNOS ORDENADOS POR NOTA MEDIA:\n");
            for (Alumno a : ordenarPorMedia(mapa)) {
                bw.write(a.getNombre() + " (" + a.getMedia() + ")\n");
            }

            bw.write("\nMEJOR ALUMNO:\n");
            Alumno mejor = mejorAlumno(mapa);
            bw.write(mejor.getNombre() + " (" + mejor.getMedia() + ")\n");

        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero: " + e.getMessage());
        }
    }
}
