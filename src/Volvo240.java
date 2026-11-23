import java.awt.*;

public class Volvo240 extends Car {

    // trimFactor is unique to the volvo so it is private and lives here rather than in Car
    private final static double trimFactor = 1.25;

    public Volvo240() {
        super(4, Color.black, 100, "Volvo240");
    }

    // the speed calculation for the volvo as it differs from the saab, thus living here instead of Car
    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
