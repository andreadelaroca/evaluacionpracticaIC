package ni.edu.uam.evaluacionpracticaic.controller;


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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Formulario1Controller implements Initializable {

    // Identificadores exactos definidos en tu archivo FXML
    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private ComboBox<String> cbxCargo;
    @FXML private TextField txtSalario;

    @FXML private Button btnGuardar;
    @FXML private Button btnLimpiar;
    @FXML private Button btnVerListado;

    @FXML private Label lblMensaje;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // 1. Cargar opciones en el ComboBox
        ObservableList<String> cargos = FXCollections.observableArrayList(
                "Desarrollador Junior",
                "Desarrollador Senior",
                "Analista de Sistemas",
                "Gerente de Proyecto",
                "Soporte Técnico"
        );
        cbxCargo.setItems(cargos);

        // 2. Asignar los eventos a los botones dinámicamente
        btnGuardar.setOnAction(this::guardarEmpleado);
        btnLimpiar.setOnAction(this::limpiarFormulario);
        btnVerListado.setOnAction(this::verListado);
    }

    private void guardarEmpleado(ActionEvent event) {
        // Reiniciar el mensaje inferior
        lblMensaje.setText("");

        String nombres = txtNombres.getText().trim();
        String apellidos = txtApellidos.getText().trim();
        String cargo = cbxCargo.getValue();
        String salarioStr = txtSalario.getText().trim();

        // Validación de campos vacíos
        if (nombres.isEmpty() || apellidos.isEmpty() || cargo == null || salarioStr.isEmpty()) {
            mostrarAlerta("Error de Validación", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
            return;
        }

        // Validación de solo letras para nombres y apellidos
        if (!nombres.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarAlerta("Error de Validación", "Los nombres solo deben contener letras.", Alert.AlertType.ERROR);
            return;
        }

        if (!apellidos.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            mostrarAlerta("Error de Validación", "Los apellidos solo deben contener letras.", Alert.AlertType.ERROR);
            return;
        }

        // Validación numérica del salario
        double salario;
        try {
            salario = Double.parseDouble(salarioStr);
            if (salario <= 0) {
                mostrarAlerta("Error de Validación", "El salario debe ser mayor a 0.", Alert.AlertType.ERROR);
                return;
            }
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Validación", "Formato numérico inválido para el salario.", Alert.AlertType.ERROR);
            return;
        }

        // Proceso de guardado exitoso
        lblMensaje.setStyle("-fx-text-fill: #28a745; -fx-font-weight: bold;"); // Cambia a verde
        lblMensaje.setText("¡Empleado " + nombres + " registrado con éxito!");

        limpiarCampos();
    }

    private void limpiarFormulario(ActionEvent event) {
        limpiarCampos();
        lblMensaje.setText(""); // Limpia también el mensaje de éxito/error
    }

    private void limpiarCampos() {
        txtNombres.clear();
        txtApellidos.clear();
        cbxCargo.getSelectionModel().clearSelection();
        txtSalario.clear();
        txtNombres.requestFocus();
    }

    private void verListado(ActionEvent event) {
        try {
            // Asegúrate de que la ruta corresponda a la ubicación real de tu vista
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ni/edu/uam/casopracticoic/view/Formulario2.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException | NullPointerException e) {
            mostrarAlerta("Error de Navegación", "No se encontró el archivo FXML del listado.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}