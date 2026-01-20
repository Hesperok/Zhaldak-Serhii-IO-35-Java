import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє речення.
 */
public class Sentence {

    private final List<Word> words = new ArrayList<>();
    private final List<Punctuation> punctuations = new ArrayList<>();

    public Sentence(String text) {
        String cleaned = text.trim();
        String[] parts = cleaned.split(" ");

        for (String part : parts) {
            if (part.matches("[a-zA-Z]+")) {
                words.add(new Word(part));
            } else {
                for (char c : part.toCharArray()) {
                    if (!Character.isLetter(c)) {
                        punctuations.add(new Punctuation(c));
                    }
                }
            }
        }
    }

    public List<Word> getWords() {
        return words;
    }
}
