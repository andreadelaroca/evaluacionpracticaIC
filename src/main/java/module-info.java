module ni.edu.uam.evaluacionpracticaic {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.evaluacionpracticaic to javafx.fxml;
    exports ni.edu.uam.evaluacionpracticaic;
    exports ni.edu.uam.evaluacionpracticaic.app;
    opens ni.edu.uam.evaluacionpracticaic.app to javafx.fxml;
    exports ni.edu.uam.evaluacionpracticaic.controller;
    opens ni.edu.uam.evaluacionpracticaic.controller to javafx.fxml;
}