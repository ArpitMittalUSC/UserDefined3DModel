package org.model.openfx.userdefinedmodel.inputgenerator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.model.openfx.userdefinedmodel.utility.Constants;

import javafx.geometry.Point3D;

public class InputFromFile {
	private Map<Integer, Point3D> pointMap = new HashMap<>();
	private List<List<Point3D>> faceList = new ArrayList<>();
	public int noOfVertices = 0;
	public int noOfFaces = 0;

	public InputFromFile(String inputFilePath) throws FileNotFoundException {
		File file = new File(inputFilePath);
		Scanner fileReader = new Scanner(file);

		Pattern.compile("^\\d+$");
		if (fileReader.hasNextLine()) {
			String data = fileReader.nextLine();
			String[] line1 = data.split(",");
			noOfVertices = Integer.valueOf(line1[0]);
			noOfFaces = Integer.valueOf(line1[1]);
		}

		int i = 1;
		while (i <= noOfVertices) {
			String data = fileReader.nextLine();
			String[] lineVertices = data.split(",");
			pointMap.put(Integer.valueOf(lineVertices[0]),
					new Point3D(Double.valueOf(lineVertices[1]) * Constants.POINT_SCALE,
							Double.valueOf(lineVertices[2]) * Constants.POINT_SCALE,
							Double.valueOf(lineVertices[3]) * Constants.POINT_SCALE));
			i++;
		}
		while (fileReader.hasNextLine()) {
			String data = fileReader.nextLine();
			String[] linefaces = data.split(",");
			ArrayList<Point3D> face = new ArrayList<>();
			for (int j = 0; j < linefaces.length; j++) {
				face.add(pointMap.get(Integer.valueOf(linefaces[j])));
			}
			face.add(face.get(0));
			faceList.add(face);
		}
		fileReader.close();
	}

	public Map<Integer, Point3D> getPointMap() {
		return pointMap;
	}

	public void setPointMap(Map<Integer, Point3D> pointMap) {
		this.pointMap = pointMap;
	}

	public List<List<Point3D>> getFaceList() {
		return faceList;
	}

	public void setFaceList(List<List<Point3D>> faceList) {
		this.faceList = faceList;
	}

}
