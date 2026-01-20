import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє текст як масив речень.
 */
public class Text {

    private final List<Sentence> sentences = new ArrayList<>();

    public Text(String text) {
        String normalized = text.replaceAll("[\\t\\s]+", " ");
        String[] parts = normalized.split("[.!?]");

        for (String part : parts) {
            if (!part.isBlank()) {
                sentences.add(new Sentence(part));
            }
        }
    }

    public List<Sentence> getSentences() {
        return sentences;
    }
}
