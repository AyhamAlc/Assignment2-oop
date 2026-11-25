import java.awt.*;

/**
 * all shared information between cars is kept here to avoid writing duplicate code
 * we make Car abstract as there is no need to be able to make a generic car object
 * new car models would be added as separate classes
 */

public abstract class Car implements Movable {

    // private so that the state can only change through this classes method
    private int nrDoors;
    private double enginePower;
    private double currentSpeed;
    private Color color;
    private String modelName;
    private double posX = 0;
    private double posY = 0;
    // 0 = north, 1 = east, 2 = south, 3 = west
    private int direction = 0;

    // speedFactor is different between the car models, so each model writes its own
    // protected because if we add new car models in any package they should be able to override
    protected abstract double speedFactor();

    public Car(int nrDoors, Color color, double enginePower, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        stopEngine();
    }

    // public so that outside code can read the values but not change them
    public int getNrDoors() {
        return nrDoors;
    }
    public double getEnginePower() { return enginePower; }
    boolean stillValid;
    public double getCurrentSpeed() {
        return currentSpeed;}
    public Color getColor() { return color; }
    public void setColor(Color clr) { color = clr; }
    public void startEngine() { currentSpeed = 0.1; }
    public void stopEngine() { currentSpeed = 0; }
    public int getDirection() { return direction; }
    public double getPosX() { return posX; }
    public double getPosY() { return posY; }

    public void setPosition(double x, double y){
        posX = x;
        posY = y;
    }


    // private so that the only way for outside code to change the speed is with gas and brake
    private void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }
    private void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }


    private  double currentGas = 0.0;
    // public because this is how a user would change the speed of the car
    public void gas(double amount) {
        if(amount < 0.0 || amount > 1){
            System.out.println("NOOOO " + amount + " is Invalid");
        }
        else{
            System.out.println("YEEEEES " + amount + " is Valid");
            incrementSpeed(amount);
        }
    }

    public void brake(double amount) {
        if(amount < 0.0 || amount > 1){
            System.out.println("NOOOO " + amount + " is  Invalid");
        }
        else{
            System.out.println("YEEEEES " + amount + " is  Valid");
            decrementSpeed(amount);
        }
    }

    @Override
    public void move() {
        if (direction == 0) {
            posY += currentSpeed;
        } else if (direction == 1) {
            posX += currentSpeed;
        } else if (direction == 2) {
            posY -= currentSpeed;
        } else if (direction == 3) {
            posX -= currentSpeed;
        }
    }

    @Override
    public void turnRight() {
        System.out.println("Turning right...");
        if (direction == 0) {
            direction = 1;
        } else if (direction == 1) {
            direction = 2;
        } else if (direction == 2) {
            direction = 3;
        } else {
            direction = 0;
        }
    }

    @Override
    public void turnLeft() {
        System.out.println("Turning left...");
        if (direction == 0) {
            direction = 3;
        } else if (direction == 1) {
            direction = 0;
        } else if (direction == 2) {
            direction = 1;
        } else {
            direction = 2;
        }
    }
}