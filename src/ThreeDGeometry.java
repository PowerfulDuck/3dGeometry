
import javafx.scene.image.*;
import javafx.animation.Animation;
import javafx.animation.RotateTransition;
import javafx.application.*;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
public class ThreeDGeometry extends Application {
 
	Button btn1;
	Button btn2;
	Button btn3;
	Button btn4;
	Label lbl;
	VBox pane;
	Box box;
	MeshView pyramid;
	Cylinder cylinder;
	Sphere sphere;
	VBox pane2;
	PerspectiveCamera camera = new PerspectiveCamera(true);
	
	private static final double CONTROL_MULTIPLIER = 0.1;
	private static final double SHIFT_MULTIPLIER = 10.0;
	private static final double MOUSE_SPEED = 0.1;
	private static final double ROTATION_SPEED = 2.0;
	private static final double TRACK_SPEED = 0.3;
	private static final double CAMERA_INITIAL_DISTANCE = -450;
    private static final double CAMERA_INITIAL_X_ANGLE = 70.0;
    private static final double CAMERA_INITIAL_Y_ANGLE = 320.0;
    private static final double CAMERA_NEAR_CLIP = 0.1;
    private static final double CAMERA_FAR_CLIP = 10000.0;
	 double mousePosX;
	    double mousePosY;
	    double mouseOldX;
	    double mouseOldY;
	    double mouseDeltaX;
	    double mouseDeltaY;
	    final Xform cameraXform = new Xform();
	    final Xform cameraXform2 = new Xform();
	    final Xform cameraXform3 = new Xform();
	
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		PhongMaterial blueStuff = new PhongMaterial();
		blueStuff.setDiffuseColor(Color.SPRINGGREEN);
		blueStuff.setSpecularColor(Color.GREEN);
		Image nirnImage = new Image("/nirn.jpg", true);
		PhongMaterial nirn = new PhongMaterial();
		nirn.setDiffuseMap(nirnImage);
	
		
		lbl = new Label();
		lbl.setPrefWidth(400);
		lbl.setText("Выберите фигуру");
		
		btn1 = new Button();
		btn1.setText("Прямоугольник");
		btn1.setOnAction(e -> buttonClick1());
		
		btn2 = new Button();
		btn2.setText("Пирамида");
		btn2.setOnAction(e -> buttonClick2());
		
		btn3 = new Button();
		btn3.setText("Цилиндр");
		btn3.setOnAction(e -> buttonClick3());
		
		btn4 = new Button();
		btn4.setText("Сфера");
		btn4.setOnAction(e -> buttonClick4());
		
		box = new Box(200,300,200);
		box.setMaterial(blueStuff);
		box.setTranslateX(500);
		Rotate rxBox = new Rotate (0,0,0,0,Rotate.X_AXIS);
		Rotate ryBox = new Rotate (0,0,0,0,Rotate.Y_AXIS);
		Rotate rzBox = new Rotate (0,0,0,0,Rotate.Z_AXIS);
		
		rxBox.setAngle(0);
		ryBox.setAngle(50);
		rzBox.setAngle(90);
		
		box.getTransforms().addAll(rxBox , ryBox , rzBox);
		
		RotateTransition rt1 = new RotateTransition();
		rt1.setNode(box);
		rt1.setDuration(Duration.millis(3000));
		rt1.setAxis(Rotate.Y_AXIS);
		rt1.setByAngle(360);
		rt1.setCycleCount(Animation.INDEFINITE);
		rt1.play();
		
		
		TriangleMesh pyramidMesh = new TriangleMesh();
		pyramidMesh.getTexCoords().addAll(0,0);
		float h = 400;
		float s =550;
		pyramidMesh.getPoints().addAll(
		0,0,0,
		0, h, -s/2,
		-s/2, h, 0,
		s/2, h, 0,
		0, h, s/2
				);
		pyramidMesh.getFaces().addAll(
		0,0, 2,0, 1,0,
		0,0, 1,0, 3,0,
		0,0, 3,0, 4,0,
		0,0, 4,0, 2,0,
		4,0, 1,0, 2,0,
		4,0, 3,0, 1,0
				);

		pyramid = new MeshView(pyramidMesh);
		pyramid.setDrawMode(DrawMode.FILL);
		pyramid.setMaterial(blueStuff);
		pyramid.setTranslateX(350);
		pyramid.setTranslateY(-100);
		
