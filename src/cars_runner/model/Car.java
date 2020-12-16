package cars_runner.model;

public enum Car {

    BLUE("/cars_runner/view/resources/carchooser/car_blue_4.png", "/cars_runner/view/resources/carchooser/car_blue_small_5.png"),
    GREEN("/cars_runner/view/resources/carchooser/car_green_1.png", "/cars_runner/view/resources/carchooser/car_green_small_2.png"),
    RED("/cars_runner/view/resources/carchooser/car_red_1.png", "/cars_runner/view/resources/carchooser/car_red_small_3.png"),
    YELLOW("/cars_runner/view/resources/carchooser/car_yellow_4.png", "/cars_runner/view/resources/carchooser/car_yellow_small_4.png");

    private String urlCar;
    private String urlLife;

    private Car(String urlCar, String urlLife) {
        this.urlCar = urlCar;
        this.urlLife = urlLife;
    }

    public String getUrlCar() {
        return this.urlCar;
    }

    public String getUrlLife() {
        return this.urlLife;
    }

}
