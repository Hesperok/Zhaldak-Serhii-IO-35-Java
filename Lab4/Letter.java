/**
 * Клас, що представляє одну літеру.
 */
public class Letter {

    private final char value;

    public Letter(char value) {
        this.value = value;
    }

    public char getValue() {
        return value;
    }

    public boolean isVowel() {
        return "aeiouyAEIOUY".indexOf(value) >= 0;
    }
}
