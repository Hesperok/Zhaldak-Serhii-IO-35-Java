import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Виконавчий клас лабораторної роботи.
 * Варіант: C13 = 11 (кава у фургоні).
 */
public class CoffeeVanApp {

    public static void main(String[] args) {
        try {
            // Створення фургону з максимальним обсягом
            Van van = new Van(50.0);

            // Додавання товарів
            van.addCoffee(new BeanCoffee("Arabica Beans", 10.0, 15.0, 8));
            van.addCoffee(new GroundCoffee("Espresso Ground", 8.0, 12.0, 7));
            van.addCoffee(new InstantCoffee("Instant Gold", 5.0, 20.0, 9));
            van.addCoffee(new BeanCoffee("Robusta Beans", 12.0, 10.0, 6));

            // Загальна вартість вантажу
            System.out.println("Загальна вартість кави: "
                    + van.calculateTotalPrice() + " грн");

            // Сортування за співвідношенням ціна / вага
            van.sortByPriceWeightRatio();

            System.out.println("\nКава після сортування (ціна/вага):");
            for (Coffee coffee : van.getCoffees()) {
                System.out.println(coffee);
            }

            // Пошук кави за діапазоном якості
            System.out.println("\nКава з якістю від 7 до 9:");
            for (Coffee coffee : van.findByQualityRange(7, 9)) {
                System.out.println(coffee);
            }

        } catch (Exception e) {
            System.out.println("Помилка виконання програми: " + e.getMessage());
        }
    }
}

/**
 * Узагальнений клас кави.
 */
abstract class Coffee {

    protected String name;
    protected double weight;
    protected double price;
    protected int quality;

    public Coffee(String name, double weight, double price, int quality) {
        this.name = name;
        this.weight = weight;
        this.price = price;
        this.quality = quality;
    }

    public double getPriceWeightRatio() {
        return price / weight;
    }

    public int getQuality() {
        return quality;
    }

    @Override
    public String toString() {
        return name +
                " | вага=" + weight +
                " | ціна=" + price +
                " | якість=" + quality;
    }
}

/**
 * Зернова кава.
 */
class BeanCoffee extends Coffee {

    public BeanCoffee(String name, double weight,
                      double price, int quality) {
        super(name, weight, price, quality);
    }
}

/**
 * Мелена кава.
 */
class GroundCoffee extends Coffee {

    public GroundCoffee(String name, double weight,
                        double price, int quality) {
        super(name, weight, price, quality);
    }
}

/**
 * Розчинна кава.
 */
class InstantCoffee extends Coffee {

    public InstantCoffee(String name, double weight,
                         double price, int quality) {
        super(name, weight, price, quality);
    }
}

/**
 * Клас фургону для перевезення кави.
 */
class Van {

    private final double maxVolume;
    private final List<Coffee> coffees = new ArrayList<>();
    private double currentVolume = 0.0;

    public Van(double maxVolume) {
        this.maxVolume = maxVolume;
    }

    public void addCoffee(Coffee coffee) {
        if (currentVolume + coffee.weight > maxVolume) {
            throw new IllegalArgumentException(
                    "Перевищено обсяг фургону");
        }
        coffees.add(coffee);
        currentVolume += coffee.weight;
    }

    public double calculateTotalPrice() {
        double sum = 0.0;
        for (Coffee coffee : coffees) {
            sum += coffee.price;
        }
        return sum;
    }

    public void sortByPriceWeightRatio() {
        coffees.sort(Comparator.comparingDouble(
                Coffee::getPriceWeightRatio));
    }

    public List<Coffee> findByQualityRange(int min, int max) {
        List<Coffee> result = new ArrayList<>();
        for (Coffee coffee : coffees) {
            if (coffee.getQuality() >= min &&
                coffee.getQuality() <= max) {
                result.add(coffee);
            }
        }
        return result;
    }

    public List<Coffee> getCoffees() {
        return coffees;
    }
}
