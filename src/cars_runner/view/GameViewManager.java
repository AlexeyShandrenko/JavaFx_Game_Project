package cars_runner.view;

import cars_runner.model.Car;
import cars_runner.model.SmallInfoLabel;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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
    private boolean isForwardKeyPressed;
    private boolean isBackKeyPressed;
    private int angle;
    private AnimationTimer gameTimer;

    private GridPane gridPane1;
    private GridPane gridPane2;
    private final static String BACKGROUND_IMAGE = "/cars_runner/view/resources/road.png";

    private final static String BLACK_CAR = "/cars_runner/view/resources/overcomes/car_black_2.png";
    private final static String BLUE_CAR = "/cars_runner/view/resources/overcomes/car_blue_2.png";
    private final static String GREEN_CAR = "/cars_runner/view/resources/overcomes/car_green_4.png";
    private final static String RED_CAR = "/cars_runner/view/resources/overcomes/car_red_1.png";
    private final static String YELLOW_CAR = "/cars_runner/view/resources/overcomes/car_yellow_3.png";
    private final static String BLUE_MOTORCYCLE = "/cars_runner/view/resources/overcomes/motorcycle_blue.png";
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
    private SmallInfoLabel pointsLabel;
    private ImageView[] playerLifes;
    private int playerLife;
    private int points;
    private final static String FUEL_IMAGE = "/cars_runner/view/resources/platformPack_item017.png";

    private final static int FUEL_RADIUS = 12;
    private final static int CAR_RADIUS = 27;
    private final static int OVERCOMES_RADIUS = 20;

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
        playerLife = 2;
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

        for (int i = 0; i < blackCars.length; i++) {
            blackCars[i].setLayoutY(blackCars[i].getLayoutY() + 7);
        }

        for (int i = 0; i < blueCars.length; i++) {
            blueCars[i].setLayoutY(blueCars[i].getLayoutY() + 7);
        }

        for (int i = 0; i < greenCars.length; i++) {
            greenCars[i].setLayoutY(greenCars[i].getLayoutY() + 7);
        }

        for (int i = 0; i < redCars.length; i++) {
            redCars[i].setLayoutY(redCars[i].getLayoutY() + 7);
        }

        for (int i = 0; i < greenMotorcycles.length; i++) {
            greenMotorcycles[i].setLayoutY(greenMotorcycles[i].getLayoutY() + 7);
        }

        for (int i = 0; i < redMotorcycles.length; i++) {
            redMotorcycles[i].setLayoutY(redMotorcycles[i].getLayoutY() + 7);
        }

        for (int i = 0; i < blueMotorcycles.length; i++) {
            blueMotorcycles[i].setLayoutY(blueMotorcycles[i].getLayoutY() + 7);
        }
    }

    private void checkIfElementAreBehindTheCarAndRelocated() {
        if (fuel.getLayoutY() > 1200) {
            setNewElementPosition(fuel);
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
                car.setLayoutX(car.getLayoutX() - 3);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            car.setRotate(angle);

            if (car.getLayoutX() < 522) {
                car.setLayoutX(car.getLayoutX() + 3);
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
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 4);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 4);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }

        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

    private void checkIfElementCollide() {
        if (CAR_RADIUS + FUEL_RADIUS > calculateDistance(car.getLayoutX() + 49, car.getLayoutX() + 15, car.getLayoutY() + 37, car.getLayoutY() + 15)) {
            setNewElementPosition(fuel);

            points++;
            String textToSet = "POINTS : ";
            if (points < 10) {
                textToSet = textToSet + "0";
            }
            pointsLabel.setText(textToSet + points);
        }

        for (int i = 0; i < blackCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blackCars[i].getLayoutX() + 20, car.getLayoutY() + 37, blackCars[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(blackCars[i]);
            }
        }

        for (int i = 0; i < blueCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blueCars[i].getLayoutX() + 20, car.getLayoutY() + 37, blueCars[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(blueCars[i]);
            }
        }

        for (int i = 0; i < greenCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, greenCars[i].getLayoutX() + 20, car.getLayoutY() + 37, greenCars[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(greenCars[i]);
            }
        }

        for (int i = 0; i < redCars.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, redCars[i].getLayoutX() + 20, car.getLayoutY() + 37, redCars[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(redCars[i]);
            }
        }

        for (int i = 0; i < blueMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, blueMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, blueMotorcycles[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(blueMotorcycles[i]);
            }
        }

        for (int i = 0; i < greenMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, greenMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, greenMotorcycles[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(greenMotorcycles[i]);
            }
        }

        for (int i = 0; i < redMotorcycles.length; i++) {
            if (OVERCOMES_RADIUS + CAR_RADIUS > calculateDistance(car.getLayoutX() + 49, redMotorcycles[i].getLayoutX() + 20, car.getLayoutY() + 37, redMotorcycles[i].getLayoutY() + 20)) {
                removeLife();
                setNewElementPosition(redMotorcycles[i]);
            }
        }
    }

    private void removeLife() {
        gamePane.getChildren().remove(playerLifes[playerLife]);
        playerLife--;
        if (playerLife < 0) {
            gameStage.close();
            gameTimer.stop();
            menuStage.show();
        }
    }

    private double calculateDistance(double x1, double x2, double y1, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
