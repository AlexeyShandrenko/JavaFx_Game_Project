package cars_runner.view;

import cars_runner.model.Car;
import cars_runner.model.SmallInfoLabel;
import cars_runner.model.SoundEffects;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Random;

public class GameViewManager {

    private AnchorPane gamePane;
    private Scene gameScene;
    private Stage gameStage;

    private static final int GAME_WIDTH = 600;
    private static final int GAME_HEIGHT = 800;

    private Stage menuStage;
    private ImageView car;

    private boolean isLeftKeyPressed;
    private boolean isRightKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "/cars_runner/view/resources/road.png";

    private final static String BLACK_CAR = "/cars_runner/view/resources/overcomes/car_black_2.png";
    private final static String BLUE_CAR = "/cars_runner/view/resources/overcomes/car_blue_2.png";
    private final static String GREEN_CAR = "/cars_runner/view/resources/overcomes/car_green_4.png";
    public final static String RED_CAR = "/cars_runner/view/resources/overcomes/car_red_1.png";
    private final static String YELLOW_CAR = "/cars_runner/view/resources/overcomes/car_yellow_3.png";
    public final static String BLUE_MOTORCYCLE = "/cars_runner/view/resources/overcomes/motorcycle_blue.png";
    private final static String GREEN_MOTORCYCLE = "/cars_runner/view/resources/overcomes/motorcycle_green.png";
    private final static String RED_MOTORCYCLE = "/cars_runner/view/resources/overcomes/motorcycle_red.png";
    private final static String OIL = "/cars_runner/view/resources/overcomes/oil.png";
    private final static String ROCK = "/cars_runner/view/resources/overcomes/rock3.png";

    private ImageView[] blackCars;
    private ImageView[] blueCars;
    private ImageView[] greenCars;
    private ImageView[] redCars;
    private ImageView[] yellowCars;
    private ImageView[] blueMotorcycles;
    private ImageView[] greenMotorcycles;
    private ImageView[] redMotorcycles;
    private ImageView[] oil;
    private ImageView[] rock;
    Random randomPositionGenerator;

    private ImageView fuel;
    private ImageView heart;
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife = 3;
    private int points;
    public final static String FUEL_IMAGE = "/cars_runner/view/resources/fuel.png";
    public final static String HEART_IMAGE = "/cars_runner/view/resources/platformPack_item017.png";

    private final static int FUEL_RADIUS = 12;
    private final static int HEART_RADIUS = 12;
    private final static int CAR_RADIUS = 27;
    private final static int OVERCOMES_RADIUS = 20;

    private static final String LIFE_LOST1 = "file:src/cars_runner/view/offf.mp3";
    private static final String LIFE_LOST2 = "file:src/cars_runner/view/NO.mp3";
    private static final String LIFE_ADD = "file:src/cars_runner/view/nice.mp3";

    MediaPlayer mediaPlayer;

