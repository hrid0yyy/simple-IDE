module sample.ide {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens sample.ide to javafx.fxml;
    exports sample.ide;
}