module org.example.iss_hospital_v2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;


    opens org.example.iss_hospital_v2 to javafx.fxml;
    opens org.example.iss_hospital_v2.Model to javafx.base;
    exports org.example.iss_hospital_v2;
}