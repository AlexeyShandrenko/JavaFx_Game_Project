package cars_runner.application;

import cars_runner.model.SoundEffects;
import cars_runner.sockets.SocketClient;
import cars_runner.view.GameViewManager;
import cars_runner.view.ViewManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {


        GameViewManager gameViewManager = new GameViewManager();
        gameViewManager.playMusic();

        SocketClient client = new SocketClient("localhost", 7477);


        try {
            ViewManager manager = new ViewManager();
            primaryStage = manager.getMainStage();
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}
