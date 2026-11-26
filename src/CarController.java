import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * Its responsibilities are to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // The delay (ms) corresponds to 20 updates/second (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // TODO A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    public void startTimer() {
        this.timer.start();
    }

    /* Each step, the TimerListener moves all cars in the list and tells the
     * view to update its images. TODO Change this method to your needs.
     */
    private final int maxX = 800 - 100;
    private final int maxY = (800 - 240) -60;
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                car.move();
                int x = (int) Math.round(car.getPosX());
                int y = (int) Math.round(car.getPosY());
                // Stay within field
                if( x < 0 || x > maxX || y < 0 || y > maxY){
                    // Clamp makes it so that if value is below min (value, min, max) it returns min, if above max returns max
                    car.setPosition(Math.clamp(x, 0, maxX), Math.clamp(y, 0, maxY));
                    //we turn left twice to turn 180 degrees, could also use turn right
                    car.turnLeft();
                    car.turnLeft();
                }

                frame.drawPanel.moveit(car, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.revalidate();
                frame.drawPanel.repaint();
            }
        }
    }

    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars) {
            car.gas(gas);
        }
    }

    void brake(int amount){
        double brake = ((double) amount /100);
        for (Car car : cars) {
            car.brake(brake);
        }
    }

    void turnLeft(){
        for (Car car : cars){
            car.turnLeft();
        }
    }

    void turnRight(){
        for (Car car : cars){
            car.turnRight();
        }
    }

    void startAllCars(){
        for (Car car : cars){
            car.startEngine();
        }
    }

    void stopAllCars(){
        for (Car car : cars){
            car.stopEngine();
        }
    }

    void turboOn(){
        for (Car car : cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOn();
            }
        }
    }

    void turboOff(){
        for (Car car : cars){
            if(car instanceof Saab95 saab){
                saab.setTurboOff();
            }
        }
    }
}
