package cars_runner.model;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;



public class CarPicker extends VBox {

    private ImageView  circleImage;
    private ImageView carImage;

    private String circleNotChoosen = "/cars_runner/view/resources/carchooser/grey_circle.png";
    private String circleChoosen = "/cars_runner/view/resources/carchooser/red_boxTick.png";

    private Car car;

    private boolean isCircleChoosen;

    public CarPicker(Car car) {
        circleImage = new ImageView(circleNotChoosen);
        carImage = new ImageView(car.getUrlCar());
        this.car = car;
        isCircleChoosen = false;
        this.setAlignment(Pos.CENTER);
        this.setSpacing(20);
        this.getChildren().add(circleImage);
        this.getChildren().add(carImage);
    }

    public Car getCar() {
        return car;
    }

    public boolean getIsCircleChoosen() {
        return isCircleChoosen;
    }

    public void setIsCircleChoosen(boolean isCircleChoosen) {
        this.isCircleChoosen = isCircleChoosen;
        String imageToSet = this.isCircleChoosen ? circleChoosen : circleNotChoosen;
        circleImage.setImage(new Image(imageToSet));
    }

}
