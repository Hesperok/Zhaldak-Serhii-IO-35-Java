import java.util.*;

/**
 * Entry point of the program.
 * Demonstrates a custom generic singly linked list
 * and domain model from Lab №5 (Coffee hierarchy).
 */
public class TextProcessor {

    /**
     * Main execution method.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            CustomLinkedList<Coffee> coffeeList = new CustomLinkedList<>();

            coffeeList.add(new GrainCoffee("Arabica", 10.5, 100, 8));
            coffeeList.add(new GroundCoffee("Robusta", 8.0, 120, 6));
            coffeeList.add(new InstantCoffee("Nescafe", 15.0, 80, 7));

            System.out.println("=== Coffee list ===");
            for (Coffee coffee : coffeeList) {
                System.out.println(coffee);
            }

            coffeeList.sort(Comparator.comparingDouble(Coffee::getPriceWeightRatio));

            System.out.println("\n=== Sorted by price/weight ===");
            for (Coffee coffee : coffeeList) {
                System.out.println(coffee);
            }

            System.out.println("\n=== Coffee with quality 6–8 ===");
            for (Coffee coffee : coffeeList) {
                if (coffee.getQuality() >= 6 && coffee.getQuality() <= 8) {
                    System.out.println(coffee);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/* ============================================================
 * Custom Generic Collection (Singly Linked List, List interface)
 * ============================================================ */

/**
 * Custom implementation of List using a singly linked list.
 *
 * @param <E> type of stored elements
 */
class CustomLinkedList<E> implements List<E> {

    /**
     * Node of singly linked list.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private int size;

    /**
     * Empty constructor.
     */
    public CustomLinkedList() {
        this.size = 0;
    }

    /**
     * Constructor with one element.
     *
     * @param element element to add
     */
    public CustomLinkedList(E element) {
        add(element);
    }

    /**
     * Constructor with standard collection.
     *
     * @param collection collection of elements
     */
    public CustomLinkedList(Collection<? extends E> collection) {
        for (E element : collection) {
            add(element);
        }
    }

    @Override
    public boolean add(E e) {
        if (head == null) {
            head = new Node<>(e);
        } else {
            Node<E> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(e);
        }
        size++;
        return true;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        Node<E> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public int size() {
        return size;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index);
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public E next() {
                E data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /* ==== Unused List methods (required by interface) ==== */

    @Override public void add(int index, E element) { throw new UnsupportedOperationException(); }
    @Override public boolean remove(Object o) { throw new UnsupportedOperationException(); }
    @Override public E remove(int index) { throw new UnsupportedOperationException(); }
    @Override public boolean isEmpty() { return size == 0; }
    @Override public boolean contains(Object o) { throw new UnsupportedOperationException(); }
    @Override public Object[] toArray() { throw new UnsupportedOperationException(); }
    @Override public <T> T[] toArray(T[] a) { throw new UnsupportedOperationException(); }
    @Override public boolean containsAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean addAll(int index, Collection<? extends E> c) { throw new UnsupportedOperationException(); }
    @Override public boolean removeAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public boolean retainAll(Collection<?> c) { throw new UnsupportedOperationException(); }
    @Override public void clear() { throw new UnsupportedOperationException(); }
    @Override public E set(int index, E element) { throw new UnsupportedOperationException(); }
    @Override public int indexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override public int lastIndexOf(Object o) { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator() { throw new UnsupportedOperationException(); }
    @Override public ListIterator<E> listIterator(int index) { throw new UnsupportedOperationException(); }
    @Override public List<E> subList(int fromIndex, int toIndex) { throw new UnsupportedOperationException(); }
}

/* ======================
 * Coffee Domain Model
 * ====================== */

/**
 * Abstract base class for coffee.
 */
abstract class Coffee {
    private final String name;
    private final double price;
    private final double weight;
    private final int quality;

    protected Coffee(String name, double price, double weight, int quality) {
        this.name = name;
        this.price = price;
        this.weight = weight;
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
        return name + " | price=" + price + " | weight=" + weight + " | quality=" + quality;
    }
}

/**
 * Grain coffee implementation.
 */
class GrainCoffee extends Coffee {
    public GrainCoffee(String name, double price, double weight, int quality) {
        super(name, price, weight, quality);
    }
}

/**
 * Ground coffee implementation.
 */
class GroundCoffee extends Coffee {
    public GroundCoffee(String name, double price, double weight, int quality) {
        super(name, price, weight, quality);
    }
}

/**
 * Instant coffee implementation.
 */
class InstantCoffee extends Coffee {
    public InstantCoffee(String name, double price, double weight, int quality) {
        super(name, price, weight, quality);
    }
}
