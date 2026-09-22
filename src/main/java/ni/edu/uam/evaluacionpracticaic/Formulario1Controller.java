package ni.edu.uam.evaluacionpracticaic;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Formulario1Controller {


    // Componentes de la interfaz
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cmbCargo;
    @FXML private TextField txtSalario;

    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnVerListado;

    /**
     * Se ejecuta al cargar el formulario para inicializar componentes
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Llenar el ComboBox de Cargo con opciones iniciales
        ObservableList<String> cargos = FXCollections.observableArrayList(
                "Desarrollador Junior",
                "Desarrollador Senior",
                "Analista de Sistemas",
                "Gerente de Proyecto",
                "Soporte Técnico"
        );
        cmbCargo.setItems(cargos);
    }

    /**
     * Acción del botón "Guardar"
     */
    @FXML
    void guardarEmpleado(ActionEvent event) {
        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String cargo = cmbCargo.getValue();
        String salarioStr = txtSalario.getText().trim();

        // 1. Validación de campos vacíos
        if (nombres.isEmpty() || apellidos.isEmpty() || cargo == null || salarioStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Todos los campos son obligatorios. Seleccione un cargo.", Alert.AlertType.ERROR);
            return;
        }

        // 2. Validación de formato (solo letras y espacios para nombres y apellidos)
        if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarAlerta("Error de Validación", "Los nombres solo deben contener letras.", Alert.AlertType.ERROR);
            return;
        }

        if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarAlerta("Error de Validación", "Los apellidos solo deben contener letras.", Alert.AlertType.ERROR);
            return;
        }

        // 3. Validación del salario
        double salario = 0.0;
        try {
            salario = Double.parseDouble(salarioStr);
            if (salario <= 0) {
                mostrarAlerta("Error de Validación", "El salario debe ser mayor a 0.", Alert.AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Validación", "Ingrese un valor numérico válido para el salario.", Alert.AlertType.ERROR);
            return;
        }

        // Proceso de guardado
        System.out.println("Empleado guardado: " + nombres + " " + apellidos + " | " + cargo + " | $" + salario);
        mostrarAlerta("Éxito", "Empleado registrado correctamente.", Alert.AlertType.INFORMATION);

        limpiarFormulario();
    }

    /**
     * Acción del botón "Limpiar"
     */
    @FXML
    void limpiarAccion(ActionEvent event) {
        limpiarFormulario();
    }

    /**
     * Acción del botón "Ver Listado" (Navegación)
     */
    @FXML
    void verListado(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FormularioListado.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            mostrarAlerta("Error de Navegación", "No se pudo cargar la vista del listado.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para restablecer la vista
     */
    private void limpiarFormulario() {
        txtNombres.clear();
        txtApellidos.clear();
        cmbCargo.getSelectionModel().clearSelection();
        txtSalario.clear();
        txtNombres.requestFocus();
    }

    /**
     * Método auxiliar para alertas
     */
    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}