    public void playMusic() {

        String track = "D:\\Repository\\ProjectAboutGameOnJavaFx\\JavaFx_Game_Project\\src\\cars_runner\\view\\gameplay_music.mp3";
        Media h = new Media(Paths.get(track).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();

    }

    public void stopMusic() {

        String track = "D:\\Repository\\ProjectAboutGameOnJavaFx\\JavaFx_Game_Project\\src\\cars_runner\\view\\gameplay_music.mp3";
        Media h = new Media(Paths.get(track).toUri().toString());
        mediaPlayer = new MediaPlayer(h);
//        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.stop();

    }

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
        randomPositionGenerator = new Random();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = true;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                    isLeftKeyPressed = false;
                } else if (event.getCode() == KeyCode.RIGHT) {
                    isRightKeyPressed = false;
                }
            }
        });

    }

    private void initializeStage() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);
        gameStage.setResizable(false);
    }

    public void createNewGame(Stage menuStage, Car choosenCar) {
        this.menuStage = menuStage;
        this.menuStage.hide();
        createBackground();
        createCar(choosenCar);
        createGameElements(choosenCar);
        createGameLoop();
        gameStage.show();
    }

    private void createGameElements(Car choosenCar) {
//        playerLife = 2;
        heart = new ImageView(HEART_IMAGE);
        setNewElementPosition(heart);
        gamePane.getChildren().add(heart);
        fuel = new ImageView(FUEL_IMAGE);
        setNewElementPosition(fuel);
        gamePane.getChildren().add(fuel);
        pointsLabel = new SmallInfoLabel("POINTS : 00");
        pointsLabel.setLayoutX(460);
        pointsLabel.setLayoutY(20);
        gamePane.getChildren().add(pointsLabel);
        playerLifes = new ImageView[3];
        for (int i = 0; i < playerLifes.length; i++) {
            playerLifes[i] = new ImageView(choosenCar.getUrlLife());
            playerLifes[i].setLayoutX(455 + (i * 50));
            playerLifes[i].setLayoutY(80);
            gamePane.getChildren().add(playerLifes[i]);
        }

        blackCars = new ImageView[1];
        for (int i = 0; i < blackCars.length; i++) {
            blackCars[i] = new ImageView(BLACK_CAR);
            setNewElementPosition(blackCars[i]);
            gamePane.getChildren().add(blackCars[i]);
        }

        blueCars = new ImageView[2];
        for (int i = 0; i < blueCars.length; i++) {
            blueCars[i] = new ImageView(BLUE_CAR);
            setNewElementPosition(blueCars[i]);
            gamePane.getChildren().add(blueCars[i]);
        }

        greenCars = new ImageView[2];
        for (int i = 0; i < greenCars.length; i++) {
            greenCars[i] = new ImageView(GREEN_CAR);
            setNewElementPosition(greenCars[i]);
            gamePane.getChildren().add(greenCars[i]);
        }

        redCars = new ImageView[2];
        for (int i = 0; i < redCars.length; i++) {
            redCars[i] = new ImageView(RED_CAR);
            setNewElementPosition(redCars[i]);
            gamePane.getChildren().add(redCars[i]);
        }

        greenMotorcycles = new ImageView[1];
        for (int i = 0; i < greenMotorcycles.length; i++) {
            greenMotorcycles[i] = new ImageView(GREEN_MOTORCYCLE);
            setNewElementPosition(greenMotorcycles[i]);
            gamePane.getChildren().add(greenMotorcycles[i]);
        }

        redMotorcycles = new ImageView[2];
        for (int i = 0; i < redMotorcycles.length; i++) {
            redMotorcycles[i] = new ImageView(RED_MOTORCYCLE);
            setNewElementPosition(redMotorcycles[i]);
            gamePane.getChildren().add(redMotorcycles[i]);
        }

        blueMotorcycles = new ImageView[1];
        for (int i = 0; i < blueMotorcycles.length; i++) {
            blueMotorcycles[i] = new ImageView(BLUE_MOTORCYCLE);
            setNewElementPosition(blueMotorcycles[i]);
            gamePane.getChildren().add(blueMotorcycles[i]);
        }
    }

    private void moveGameElements() {
        fuel.setLayoutY(fuel.getLayoutY() + 5);

        heart.setLayoutY(heart.getLayoutY() + 5);

        for (int i = 0; i < blackCars.length; i++) {
            blackCars[i].setLayoutY(blackCars[i].getLayoutY() + 15);
        }

        for (int i = 0; i < blueCars.length; i++) {
            blueCars[i].setLayoutY(blueCars[i].getLayoutY() + 12);
        }

        for (int i = 0; i < greenCars.length; i++) {
            greenCars[i].setLayoutY(greenCars[i].getLayoutY() + 14);
        }

        for (int i = 0; i < redCars.length; i++) {
            redCars[i].setLayoutY(redCars[i].getLayoutY() + 12);
        }

        for (int i = 0; i < greenMotorcycles.length; i++) {
            greenMotorcycles[i].setLayoutY(greenMotorcycles[i].getLayoutY() + 17);
        }

        for (int i = 0; i < redMotorcycles.length; i++) {
            redMotorcycles[i].setLayoutY(redMotorcycles[i].getLayoutY() + 20);
        }

        for (int i = 0; i < blueMotorcycles.length; i++) {
            blueMotorcycles[i].setLayoutY(blueMotorcycles[i].getLayoutY() + 16);
        }
    }

    private void checkIfElementAreBehindTheCarAndRelocated() {
        if (fuel.getLayoutY() > 1200) {
            setNewElementPosition(fuel);
        }

        if (heart.getLayoutY() > 1200) {
            setNewElementPosition(heart);
        }

        for (int i = 0; i < blackCars.length; i++) {
            if (blackCars[i].getLayoutY() > 900) {
                setNewElementPosition(blackCars[i]);
            }
        }

        for (int i = 0; i < blueCars.length; i++) {
            if (blueCars[i].getLayoutY() > 900) {
                setNewElementPosition(blueCars[i]);
            }
        }

        for (int i = 0; i < greenCars.length; i++) {
            if (greenCars[i].getLayoutY() > 900) {
                setNewElementPosition(greenCars[i]);
            }
        }

        for (int i = 0; i < redCars.length; i++) {
            if (redCars[i].getLayoutY() > 900) {
                setNewElementPosition(redCars[i]);
            }
        }

        for (int i = 0; i < greenMotorcycles.length; i++) {
            if (greenMotorcycles[i].getLayoutY() > 900) {
                setNewElementPosition(greenMotorcycles[i]);
            }
        }

        for (int i = 0; i < redMotorcycles.length; i++) {
            if (redMotorcycles[i].getLayoutY() > 900) {
                setNewElementPosition(redMotorcycles[i]);
            }
        }

        for (int i = 0; i < blueMotorcycles.length; i++) {
            if (blueMotorcycles[i].getLayoutY() > 900) {
                setNewElementPosition(blueMotorcycles[i]);
            }
        }
    }

    private void setNewElementPosition(ImageView image) {
        image.setLayoutX(randomPositionGenerator.nextInt(370));
        image.setLayoutY(-(randomPositionGenerator.nextInt(3200) + 600));
    }

    private void createCar(Car choosenCar) {
        car = new ImageView(choosenCar.getUrlCar());
        car.setLayoutX(GAME_WIDTH / 2);
        car.setLayoutY(GAME_HEIGHT - 110);
        gamePane.getChildren().add(car);
    }

    private void createGameLoop() {
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                moveBackground();
                moveGameElements();
                checkIfElementAreBehindTheCarAndRelocated();
                checkIfElementCollide();
                moveCar();
            }
        };

        gameTimer.start();

    }

    private void moveCar() {


        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            car.setRotate(angle);
            if (car.getLayoutX() > -20) {
                car.setLayoutX(car.getLayoutX() - 6);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            car.setRotate(angle);

            if (car.getLayoutX() < 522) {
                car.setLayoutX(car.getLayoutX() + 6);
            }
        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            car.setRotate(angle);
        }

        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            car.setRotate(angle);
        }
    }

    private void createBackground() {
        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImage1 = new ImageView(BACKGROUND_IMAGE);
            ImageView backgroundImage2 = new ImageView(BACKGROUND_IMAGE);
            GridPane.setConstraints(backgroundImage1, i % 3, i / 3);
            GridPane.setConstraints(backgroundImage2, i % 3, i / 3);
            gridPane1.getChildren().add(backgroundImage1);
            gridPane2.getChildren().add(backgroundImage2);
        }

        gridPane2.setLayoutY(-1024);
        gamePane.getChildren().addAll(gridPane1, gridPane2);

    }

    private void moveBackground() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 6);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 6);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

    private void checkIfElementCollide() {
        if (CAR_RADIUS + FUEL_RADIUS > calculateDistance(car.getLayoutX() + 49, fuel.getLayoutX() + 15, car.getLayoutY() + 37, fuel.getLayoutY() + 15)) {
            try {
                SoundEffects.playSoundAdd(new URI(LIFE_ADD));
            } catch (URISyntaxException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            setNewElementPosition(fuel);

            points += 2;
            String textToSet = "POINTS : ";
            if (points < 10) {
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points);

        }

        if (CAR_RADIUS + HEART_RADIUS > calculateDistance(car.getLayoutX() + 49, heart.getLayoutX() + 15, car.getLayoutY() + 37,
                heart.getLayoutY() + 15)) {
            try {
                SoundEffects.playSoundAdd(new URI(LIFE_ADD));
            } catch (URISyntaxException e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            setNewElementPosition(heart);
            points +=5;
            String textToset = "Points: ";
            if (points < 10) {
                textToset += "0";
            }
            pointsLabel.setText(textToset + points);
            addLife();
        }

        for (int i = 0; i < blackCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blackCars[i].getLayoutX() + 20, car.getLayoutY() + 37, blackCars[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost1(new URI(LIFE_LOST1));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(blackCars[i]);
            }
        }

        for (int i = 0; i < blueCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blueCars[i].getLayoutX() + 20, car.getLayoutY() + 37, blueCars[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost2(new URI(LIFE_LOST2));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(blueCars[i]);
            }
        }

        for (int i = 0; i < greenCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, greenCars[i].getLayoutX() + 20, car.getLayoutY() + 37, greenCars[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost1(new URI(LIFE_LOST1));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(greenCars[i]);
            }
        }

        for (int i = 0; i < redCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, redCars[i].getLayoutX() + 20, car.getLayoutY() + 37, redCars[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost1(new URI(LIFE_LOST1));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(redCars[i]);
            }
        }

        for (int i = 0; i < blueMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blueMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, blueMotorcycles[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost1(new URI(LIFE_LOST1));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(blueMotorcycles[i]);
            }
        }

        for (int i = 0; i < greenMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, greenMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, greenMotorcycles[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost2(new URI(LIFE_LOST2));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(greenMotorcycles[i]);
            }
        }

        for (int i = 0; i < redMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, redMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, redMotorcycles[i].getLayoutY() + 20)) {
                try {
                    SoundEffects.playSoundLost1(new URI(LIFE_LOST1));
                } catch (URISyntaxException e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                removeLife();
                setNewElementPosition(redMotorcycles[i]);
            }
        }
    }

    private void removeLife() {
        gamePane.getChildren().remove(playerLifes[playerLife - 1]);
        playerLife--;
        if (playerLife <= 0) {
            gameStage.close();
            gameTimer.stop();
            SaveScoreViewManager saveScoreViewManager = new SaveScoreViewManager();
            saveScoreViewManager.createScoreScene(menuStage, points, getBackgroundImage());
        }
    }

    private String getBackgroundImage(){
        return "/cars_runner/view/resources/parking.jpg";
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private void addLife() {
        if (playerLife < 3) {
            gamePane.getChildren().add(playerLifes[playerLife]);
            playerLife++;
        }
    }

}
