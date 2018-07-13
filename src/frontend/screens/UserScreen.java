package frontend.screens;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import backend.user.User;
import frontend.buttons.ButtonFactory;
import frontend.displays.StatsDisplay;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserScreen implements Screen {
	private StatsDisplay statsDisplay;
	private Stage myStage;
	private StackPane myPane;
	private Scene myScene; 
	public UserScreen(Stage stage, User user) {
		myStage = stage;
		setupScreen();
		setupContent(user);
		setupStage();
		
	}
	
	private void setupScreen() {
		myPane = new StackPane();
		myPane.setBackground(new Background(new BackgroundFill(DEFAULT_BACKGROUND, null, null)));
		myPane.setId("make_game_screen");
		myScene = new Scene(myPane);
		myScene.getStylesheets().add(STYLE_PATH);
	}
	
	private void setupContent(User user) {
		XStream xstream = new XStream(new DomDriver());
		VBox box = new VBox();
		box.getChildren().addAll(
				new Text("Welcome " + user.getName()) {{ setId("default_authoring_title"); }},
				new Text(user.getStats()) {{ setId("default_authoring_label");}},
				ButtonFactory.makeButton("New Game", e-> new GameStartScreen(myStage, user), "image_button"),
				ButtonFactory.makeButton("Load Game", e -> loadGame(user), "image_button"),
				ButtonFactory.makeButton("Go To Settings", e -> new SettingsScreen(myStage, user), "image_button")
				);
		box.setAlignment(Pos.CENTER_LEFT);
		box.setPadding(new Insets(0, 0, 0, 30));
		box.setSpacing(SPACING_SMALL);
		myPane.getChildren().add(box);
	}
	
	private void setupStage() {
		myStage.setScene(myScene);
		myStage.setTitle("User Screen");
		myStage.setWidth(INITIAL_SCENE_WIDTH);
		myStage.setHeight(INITIAL_SCENE_HEIGHT);
		myStage.setResizable(false);
		myStage.show();
	}
	
	private void loadGame(User user) {
		//TODO figure out how to specify games to load (by id?)
		new ReplayScreen(myStage, user);
	}
}
