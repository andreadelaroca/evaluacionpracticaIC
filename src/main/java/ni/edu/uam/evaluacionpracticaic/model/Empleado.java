package ni.edu.uam.empleados.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {

    private String nombre;
    private String apellidos;
    private String cargo;
    private double salario;

    public String getNombreCompleto() {
        return nombre + " " + apellidos;
    }
}
