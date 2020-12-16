package cars_runner.view;

import cars_runner.model.Car;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

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

    public GameViewManager() {
        initializeStage();
        createKeyListeners();
    }

    private void createKeyListeners() {

        gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    isForwardKeyPressed = true;
                } else if (event.getCode() == KeyCode.DOWN) {
                    isBackKeyPressed = true;
                }
            }
        });

        gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.UP) {
                    isForwardKeyPressed = false;
                } else if (event.getCode() == KeyCode.DOWN) {
                    isBackKeyPressed = false;
                }
            }
        });

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
        createGameLoop();
        gameStage.show();
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

}
