module com.example.indian_geography_trivia {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.indian_geography_trivia to javafx.fxml;
    exports com.example.indian_geography_trivia;
}