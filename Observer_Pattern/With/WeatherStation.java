package Observer_Pattern.With;

import java.util.ArrayList;
import java.util.List;

public class WeatherStation {
    double temperature;
    List<Observer> allDisplay = new ArrayList<>();
    WeatherStation(double temperature){
        this.temperature = temperature;
    }

    public void addObserver(Observer observer){
        this.allDisplay.add(observer);
    }

    public void removeObserver(Observer observer){
        this.allDisplay.remove(observer);
    }

    public void updateDisplay(){
        for(Observer observer : allDisplay){
            observer.updateDisplay(temperature);
        }
    }

    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation(25.0);
        weatherStation.addObserver(new MobileDisplay());
        weatherStation.addObserver(new LEDDisplay());
        weatherStation.updateDisplay();

        weatherStation = new WeatherStation(22.0);
        weatherStation.addObserver(new MobileDisplay());
        weatherStation.removeObserver(new LEDDisplay());
        weatherStation.updateDisplay();

        /**
         * Benefit:
         * 1. Decouple WeatherStation from display logic.
         * 2. New Display type can be added without modifying WeatherStation.
         * 3. Adheres to Open Closed Principle and Single Responsibilities Principle.
         */

    }
}

interface Observer{
    void updateDisplay(double temperature);
}
class MobileDisplay implements Observer{
    public void updateDisplay(double temperature) {
        System.out.println("Mobile Display Temerature : "+temperature);
    }
}

class LEDDisplay implements Observer{
    public void updateDisplay(double temperature) {
        System.out.println("LED Display Temerature : "+temperature);
    }
}
