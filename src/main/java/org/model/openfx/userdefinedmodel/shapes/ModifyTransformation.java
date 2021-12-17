package org.model.openfx.userdefinedmodel.shapes;

import org.model.openfx.userdefinedmodel.utility.Constants;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.transform.Rotate;

public class ModifyTransformation {

	static Rotate xRotate = new Rotate(0, Constants.ORIGIN.add(Rotate.X_AXIS));
	static Rotate yRotate = new Rotate(0, Constants.ORIGIN.add(Rotate.Y_AXIS));
	static Rotate zRotate = new Rotate(0, Constants.ORIGIN.add(Rotate.Z_AXIS));

	final static DoubleProperty angleX = new SimpleDoubleProperty(0);
	final static DoubleProperty angleY = new SimpleDoubleProperty(0);
	final static DoubleProperty angleZ = new SimpleDoubleProperty(0);

	static double anchorX, anchorOldX, anchorY, anchorOldY;

	public static Scene addTransformationToGroup(Group group) {

		group.getTransforms().addAll(xRotate, yRotate);

		xRotate.angleProperty().bind(angleX);
		yRotate.angleProperty().bind(angleY);
		zRotate.angleProperty().bind(angleZ);

		Scene scene = new Scene(group, Constants.HEIGHT, Constants.WIDTH);

		scene.setOnMousePressed(event -> {
			anchorX = event.getSceneX() - Constants.HEIGHT/2;
			anchorY = event.getSceneY() + Constants.WIDTH;
			anchorOldX = event.getSceneX() - Constants.HEIGHT;
			anchorOldY = event.getSceneY() + Constants.WIDTH;
		});
		scene.setOnMouseDragged(event -> {
			anchorX = event.getSceneX() - Constants.HEIGHT;
			anchorY = event.getSceneY() + Constants.WIDTH;
			double dx = (anchorX - anchorOldX);
			double dy = (anchorY - anchorOldY);
			anchorOldX = anchorX;
			anchorOldY = anchorY;
			if (event.isPrimaryButtonDown()) {
				angleX.set(xRotate.getAngle() + dy);
				angleY.set(yRotate.getAngle() - dx);
			}
		});

		return scene;
	}
}
