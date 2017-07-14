package ryan.flashcards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This is the Launcher activity. It allows the user to select the language on which they wish
 *      to be tested.
 */
public class LanguageSelectionScreen extends AppCompatActivity {

    /** Used to identify Intents */
    public final static String LANGUAGE = "com.ryan.flashcards.LANGUAGE";

    /** The number of installed languages */
    int numberOfLanguages = 7;

    /**
     * creates the screen for the user to select languages.
     * @param savedInstanceState for coming back to the program
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_selection);

        Language language = new Language(numberOfLanguages);

        /** Sets up all the languages, including name, dictionary and button id */
        language.addNewLanguage("Lulani", R.array.lulaniArray,R.id.buttonLulani);
        language.addNewLanguage("Egyptian", R.array.egyptianArray, R.id.buttonEgyptian);
        language.addNewLanguage("Euskara", R.array.euskaraArray, R.id.buttonEuskara);
        language.addNewLanguage("Toki Pona", R.array.tokiponaArray, R.id.buttonTokipona);
        language.addNewLanguage("教育漢字", R.array.教育漢字array, R.id.buttonKanji);
        language.addNewLanguage("日本語", R.array.japaneseArray, R.id.buttonJapanese);
        language.addNewLanguage("Arabic", R.array.arabicArray, R.id.buttonArabic);
    }

    /**
     * informs the Main Activity of which language the user wishes to be tested upon
     * @param view the language chosen by the user
     */
    public void selectLanguage(View view) {
        int chosenLanguage = view.getId();
        Language language = new Language();
        chosenLanguage = language.findLanguageByButton(chosenLanguage);
        Intent intent;
        if (chosenLanguage == 1) {
            intent = new Intent(LanguageSelectionScreen.this, Egyptian.class);
        } else {
            intent = new Intent(LanguageSelectionScreen.this, FlashCardsScreen.class);
        }
        intent.putExtra(LANGUAGE, chosenLanguage);
        startActivity(intent);
    }
}
