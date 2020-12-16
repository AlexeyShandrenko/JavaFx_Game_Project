package cars_runner.view;

import cars_runner.model.CarsRunnerButton;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    private static final int WIDTH = 1160;
    private static final int HEIGHT = 904;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 150;

    List<CarsRunnerButton> menuButtons;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
    }

    public Stage getMainStage() {
        return mainStage;
    }

    private void addMenuButton(CarsRunnerButton button) {
        button.setLayoutX(MENU_BUTTONS_START_X);
        button.setLayoutY(MENU_BUTTONS_START_Y + menuButtons.size() * 100);
        menuButtons.add(button);
        mainPane.getChildren().add(button);
    }

    private void createButtons() {
        createOnePlayerButton();
        createTwoPlayersButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createOnePlayerButton() {
        CarsRunnerButton onePlayerButton = new CarsRunnerButton("1 PLAYER");
        addMenuButton(onePlayerButton);
    }

    private void createTwoPlayersButton() {
        CarsRunnerButton twoPlayerButton = new CarsRunnerButton("2 PLAYERS");
        addMenuButton(twoPlayerButton);
    }

    private void createScoresButton() {
        CarsRunnerButton scoresButton = new CarsRunnerButton("SCORES");
        addMenuButton(scoresButton);
    }

    private void createHelpButton() {
        CarsRunnerButton helpButton = new CarsRunnerButton("HELP");
        addMenuButton(helpButton);
    }

    private void createCreditsButton() {
        CarsRunnerButton creditsButton = new CarsRunnerButton("CREDITS");
        addMenuButton(creditsButton);
    }

    private void createExitButton() {
        CarsRunnerButton exitButton = new CarsRunnerButton("EXIT");
        addMenuButton(exitButton);
    }

    private void createBackground() {
        Image backgroundImage = new Image("/cars_runner/view/resources/menu_background.jpg", 1160, 904, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

}
