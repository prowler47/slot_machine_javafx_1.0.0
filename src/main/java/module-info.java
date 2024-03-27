module ua.dragunovskiy.slotmachine {
    requires javafx.controls;
    requires javafx.fxml;


    opens ua.dragunovskiy.slotmachine to javafx.fxml;
    exports ua.dragunovskiy.slotmachine;
}