package cars_runner.model;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CarsRunnerButton extends Button {

    private final String FONT_PATH = "src/cars_runner/model/resources/ui_pack/fonts/kenvector_future.ttf";
    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/cars_runner/model/resources/ui_pack/img/red_button00.png');";
    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/cars_runner/model/resources/ui_pack/img/red_button01.png');";

    public CarsRunnerButton(String text) {
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setStyle(BUTTON_FREE_STYLE);
        initializeButtonListeners();
    }

    private void setButtonFont() {
        try {
            setFont(Font.loadFont(new FileInputStream(FONT_PATH), 20));
        } catch (FileNotFoundException e) {
            setFont(Font.font("Verdana", 20));
        }
    }

    private void setButtonPressedStyle() {
        setStyle(BUTTON_PRESSED_STYLE);
        setPrefHeight(45);
        setLayoutY(getLayoutY() + 4);
    }

    private void setButtonReleasedStyle() {
        setStyle(BUTTON_FREE_STYLE);
        setPrefHeight(49);
        setLayoutY(getLayoutY() - 4);
    }

    private void initializeButtonListeners() {
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonPressedStyle();
                }
            }
        });

        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)) {
                    setButtonReleasedStyle();
                }
            }
        });

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow());
            }
        });

        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }

//    private final String FONT_PATH = "/resources/kenvector_future.ttf";
//    private final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/resources/ui_pack/img/yellow_button01.png');";
//    private final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/resources/ui_pack/img/yellow_button00.png');";
//
//    public CarsRunnerButton(String text) {
//        setText(text);
//        setButtonFont();
//        setPrefWidth(190);
//        setPrefHeight(49);
//        setStyle(BUTTON_FREE_STYLE);
//        initializeButtonListeners();
//
//    }
//
//    private void setButtonFont() {
//
//        setFont(Font.loadFont(getClass().getResourceAsStream(FONT_PATH), 23));
//
//    }
//
//    private void setButtonPressedStyle() {
//        setStyle(BUTTON_PRESSED_STYLE);
//        setPrefHeight(45);
//        setLayoutY(getLayoutY() + 4);
//
//    }
//
//    private void setButtonReleasedStyle() {
//        setStyle(BUTTON_FREE_STYLE);
//        setPrefHeight(45);
//        setLayoutY(getLayoutY() - 4);
//
//    }
//
//    private void initializeButtonListeners() {
//
//        setOnMousePressed(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)) {
//                    setButtonPressedStyle();
//                }
//
//            }
//        });
//
//        setOnMouseReleased(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                if(event.getButton().equals(MouseButton.PRIMARY)) {
//                    setButtonReleasedStyle();
//                }
//
//            }
//        });
//
//        setOnMouseEntered(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                setEffect(new DropShadow());
//
//            }
//        });
//
//        setOnMouseExited(new EventHandler<MouseEvent>() {
//
//            @Override
//            public void handle(MouseEvent event) {
//                setEffect(null);
//
//            }
//        });
//
//
//
//
//
//
//
//
//
//
//
//
//    }

}
