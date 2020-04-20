package media;

import java.util.ArrayList;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class GraphicsHandler {
	private StackPane pane;
	private ArrayList<Shape> shapes = new ArrayList<Shape>();// shape objects
	private ArrayList<String> ids = new ArrayList<String>();//corresponding IDs
	private int paneWidth;
	private int paneHeight;
	public GraphicsHandler(StackPane targetPane) {
		this.pane = targetPane;
		paneWidth = (int)pane.getMinWidth();
		paneHeight = (int)pane.getMinHeight();
	}
	//single colour line
	public void registerLine(float xStart, float xEnd, float yStart, float yEnd, String lineColour, String id) {
		Color lc = Color.web(lineColour);//creates colour from hash code
		int lineWidth = 5;//line width
		Shape shape = new Shape(lc,lc,paneWidth,paneHeight,lineWidth);//creates shape object
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));//adds start point to object
		shape.addPoint((int)(paneWidth*xEnd),(int)(paneHeight*yEnd));//adds end point to object
		shapes.add(shape);//adds shape to array
		shape.create();//constructs the shape group
		addId(id);//adds either unique id to array or "invalid id assigned"
	}
	//solid colour rectangle
	public void registerRectangle(float xStart, float yStart, float width, float height, String fillColour, String id) {
		Color fc = Color.web(fillColour);
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0);
		//adds the 4 points to the object
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*(yStart+height)));
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*(yStart+height)));
		
		shapes.add(shape);
		shape.create();
		addId(id);
		
	}
	//gradient colour rectangle
	public void registerRectangle(float xStart, float yStart, float width, float height, String id, float shading_x1, float shading_y1, String shading_colour1, float shading_x2, float shading_y2, String shading_colour2, Boolean shading_cyclic) {
		Color c1 = Color.web(shading_colour1);//creates first colour of gradient
		Color c2 = Color.web(shading_colour2);//creates the second colour of the gradient
		Shape shape = new Shape(paneWidth,paneHeight,0,c1,c2,(int)(paneWidth*shading_x1),(int)(paneHeight*shading_y1),(int)(paneWidth*shading_x2),(int)(paneHeight*shading_y2),shading_cyclic); //shape with gradient fill
		//adds the 4 points to the object
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*yStart));
		shape.addPoint((int)(paneWidth*(xStart+width)),(int)(paneHeight*(yStart+height)));
		shape.addPoint((int)(paneWidth*xStart),(int)(paneHeight*(yStart+height)));
		
		shapes.add(shape);
		shape.create();
		addId(id);

	}
	//creates solid oval
	public void registerOval(float xStart, float yStart, float width, float height, String fillColour, String id) {
		Color fc = Color.web(fillColour);
		Shape shape = new Shape(fc,fc,paneWidth,paneHeight,0);
		shape.drawOval((int)(paneWidth*width),(int)(paneHeight*height),(int)(paneWidth*xStart),(int)(paneHeight*yStart));//creates oval
		shapes.add(shape);
		shape.create();
		addId(id);
		
	}
	//creates gradient oval
	public void registerOval(float xStart, float yStart, float width, float height, String id, float shading_x1, float shading_y1, String shading_colour1, float shading_x2, float shading_y2, String shading_colour2, Boolean shading_cyclic) {
		Color c1 = Color.web(shading_colour1);
		Color c2 = Color.web(shading_colour2);
		Shape shape = new Shape(paneWidth,paneHeight,0,c1,c2,(int)(paneWidth*shading_x1),(int)(paneHeight*shading_y1),(int)(paneWidth*shading_x2),(int)(paneHeight*shading_y2),shading_cyclic);
		shape.drawOval((int)(paneWidth*width),(int)(paneHeight*height),(int)(paneWidth*xStart),(int)(paneHeight*yStart));
		shapes.add(shape);
		shape.create();
		addId(id);
		
	}
	public void undrawGraphic(String id) {//removes shape with the corresponding id
		if(id == "invalid id assigned") {
			return;
		}
		int i = ids.indexOf(id);// finds index in id arrayList
		if (i>=0) {
			if (pane.getChildren().contains(shapes.get(i).get())) {//checks if shape is drawn
				pane.getChildren().remove(shapes.get(i).get());//removes if drawn
			}
		}
		else {}
	}
	
	public void drawGraphic(String id) {//draws shape with corresponding id
		if(id == "invalid id assigned") {
			return;
		}
		int i = ids.indexOf(id);//finds index of id in id arrayList
		if (i>=0) {
			if (pane.getChildren().contains(shapes.get(i).get())==false) {//checks if shape is already drawn
				pane.getChildren().add(shapes.get(i).get());//draws the shape
			}	
		}
		else {}
	}
	
	private void addId(String id) {//used to validate the ID
		int length = ids.size();
		Boolean unique = true;
		for (int i=0;i<length;i++) {//checks id against all others in array list
			if(id == ids.get(i)) {
				unique = false;
				break;
			}
			else {}
		}
		if (unique == true) {//if unique original id is added to the arrayList
			ids.add(id);
		}
		else if(unique == false) {//if not unique then id is replaced with "invalid id assigned"
			ids.add("invalid id assigned");
		}
	}
}
