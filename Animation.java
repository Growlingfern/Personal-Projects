/**
 * 
 */
package Week78;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
/**
 * @author kp013494
 *
 */
public class Animation extends Application {
	private int canvasSize = 512;
	private double orbitSize = canvasSize / 3;
	private double sunSize = 80;
	private double earthSize = 30;
	private GraphicsContext gc;
	private Image earth = new Image(getClass().getResourceAsStream("earth.png"));
	private Image sun = new Image(getClass().getResourceAsStream("sun.png"));
	
	
	public void drawIt (Image i, double x, double y, double sz){
		gc.drawImage(i,  x - sz/2,  y - sz/2, sz, sz);
	}
	
	private void drawSystem(double x, double y) {
		gc.clearRect(0,  0 ,  canvasSize,  canvasSize);
		drawIt(sun, canvasSize/2, canvasSize/2, sunSize);
		drawIt(earth, x, y, earthSize);
	}
	
	private void drawSystem(double t){
		double x = canvasSize/2 + orbitSize * Math.cos(t);
		double y = canvasSize/2 + orbitSize * Math.sin(t);
		drawSystem(x,y);
	}
	
	public void start(Stage stagePrimary) throws Exception {
		// TODO Auto-generated method stub
		stagePrimary.setTitle("Solar System");
		
		Group root = new Group();
		Canvas canvas = new Canvas(canvasSize, canvasSize);
		
		root.getChildren().add(canvas);
		gc = canvas.getGraphicsContext2D();
		
		Scene scene = new Scene(root);
		stagePrimary.setScene(scene);
		
		final long startNanoTime = System.nanoTime();
		
		new AnimationTimer() {
			public void handle(long currentNanoTime) {
				double t = (currentNanoTime - startNanoTime) / 1000000000.0;
				
				drawSystem(t);
			}
		}.start();
		stagePrimary.show();
	}
	public static void main(String[] args) {
		Application.launch(args); // launch the GUI
	}
}
