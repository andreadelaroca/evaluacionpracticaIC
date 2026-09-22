package ni.edu.uam.evaluacionpracticaic.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    private String nombres;
    private String apellidos;
    private String cargo;
    private double salario;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
