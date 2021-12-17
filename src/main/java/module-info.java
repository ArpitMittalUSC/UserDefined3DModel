module org.model.openfx.userdefinedmodel {
    requires javafx.controls;
    requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;

    opens org.model.openfx.userdefinedmodel to javafx.fxml;
    exports org.model.openfx.userdefinedmodel;
}
