package ryan.flashcards;

/**
 * Controls the languages used in the rest of the app
 * Uses static variables so that all other classes get the same information.
 * Created by Ryan on 28-05-2016.
 */
public class Language {

    /** how many languages are currently installed, and the maximum number of installed languages */
    static int numberOfLanguages = 0;
    static int maximumNumberOfLanguages;

    /** details for each language. */
    /** the name of the language */
    static String[] names;
    /** the id for the dictionary */
    static int[] dictionaries;
    /** the id for the button */
    static int[] buttons;

    /**
     * empty constructor - allows access to the static qualities
     */
    public Language() {

    }

    /**
     * instantiates a Language group
     * @param numberOfLanguages the number of languages about to be installed
     */
    public Language(int numberOfLanguages) {
        Language.maximumNumberOfLanguages = numberOfLanguages;
        names = new String[maximumNumberOfLanguages];
        dictionaries = new int[maximumNumberOfLanguages];
        buttons = new int[maximumNumberOfLanguages];
        Language.numberOfLanguages = 0;
    }

    /**
     * allows the addition of a new language to the Language group, if it is not full
     * will do nothing if the group is full
     * @param name the name of the language, to appear on the button
     * @param dictionary the id of the dictionary string array
     * @param button the id of the button corresponding to the language
     */
    public void addNewLanguage(String name, int dictionary, int button) {
        if (numberOfLanguages < maximumNumberOfLanguages) {
            names[numberOfLanguages] = name;
            dictionaries[numberOfLanguages] = dictionary;
            buttons[numberOfLanguages] = button;
            numberOfLanguages++;
        }
    }

    /** ordinal is a number n: 0 <= n < maximumNumberOfLanguages */
    public String getName(int ordinal) {
        return names[ordinal];
    }

    /**
     * @param ordinal a number n: 0 <= n < maximumNumberOfLanguages
     * @return the id of the location of the dictionary
     */
    public int getDictionary(int ordinal) {
        return dictionaries[ordinal];
    }

    /**
     * @param ordinal a number n: 0 <= n < maximumNumberOfLanguages
     * @return the id of the button corresponding to the language
     */
    public int getButton (int ordinal) {
        return buttons[ordinal];
    }

    public int getNumberOfLanguages() {
        return numberOfLanguages;
    }

    /**
     * allows the program to translate between ordinal and id
     * @param button the id of a button
     * @return the ordinal n of the language
     */
    public int findLanguageByButton (int button){
        for (int i = 0; i < numberOfLanguages; i++) {
            if (buttons[i] == button) {
                return i;
            }
        }
        return -1;
    }
}