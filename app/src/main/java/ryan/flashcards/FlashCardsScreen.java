package ryan.flashcards;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class FlashCardsScreen extends AppCompatActivity {

    /**
     * number of possible answers displayed as buttons to the user
     */
    int numberOfAnswers = 4;

    /**
     * pseudo-random number generator
     */
    final Random randomInt = new Random();

    /**
     * the entire dictionary, in order to pick questions and answers from it
     */
    String[] dictionary;

    /**
     * the number of entries in the dictionary
     */
    int numberOfEntries;

    /**
     * the answer button IDs
     */
    final int[] answers = new int[numberOfAnswers];

    /**
     * the place were the question appears
     */
    TextView questionBox;

    /**
     * the ordinal for the button has the correct answer
     */
    int correct;

    /**
     * the correct answer
     */
    String correctAnswer;

    /**
     * where the answer appears if the user gets the question wrong
     */
    TextView answerBox;

    /**
     * the number of correct answers given subtract the number of incorrect answers
     */
    int scoreCounter = 0;

    /**
     * where the score counter appears
     */
    TextView counterBox;

    /** Used to identify Intents */
    public final static String SEARCH = "com.ryan.flashcards.SEARCH";

    /**
     * Initialises the main flashcards screen.
     *
     * @param savedInstanceState saved program state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /** get selected language from Language Selection Screen */
        Intent intent = getIntent();
        Language language = new Language();
        int chosenLanguage = intent.getIntExtra(LanguageSelectionScreen.LANGUAGE, 0);
        try {
            dictionary = this.getResources().getStringArray(language.getDictionary(chosenLanguage));
        } catch (ArrayIndexOutOfBoundsException ar) {
            Toast.makeText(FlashCardsScreen.this, "That Language has not yet been installed",
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        numberOfEntries = dictionary.length / 2;

        /** Set up answer answers with correct design*/
        answers[0] = R.id.buttonOne;
        answers[1] = R.id.buttonTwo;
        answers[2] = R.id.buttonThree;
        answers[3] = R.id.buttonFour;
        for (int i = 0; i < 4; i++) {
            Button answer = (Button) findViewById(answers[i]);
            if (answer != null) {
                answer.setBackgroundResource(android.R.drawable.btn_default);
            }
        }

        /** set up text boxes */
        questionBox = (TextView) findViewById(R.id.questionText);
        answerBox = (TextView) findViewById(R.id.answerText);
        counterBox = (TextView) findViewById(R.id.counterText);

        setNewQuestion();
    }

    /**
     * Checks if the user has tapped the correct button
     *
     * @param view the button the user has tapped
     */
    public void checkAnswer(final View view) {

        /** finds the button that the user has tapped */
        final Button answer = (Button) view;
        int chosenAnswer = answer.getId();
        int choice = -1;
        for (int i = 0; i < numberOfAnswers; i++) {
            if (chosenAnswer == answers[i]) {
                choice = i;
                break;
            }
        }

        /**
         * if the user got the answer incorrect, the correct answer is displayed in red, the
         *      selected button turns red and says 'incorrect', and the score counter is
         *      decremented by 1.
         * if the user has picked the correct answer, the selected button turns green and says
         *      correct, and the score counter is incremented by 1. The change does not last
         *      as long as when the user is incorrect.
         */
        final int duration = (choice == correct) ? 300 : 750;
        int colour = (choice == correct) ? Color.GREEN : Color.RED;
        String text = (choice == correct) ? "Correct!" : "Wrong!";
        scoreCounter += (choice == correct) ? 1 : -1;
        answer.setBackgroundColor(colour);
        answer.setText(text);

        if (choice != correct) {
            answerBox.setTextColor(Color.RED);
            answerBox.setText(correctAnswer);
        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                answer.setBackgroundResource(android.R.drawable.btn_default);
                questionBox.setTextColor(Color.BLACK);
                setNewQuestion();
            }
        }, duration);
    }

    /**
     * sets up the next question
     */
    public void setNewQuestion() {
        /** the number of the correct button */
        int intAnswer;

        /** the correct answer */
        String strAnswer;

        /** the incorrect answer */
        String wrong;

        /** the choices which will appear on the buttons */
        String[] answerList = new String[4];

        /** selects the question for the user */
        int intQuestion = randomInt.nextInt(numberOfEntries) * 2;

        /** selects whether the user is required to answer in English or not */
        int language = randomInt.nextInt(2);

        /** selects where the correct answer will appear */
        correct = randomInt.nextInt(4);

        /**
         * clears the answer text, puts the question in the correct box,
         * and updates the score counter.
         */
        answerBox.setText("");

        questionBox.setText(dictionary[intQuestion + language]);
        counterBox.setText(String.format(new Locale("en"), "%d", scoreCounter));

        /** flips the language to show possible answers for the question */
        language = (language == 0) ? 1 : 0;

        /** finds the correct answer */
        correctAnswer = dictionary[intQuestion + language];

        /** builds a list of possible answers */
        for (int i = 0; i < 4; i++) {
            intAnswer = randomInt.nextInt(numberOfEntries) * 2;
            strAnswer = dictionary[intAnswer + language];

            /** removes duplicate answers */
            if (strAnswer.equals(correctAnswer)) {
                i--;
            } else {
                answerList[i] = strAnswer;
            }
            for (int j = 0; j < i; j++) {
                if (strAnswer.equals(answerList[j])) {
                    i--;
                    break;
                }
            }

        }

        /** populates answer buttons with possible answers */
        for (int i = 0; i < 4; i++) {
            Button answer = (Button) findViewById(answers[i]);
            if (answer != null) {
                if (i != correct) {
                    wrong = answerList[i];
                    answer.setText(wrong);
                } else {
                    answer.setText(correctAnswer);
                }
            }
        }
    }

    /**
     * Starts the "Find a Word" activity
     * @param view the button which launches the activity
     */
    public void findWord(View view) {
        Intent intent = new Intent(FlashCardsScreen.this, FindWordScreen.class);
        intent.putExtra(SEARCH, dictionary);
        startActivity(intent);
    }
}