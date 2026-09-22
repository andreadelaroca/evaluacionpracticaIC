package ni.edu.uam.evaluacionpracticaic.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ni.edu.uam.evaluacionpracticaic.model.Empleado;

public class EmpleadoData {
    private static EmpleadoData instance;
    private final ObservableList<Empleado> listaEmpleados;

    private EmpleadoData() {
        listaEmpleados = FXCollections.observableArrayList();
    }

    public static synchronized EmpleadoData getInstance() {
        if (instance == null) {
            instance = new EmpleadoData();
        }
        return instance;
    }

    public ObservableList<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }
}