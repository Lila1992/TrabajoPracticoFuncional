import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

// Clase Alumno con atributos nombre, nota y curso
class Alumno {
    private String nombre;
    private double nota;
    private String curso;

    public Alumno(String nombre, double nota, String curso) {
        this.nombre = nombre;
        this.nota = nota;
        this.curso = curso;
    }

    public String getNombre() { return nombre; }
    public double getNota() { return nota; }
    public String getCurso() { return curso; }

    @Override
    public String toString() {
        return "Alumno [Nombre: " + nombre + ", Nota: " + nota + ", Curso: " + curso + "]";
    }
}

public class Main {
    public static void main(String[] args) {
        List<Alumno> alumnos = new ArrayList<>();
        alumnos.add(new Alumno("Shirley Torres", 9.5, "Programación III"));
        alumnos.add(new Alumno("Carlos Pérez", 6.5, "Bases de Datos II"));
        alumnos.add(new Alumno("Ana Gómez", 8.0, "Programación III"));
        alumnos.add(new Alumno("Lucas Rodríguez", 10.0, "Ingeniería de Software"));
        alumnos.add(new Alumno("María Fernández", 5.0, "Bases de Datos II"));
        alumnos.add(new Alumno("Javier Martínez", 7.5, "Programación III"));
        alumnos.add(new Alumno("Sofia López", 9.0, "Ingeniería de Software"));

        System.out.println("=== PROGRAMACIÓN FUNCIONAL Y STREAMS EN JAVA ===\n");

        // 1. Obtener los nombres de los alumnos aprobados (nota >= 7) en mayúsculas y ordenados
        List<String> aprobadosMayusculas = alumnos.stream()
                .filter(a -> a.getNota() >= 7.0)
                .map(a -> a.getNombre().toUpperCase())
                .sorted()
                .collect(Collectors.toList());

        System.out.println("1. Alumnos aprobados (nota >= 7) en mayúsculas y ordenados:");
        aprobadosMayusculas.forEach(nombre -> System.out.println(" - " + nombre));

        // 2. Calcular el promedio general de notas
        OptionalDouble promedioGeneral = alumnos.stream()
                .mapToDouble(Alumno::getNota)
                .average();

        System.out.println("\n2. Promedio general de notas:");
        if (promedioGeneral.isPresent()) {
            System.out.printf(" Promedio General: %.2f\n", promedioGeneral.getAsDouble());
        }

        // 3. Agrupar alumnos por curso usando Collectors.groupingBy()
        Map<String, List<Alumno>> alumnosPorCurso = alumnos.stream()
                .collect(Collectors.groupingBy(Alumno::getCurso));

        System.out.println("\n3. Alumnos agrupados por curso:");
        alumnosPorCurso.forEach((curso, lista) -> {
            System.out.println(" Curso: " + curso);
            lista.forEach(a -> System.out.println("   * " + a.getNombre() + " (Nota: " + a.getNota() + ")"));
        });

        // 4. Obtener los 3 mejores promedios (alumnos con mejores notas)
        List<Alumno> top3Mejores = alumnos.stream()
                .sorted(Comparator.comparingDouble(Alumno::getNota).reversed())
                .limit(3)
                .collect(Collectors.toList());

        System.out.println("\n4. Top 3 alumnos con mejores promedios/notas:");
        top3Mejores.forEach(a -> System.out.println(" ★ " + a.getNombre() + " - Nota: " + a.getNota()));
    }
}