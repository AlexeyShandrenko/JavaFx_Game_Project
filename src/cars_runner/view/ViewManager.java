package cars_runner.view;

import cars_runner.model.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ViewManager {

    public final static String FONT_PATH = "src/cars_runner/model/resources/ui_pack/fonts/kenvector_future.ttf";
    private static final int WIDTH = 1160;
    private static final int HEIGHT = 904;
    private AnchorPane mainPane;
    public Scene mainScene;
    private Stage mainStage;

    private final static int MENU_BUTTONS_START_X = 100;
    private final static int MENU_BUTTONS_START_Y = 250;

    private CarsRunnerSubscene creditsSubscene;
    private CarsRunnerSubscene helpSubscene;
    private CarsRunnerSubscene scoreSubscene;
    private CarsRunnerSubscene carChooserSubscene;

    private CarsRunnerSubscene sceneToHide;

    List<CarsRunnerButton> menuButtons;

    List<CarPicker> carsList;
    private Car choosenCar;

    MediaPlayer mediaPlayer;

    private static final String fileString = "src/cars_runner/model/score.txt";


    public ViewManager() {
        menuButtons = new ArrayList<>();
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        createButtons();
        createBackground();
        createLogo();
        createSubscenes();
    }

    private void showSubScene(CarsRunnerSubscene subscene) {
        if (sceneToHide != null) {
            sceneToHide.moveSubScene();
        }

        subscene.moveSubScene();
        sceneToHide = subscene;
    }

    private void createSubscenes() {
//        creditsSubscene = new CarsRunnerSubscene();
//        mainPane.getChildren().add(creditsSubscene);


//        helpSubscene = new CarsRunnerSubscene();
//        mainPane.getChildren().add(helpSubscene);

//        scoreSubscene = new CarsRunnerSubscene();
//        mainPane.getChildren().add(scoreSubscene);

        createCarChooserSubScene();
        createScoreSubscene();
        createHelpSubScene();
        createCreditsSubscene();
    }

    private void createScoreSubscene() {
        scoreSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(scoreSubscene);

        InfoLabel message = new InfoLabel("SCORES");
        message.setLayoutX(120);
        message.setLayoutY(20);
        scoreSubscene.getPane().getChildren().add(message);
        try {
            File file = new File(fileString);
            if (!file.exists()) file.createNewFile();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                if (!line.trim().equals("")) {
                    TextLabel score = new TextLabel(line, 25);
                    score.setLayoutX(40);
                    score.setLayoutY(90 + i * 30);
                    scoreSubscene.getPane().getChildren().add(score);
                }
                i++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createHelpSubScene() {
        helpSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(helpSubscene);
        InfoLabel message = new InfoLabel("HELP");
        message.setLayoutX(120);
        message.setLayoutY(20);
        String text = "Choose your car and start the game \non the start tab.\n\n" +
                "Control your car with left and right.\n\n" +
                "Avoid overcomes and collect fuels and hearts.\n\n" +
                "Check your ranking on score tab.\n\n" +
                "Press the exit button if you want \nto quit the game.";

        TextLabel textLabel = new TextLabel(text, 17);
        textLabel.setLayoutX(40);
        textLabel.setLayoutY(90);
        helpSubscene.getPane().getChildren().addAll(message, textLabel);
    }

    private void createCreditsSubscene() {
        creditsSubscene = new CarsRunnerSubscene();
        mainPane.getChildren().add(creditsSubscene);

        InfoLabel credits = new InfoLabel("CREDITS");
        credits.setLayoutX(120);
        credits.setLayoutY(20);

        String text = "University project by Shandrenko Alexey";

        TextLabel textLabel = new TextLabel(text, 17);
        textLabel.setLayoutX(40);
        textLabel.setLayoutY(90);
        creditsSubscene.getPane().getChildren().addAll(credits, textLabel);

//        Label credit0 = new Label("University project by Alexey Shandrenko");
//        credit0.setAlignment(Pos.CENTER);
//        VBox creditsBox = new VBox(10, credit0);
//        creditsBox.setLayoutX(50);
//        creditsBox.setLayoutY(80);
//        creditsSubscene.getPane().getChildren().addAll(credits, creditsBox);
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
        createPlayButton();

//        createTwoPlayersButton();
        createScoresButton();
        createHelpButton();
        createCreditsButton();
        createExitButton();
    }

    private void createPlayButton() {
        CarsRunnerButton onePlayerButton = new CarsRunnerButton("PLAY");
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
