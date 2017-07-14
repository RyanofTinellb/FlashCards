package ryan.flashcards;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class FindWordScreen extends AppCompatActivity {

    /**
     * the button the user presses to search for the word in the EditText
     */
    Button searchButton;

    /**
     * the word for which the user is searching
     */
    EditText word;

    /**
     * where the found words are to be displayed
     */
    TextView results;

    /**
     * the option buttons for full search, beginning search and so on.
     */
    RadioButton full;
    RadioButton start;
    RadioButton end;
    RadioButton normal;

    /**
     * the dictionary through which the user is searching
     */
    String[] dictionary;

    /**
     * Begins screen
     *
     * @param savedInstanceState the state of the program the last time the user was here.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_word);

        Intent intent = getIntent();
        dictionary = intent.getStringArrayExtra(FlashCardsScreen.SEARCH);

        searchButton = (Button) findViewById(R.id.searchButton);
        word = (EditText) findViewById(R.id.word);
        results = (TextView) findViewById(R.id.results);

        searchButton.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchButton, InputMethodManager.SHOW_IMPLICIT);
        results.setMovementMethod(new ScrollingMovementMethod());

        full = (RadioButton) findViewById(R.id.fullSearch);
        start = (RadioButton) findViewById(R.id.beginningSearch);
        end = (RadioButton) findViewById(R.id.endSearch);
        normal = (RadioButton) findViewById(R.id.normalSearch);
    }

    /**
     * uses Search to find the given word
     *
     * @param view the button the user has clicked to run the search
     */
    public void runSearch(View view) {

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(word.getWindowToken(), 0);

        String searchTerm = word.getText().toString();
        final Search search = new Search(dictionary);
        Search.Option option;
        if (searchTerm.length() > 0) {
            if (full.isChecked()) {
                option = Search.Option.FULL;
            } else if (start.isChecked()) {
                option = Search.Option.BEGINNING;
            } else if (end.isChecked()) {
                option = Search.Option.END;
            } else {
                option = Search.Option.NORMAL;
            }
            results.setText(search.findWord(option, searchTerm));
        }
    }
}
