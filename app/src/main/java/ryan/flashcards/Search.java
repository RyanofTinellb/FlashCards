package ryan.flashcards;

/**
 * Created by Ryan on 29-05-2016.
 * Searches a given dictionary for a given word
 * Options: full word; start of string; end of string
 */

public class Search {

    String[] dictionary;

    public enum Option {
        NORMAL,
        FULL,
        BEGINNING,
        END
    }

    public Search() {

    }

    public Search (String[] dictionary) {
        this.dictionary = dictionary;
    }

    /**
     * finds a word anywhere in the dictionary
     * calls full version of findWord
     */
    public String findWord(String word) {
        return findWord(Option.NORMAL, word);
    }

    /**
     * finds a word in a dictionary
     * case-sensitive
     * @param option: full: if the dictionary entry precisely matches the given word
     *              beginning: if the dictionary entry begins with the given word
     *              end: if the dictionary entry ends with the given word
     *              normal: if the dictionary entry has the given word within it anywhere
     * @param word the given word
     * @return Language: Fa?a or Fa?a: Language for each time the given word is found
     */
    public String findWord(Option option, String word) {

        /** holding here for time efficiency */
        int wordLength = word.length();

        String result = "";

        switch (option) {
            case FULL:
                for (int i = 0; i < dictionary.length; i++) {
                    if (dictionary[i].equals(word)) {
                        int entry = i % 2 == 0 ? i : i - 1;
                        result += dictionary[entry] + ": " + dictionary[++entry] + "\n";
                    }
                }
                break;
            case BEGINNING:
                for (int i = 0; i < dictionary.length; i++) {
                    if (dictionary[i].length() >= wordLength && dictionary[i].startsWith(word)) {
                        int entry = i % 2 == 0 ? i : i - 1;
                        result += dictionary[entry] + ": " + dictionary[++entry] + "\n";
                    }
                }
                break;
            case END:
                for (int i = 0; i < dictionary.length; i++) {
                    if (dictionary[i].length() >= wordLength && dictionary[i].endsWith(word)) {
                        int entry = i % 2 == 0 ? i : i - 1;
                        result += dictionary[entry] + ": " + dictionary[++entry] + "\n";
                    }
                }
                break;
            case NORMAL:
                for (int i = 0; i < dictionary.length; i++) {
                    if (dictionary[i].length() >= wordLength && dictionary[i].contains(word)) {
                        int entry = i % 2 == 0 ? i : i - 1;
                        result += dictionary[entry] + ": " + dictionary[++entry] + "\n";
                    }
                }
                break;
            default:
                break;
        }
        return result;
    }
}


