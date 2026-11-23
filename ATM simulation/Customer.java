public class Customer {
    private final String name;
    private final String pin;

    public Customer(String name, String pin) {
        this.name = name;
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public boolean authenticatePin(String inputPin) {
        return pin.equals(inputPin);
    }
}