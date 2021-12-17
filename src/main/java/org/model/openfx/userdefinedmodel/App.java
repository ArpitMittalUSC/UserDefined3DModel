package org.model.openfx.userdefinedmodel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.model.openfx.userdefinedmodel.inputgenerator.InputFromFile;
import org.model.openfx.userdefinedmodel.shapes.CreateShapes;
import org.model.openfx.userdefinedmodel.shapes.ModifyTransformation;
import org.model.openfx.userdefinedmodel.utility.Constants;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
	
	@Override
	public void start(Stage stage) throws FileNotFoundException {

		InputFromFile input = new InputFromFile(Constants.FILEPATH);
		
		ArrayList<Node> sphereList = CreateShapes.createSpheres(input.getPointMap());
		ArrayList<Node> lineList = CreateShapes.createLines(input.getFaceList());

		ArrayList<Node> nodeList = new ArrayList<>();
		nodeList.addAll(sphereList);
		nodeList.addAll(lineList);

		Group group = new Group(nodeList);

		stage.setScene(ModifyTransformation.addTransformationToGroup(group));
		// Set the Title of the Stage
		stage.setTitle("Sphere");
		// Display the Stage
		stage.show();
	}

	public static void main(String[] args) {
		launch();
	}

}