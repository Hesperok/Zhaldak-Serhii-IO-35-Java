/**
 * Клас, що представляє слово як масив літер.
 */
public class Word {

    private final Letter[] letters;

    public Word(String text) {
        letters = new Letter[text.length()];
        for (int i = 0; i < text.length(); i++) {
            letters[i] = new Letter(text.charAt(i));
        }
    }

    public int countVowels() {
        int count = 0;
        for (Letter letter : letters) {
            if (letter.isVowel()) {
                count++;
            }
        }
        return count;
    }

    public String getText() {
        StringBuilder builder = new StringBuilder();
        for (Letter letter : letters) {
            builder.append(letter.getValue());
        }
        return builder.toString();
    }
}
