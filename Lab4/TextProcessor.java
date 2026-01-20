import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Виконавчий клас лабораторної роботи.
 */
public class TextProcessor {

    public static void main(String[] args) {
        try {
            String inputText =
                    "Java   is\t\tpowerful. Programming in Java   is useful!";

            Text text = new Text(inputText);
            List<Word> allWords = new ArrayList<>();

            for (Sentence sentence : text.getSentences()) {
                allWords.addAll(sentence.getWords());
            }

            allWords.sort(Comparator.comparingInt(Word::countVowels));

            System.out.println("Слова, відсортовані за кількістю голосних:");
            for (Word word : allWords) {
                System.out.println(word.getText()
                        + " (" + word.countVowels() + ")");
            }

        } catch (Exception e) {
            System.out.println("Помилка виконання програми: " + e.getMessage());
        }
    }
}
