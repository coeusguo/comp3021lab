package lab10;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * 
 * COMP 3021
 * 
 * This is a unresponsive JavaFX GUI Once you press start, the program starts to print hello! 
 * to the console and the GUI becomes unresponsive and therefore you cannot press stop button
 * 
 * We need to use threads!
 * 
 * @author valerio
 *
 */
public class UnresponsiveUI extends Application {

	final static int SCENE_WIDTH = 300;
	final static int SCENE_HEIGHT = 300;
	boolean stop = true;
	printHello p;
	Thread t;


	public static void main(String[] args) {
		launch(UnresponsiveUI.class, args);
	}

	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(addHBox(), SCENE_WIDTH, SCENE_HEIGHT);
		stage.setScene(scene);
		stage.setTitle("Example of unresponsive UI");
		stage.show();
	}

	/**
	 * This create the top section
	 * 
	 * @return
	 */
	private HBox addHBox() {

		HBox hbox = new HBox();
		hbox.setPadding(new Insets(15, 12, 15, 12));
		hbox.setSpacing(10); // Gap between nodes

		Button buttonStart = new Button("START");
		buttonStart.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				p = new printHello();
				t = new Thread(p);
				t.start();
			}
		});

		Button buttonStop = new Button("STOP");
		buttonStop.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
					p.set(true);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Successfully stopped");
					alert.setContentText("Now the GUI is responsive, congratulations");
					alert.showAndWait().ifPresent(rs -> {
						if (rs == ButtonType.OK) {
							System.out.println("Pressed OK.");
						}
					});
				
			}
		});

		hbox.getChildren().addAll(buttonStart, buttonStop);
		return hbox;
	}
}

class printHello implements Runnable{
	private  boolean stop = false;
	@Override
	public void run(){
		
			while(!stop){
				System.out.println("hello!");
			}
		
	}
	public void set(boolean s){
		stop = s;
	}
}