import java.util.Arrays;
import java.util.Comparator;

public class TextTask {

    public static void main(String[] args) {
        try {
            String text = "Java is a powerful programming language. "
                        + "It is widely used in enterprise applications. "
                        + "Learning Java improves problem solving skills.";

            String[] words = text
                    .replaceAll("[^a-zA-Z ]", "")
                    .toLowerCase()
                    .split("\\s+");

            Arrays.sort(words, Comparator.comparingInt(TextTask::countVowels));

            System.out.println("Слова, відсортовані за кількістю голосних:");
            for (String word : words) {
                System.out.println(word + " (" + countVowels(word) + ")");
            }

        } catch (NullPointerException e) {
            System.out.println("Помилка: текст не може бути null");
        } catch (Exception e) {
            System.out.println("Невідома помилка: " + e.getMessage());
        }
    }


    private static int countVowels(String word) {
        int count = 0;
        String vowels = "aeiouy";

        for (int i = 0; i < word.length(); i++) {
            if (vowels.indexOf(word.charAt(i)) >= 0) {
                count++;
            }
        }
        return count;
    }
}
