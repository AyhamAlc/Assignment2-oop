import java.awt.*;

public class Saab95 extends Car {

    // only the saab has a turbo so it lives here instead of Car
    private boolean turboOn;

    public Saab95() {
        super(2, Color.red, 125, "Saab95");
        turboOn = false;
    }

    public void setTurboOn() {
        turboOn = true;
    }
    public void setTurboOff() {
        turboOn = false;
    }

    // the speed calculation for the saab as it differs from the volvo, thus living here instead of Car
    @Override
    protected double speedFactor() {
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return getEnginePower() * 0.01 * turbo;
    }
}