		RotateTransition rt2 = new RotateTransition();
		rt2.setNode(pyramid);
		rt2.setDuration(Duration.millis(3000));
		rt2.setAxis(Rotate.Y_AXIS);
		rt2.setByAngle(360);
		rt2.setCycleCount(Animation.INDEFINITE);
		rt2.play();
		
		
		cylinder = new Cylinder(100,400);
		cylinder.setMaterial(blueStuff);
		cylinder.setTranslateX(500);
		cylinder.setTranslateY(-50);
		RotateTransition rt3 = new RotateTransition();
		rt3.setNode(cylinder);
		rt3.setDuration(Duration.millis(3000));
		rt3.setAxis(Rotate.X_AXIS);
		rt3.setByAngle(360);
		rt3.setCycleCount(Animation.INDEFINITE);
		rt3.play();
		
		
		sphere = new Sphere(200);
		sphere.setMaterial(nirn);
		sphere.setTranslateX(400);
		sphere.setTranslateY(-50);
		RotateTransition rt4 = new RotateTransition();
		rt4.setNode(sphere);
		rt4.setDuration(Duration.millis(3000));
		rt4.setAxis(Rotate.Y_AXIS);
		rt4.setByAngle(360);
		rt4.setCycleCount(Animation.INDEFINITE);
		rt4.play();
		
		
		pane = new VBox();
		pane.setSpacing(10);
		pane.getChildren().addAll(lbl, btn1, btn2, btn3, btn4);
		
		pane2 = new VBox();
		pane.setSpacing(10);
	
		
		Scene scene = new Scene (pane,1200,720);
		Scene scene2 = new Scene(pane2,1200,720);
		
		PerspectiveCamera camera = new PerspectiveCamera(true);
		camera.setTranslateZ(-1000);
		camera.setTranslateX(600);
		camera.setTranslateY(350);
		camera.setNearClip(0.1);
		camera.setFarClip(2000.0);
		
		camera.setFieldOfView(40);
		scene.setCamera(camera);
		
		handleMouse(scene, camera);
		buildCamera();
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("3D Геометрия");
		primaryStage.show();
		
	}
	
	private void buildCamera() {
        pane.getChildren().add(cameraXform);
        cameraXform.getChildren().add(cameraXform2);
        cameraXform2.getChildren().add(cameraXform3);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);
 
        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);
    }

	private void handleMouse(Scene scene, final Node root) {
		 
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();
            }
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent me) {
                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX); 
                mouseDeltaY = (mousePosY - mouseOldY);

               double modifier = 1.0;
               double modifierFactor = 1.0;


               if (me.isControlDown()) {
                    modifier = CONTROL_MULTIPLIER;
                    modifierFactor = CONTROL_MULTIPLIER;
                } 
                if (me.isShiftDown()) {
                    modifier = SHIFT_MULTIPLIER;
                    modifierFactor = SHIFT_MULTIPLIER;
                }     
                if (me.isPrimaryButtonDown()) {
                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() -
                       mouseDeltaX*modifierFactor*modifier*ROTATION_SPEED);  // 
                   cameraXform.rx.setAngle(cameraXform.rx.getAngle() +
                       mouseDeltaY*modifierFactor*modifier*ROTATION_SPEED);  // -
                }
                else if (me.isSecondaryButtonDown()) {
                    double z = camera.getTranslateZ();
                    double newZ = z + mouseDeltaX*MOUSE_SPEED*modifier;
                    camera.setTranslateZ(newZ);
                }
                else if (me.isMiddleButtonDown()) {
                   cameraXform2.t.setX(cameraXform2.t.getX() + 
                      mouseDeltaX*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                   cameraXform2.t.setY(cameraXform2.t.getY() + 
                      mouseDeltaY*MOUSE_SPEED*modifier*TRACK_SPEED);  // -
                }
           }
       }); 
   } 
	private void buttonClick1() {
		pane.getChildren().removeAll(box,pyramid,cylinder,sphere);
		pane.getChildren().add(box);
	}
	
    private void buttonClick2() {
    	pane.getChildren().removeAll(box,pyramid,cylinder,sphere);
	    pane.getChildren().add(pyramid);
	}
    
    private void buttonClick3() {
    	pane.getChildren().removeAll(box,pyramid,cylinder,sphere);
		pane.getChildren().add(cylinder);
	}
    
    private void buttonClick4() {
    	pane.getChildren().removeAll(box,pyramid,cylinder,sphere);
    	pane.getChildren().add(sphere);
	}

}
