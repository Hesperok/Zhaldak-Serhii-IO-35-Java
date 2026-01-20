import java.util.Arrays;
import java.util.Comparator;

/**
 * Головний клас для демонстрації роботи з масивом будівельних блоків.
 */
public class BuildingBlockTask {

    public static void main(String[] args) {
        try {
            // Створення масиву будівельних блоків
            BuildingBlock[] blocks = {
                new BuildingBlock(2, 5, "Stone", false, 3.5),
                new BuildingBlock(1, 10, "Obsidian", false, 6.0),
                new BuildingBlock(1, 3, "Wood", false, 2.0),
                new BuildingBlock(3, 2, "Glass", true, 1.5),
                new BuildingBlock(2, 8, "Iron", false, 5.0)
            };

            // Сортування:
            // 1) за id — за зростанням
            // 2) за hardness — за спаданням
            Arrays.sort(blocks, Comparator
                    .comparingInt(BuildingBlock::getId)
                    .thenComparing(
                            Comparator.comparingInt(BuildingBlock::getHardness)
                                    .reversed()
                    ));

            System.out.println("Відсортований масив будівельних блоків:");
            for (BuildingBlock block : blocks) {
                System.out.println(block);
            }

            // Об’єкт для пошуку
            BuildingBlock target = new BuildingBlock(
                    1, 10, "Obsidian", false, 6.0);

            // Пошук ідентичного об’єкта
            boolean found = false;
            for (BuildingBlock block : blocks) {
                if (block.equals(target)) {
                    found = true;
                    System.out.println("\nЗнайдено ідентичний об’єкт:");
                    System.out.println(block);
                    break;
                }
            }

            if (!found) {
                System.out.println("\nІдентичний об’єкт не знайдено.");
            }

        } catch (Exception e) {
            System.out.println("Помилка виконання програми: " + e.getMessage());
        }
    }
}

/**
 * Клас, що описує будівельний блок (аналог блоків з Minecraft).
 */
class BuildingBlock {

    private int id;
    private int hardness;
    private String material;
    private boolean transparent;
    private double weight;

    public BuildingBlock(int id, int hardness, String material,
                         boolean transparent, double weight) {
        this.id = id;
        this.hardness = hardness;
        this.material = material;
        this.transparent = transparent;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public int getHardness() {
        return hardness;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        BuildingBlock other = (BuildingBlock) obj;
        return id == other.id
                && hardness == other.hardness
                && transparent == other.transparent
                && Double.compare(weight, other.weight) == 0
                && material.equals(other.material);
    }

    @Override
    public String toString() {
        return "BuildingBlock{" +
                "id=" + id +
                ", hardness=" + hardness +
                ", material='" + material + '\'' +
                ", transparent=" + transparent +
                ", weight=" + weight +
                '}';
    }
}
