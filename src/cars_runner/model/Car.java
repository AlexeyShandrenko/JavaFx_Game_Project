package cars_runner.model;

public enum Car {

    BLUE("/cars_runner/view/resources/carchooser/car_blue_4.png"),
    GREEN("/cars_runner/view/resources/carchooser/car_green_1.png"),
    RED("/cars_runner/view/resources/carchooser/car_red_1.png"),
    YELLOW("/cars_runner/view/resources/carchooser/car_yellow_4.png");

    private String urlCar;

    private Car(String urlCar) {
        this.urlCar = urlCar;
    }

    public String getUrlCar() {
        return this.urlCar;
    }

}
