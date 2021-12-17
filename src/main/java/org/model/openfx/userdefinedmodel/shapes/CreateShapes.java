package org.model.openfx.userdefinedmodel.shapes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public final class CreateShapes {

	public final static int HEIGHT = 600;
	public final static int WIDTH = 600;
	public final static Point3D ORIGIN = new Point3D(WIDTH / 2, HEIGHT / 2, 0);
	public final static int POINT_SCALE = 100;

	public static final ArrayList<Node> createLines(List<List<Point3D>> faceList) {
		ArrayList<Node> lineList = new ArrayList<>();
		for (List<Point3D> face : faceList) {
			for (int k = 1; k < face.size(); k++) {
				lineList.add(getLineForPoints(face.get(k - 1), face.get(k)));
			}
		}
		return lineList;
	}

	public static final ArrayList<Node> createSpheres(Map<Integer, Point3D> pointMap) {
		ArrayList<Node> sphereList = new ArrayList<>();
		System.out.println("pointMap" + pointMap);
		for (Integer point : pointMap.keySet()) {
			sphereList.add(getSphereForPoint(pointMap.get(point)));
		}
		return sphereList;
	}

	public static final Sphere getSphereForPoint(Point3D point1) {
		Sphere sphere1 = new Sphere(10);
		sphere1.setLayoutX(getLayoutX(point1.getX()));
		sphere1.setLayoutY(getLayoutY(point1.getY()));
		sphere1.setTranslateZ(point1.getZ());
		return sphere1;
	}

	public static final Cylinder getLineForPoints(Point3D origin, Point3D target) {
		Point3D yAxis = new Point3D(0, 1, 0);
		Point3D diff = target.subtract(origin);
		double height = diff.magnitude();

		Point3D mid = target.midpoint(origin);
		Translate moveToMidpoint = new Translate(getLayoutX(mid.getX()), getLayoutY(mid.getY()), mid.getZ());

		Point3D axisOfRotation = diff.crossProduct(yAxis);
		double angle = Math.acos(diff.normalize().dotProduct(yAxis));
		Rotate rotateAroundCenter = new Rotate(Math.toDegrees(angle), axisOfRotation);

		Cylinder line = new Cylinder(4, height);

		line.getTransforms().addAll(moveToMidpoint, rotateAroundCenter);
		return line;
	}

	private static double getLayoutY(double i) {
		return HEIGHT / 2 - i;
	}

	private static double getLayoutX(double d) {
		return d + WIDTH / 2;
	}
}
