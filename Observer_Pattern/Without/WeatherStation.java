package Observer_Pattern.Without;

public class WeatherStation {
    double temperature;
    WeatherStation(double temperature){
        this.temperature = temperature;
    }

    public void updateDisplay(){
        System.out.println("Mobile Display : "+temperature);
        System.out.println("LED Display :"+temperature);
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation(25.0);
        weatherStation.updateDisplay();

        /**
         * Problems:
         * 1. Tight coupling between WeatherStation and display logic.
         * 2. Adding/removing displays require code change.
         * 3. Violates Open closed Principle and Single Responsibilities Principle.
         */
    }
}
