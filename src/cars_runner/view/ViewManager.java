package cars_runner.view;

import cars_runner.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private CarsRunnerSubscene creditsSubscene;
    private CarsRunnerSubscene helpSubscene;
    private CarsRunnerSubscene scoreSubscene;
    private CarsRunnerSubscene carChooserSubscene;

    private CarsRunnerSubscene sceneToHide;

    List<CarsRunnerButton> menuButtons;

    List<CarPicker> carsList;
    private Car choosenCar;

    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createSubscenes();
        createButtons();
        createBackground();
        createLogo();
    }

    private void showSubScene(CarsRunnerSubscene subscene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subscene.moveSubScene();
        sceneToHide = subscene;
    }

    private void createSubscenes() {
        creditsSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(creditsSubscene);

        helpSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(helpSubscene);

        scoreSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(scoreSubscene);

//        carChooserSubscene = new CarsRunnerSubscene();
//        mainPane.getChildren().add(carChooserSubscene);

        createCarChooserSubScene();
    }

    private void createCarChooserSubScene() {
        carChooserSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(carChooserSubscene);

        InfoLabel chooseCarLabel = new InfoLabel("CHOOSE YOUR CAR");
        chooseCarLabel.setLayoutX(110);
        chooseCarLabel.setLayoutY(25);
        carChooserSubscene.getPane().getChildren().add(chooseCarLabel);
        carChooserSubscene.getPane().getChildren().add(createCarsToChoose());
        carChooserSubscene.getPane().getChildren().add(createButtonToStart());

    }

    private HBox createCarsToChoose() {
        HBox box = new HBox();
        box.setSpacing(20);
        carsList = new ArrayList<>();
        for (Car car : Car.values()) {
            CarPicker carToPick = new CarPicker(car);
            carsList.add(carToPick);
            box.getChildren().add(carToPick);
            carToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    for (CarPicker car : carsList) {
                        car.setIsCircleChoosen(false);
                    }
                    carToPick.setIsCircleChoosen(true);
                    choosenCar = carToPick.getCar();
                }
            });
        }
        box.setLayoutX(130);
        box.setLayoutY(100);
        return box;
    }

    private CarsRunnerButton createButtonToStart() {
        CarsRunnerButton startButton = new CarsRunnerButton("START");
        startButton.setLayoutX(350);
        startButton.setLayoutY(300);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (choosenCar != null) {
                    GameViewManager gameViewManager = new GameViewManager();
                    gameViewManager.createNewGame(mainStage, choosenCar);
                }

            }
        });

        return startButton;
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

        onePlayerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(carChooserSubscene);
            }
        });
    }

    private void createTwoPlayersButton() {
        CarsRunnerButton twoPlayerButton = new CarsRunnerButton("2 PLAYERS");
        addMenuButton(twoPlayerButton);
    }

    private void createScoresButton() {
        CarsRunnerButton scoresButton = new CarsRunnerButton("SCORES");
        addMenuButton(scoresButton);

        scoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(scoreSubscene);
            }
        });
    }

    private void createHelpButton() {
        CarsRunnerButton helpButton = new CarsRunnerButton("HELP");
        addMenuButton(helpButton);

        helpButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(helpSubscene);
            }
        });
    }

    private void createCreditsButton() {
        CarsRunnerButton creditsButton = new CarsRunnerButton("CREDITS");
        addMenuButton(creditsButton);

        creditsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showSubScene(creditsSubscene);
            }
        });
    }

    private void createExitButton() {
        CarsRunnerButton exitButton = new CarsRunnerButton("EXIT");
        addMenuButton(exitButton);

        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mainStage.close();
            }
        });
    }

    private void createBackground() {
        Image backgroundImage = new Image("/cars_runner/view/resources/menu_background.jpg", 1160, 904, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }

    private void createLogo() {
        ImageView logo = new ImageView("/cars_runner/view/resources/game_logo.png");
        logo.setLayoutX(120);
        logo.setLayoutY(5);

        logo.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(new DropShadow());
            }
        });

        logo.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logo.setEffect(null);
            }
        });

        mainPane.getChildren().add(logo);

    }

}
