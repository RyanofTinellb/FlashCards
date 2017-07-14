package ryan.flashcards;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

public class Egyptian extends AppCompatActivity {

    /**
     * pseudo-random number generator
     */
    final Random randomInt = new Random();

    /**
     * list of all images
     */
    int[] glyphs = {R.drawable.aleph, R.drawable.ayin, R.drawable.beth, R.drawable.daleth,
            R.drawable.faa, R.drawable.ge, R.drawable.hbreve, R.drawable.hdot, R.drawable.heh,
            R.drawable.hline, R.drawable.jar, R.drawable.kup, R.drawable.mem, R.drawable.nun,
            R.drawable.pe, R.drawable.qoph, R.drawable.resh, R.drawable.samekh, R.drawable.shin,
            R.drawable.tav, R.drawable.tchek, R.drawable.wuw, R.drawable.yii, R.drawable.yod,
            R.drawable.zayin};



    /**
     * list of all characters
     */
    int[] glyphNames = {R.string.Aleph, R.string.Ayin, R.string.Beth, R.string.Daleth,
            R.string.Faa, R.string.Ge, R.string.Hbreve, R.string.Hdot, R.string.Heh,
            R.string.Hline, R.string.Jar, R.string.Kup, R.string.Mem, R.string.Nun,
            R.string.Pe, R.string.Qoph, R.string.Resh, R.string.Samekh, R.string.Shin,
            R.string.Tav, R.string.Tchek, R.string.Wuw, R.string.Yii, R.string.Yod,
            R.string.Zayin};

    /**
     * the number of entries in the dictionary
     */
    int numberOfEntries = glyphs.length;

    /**
     * correct answer
     */
    int correct;

    /**
     * correct box
     */
    int correctBox;

    /**
     * random numbers
     */
    int[] randomNumbers = new int[4];

    /**
     * text box
     */
    TextView questionBox;

    /**
     * list of buttons
     */
    ImageButton[] buttons = new ImageButton[4];

    /**
     * Initialises the main flashcards screen.
     *
     * @param savedInstanceState saved program state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.egyptian);
        buttons[0] = (ImageButton) findViewById(R.id.firstImage);
        buttons[1] = (ImageButton) findViewById(R.id.secondImage);
        buttons[2] = (ImageButton) findViewById(R.id.thirdImage);
        buttons[3] = (ImageButton) findViewById(R.id.fourthImage);
        questionBox = (TextView) findViewById(R.id.questionBox);
        newButtons();
    }

    public void newButtons() {
        questionBox.setBackgroundColor(Color.WHITE);
        correct = randomInt.nextInt(numberOfEntries);
        correctBox = randomInt.nextInt(4);
        // ensure all random numbers are distinct
        for (int i = 0; i < 4; i++) {
            randomNumbers[i] = randomInt.nextInt(numberOfEntries);
            if (randomNumbers[i] == correct) {
                i--; continue;
            }
            for (int j = 0; j < i; j++) {
                if (randomNumbers[j] == randomNumbers[i]) {
                    i--; break;
                }
            }
        }
        for (int i = 0; i < 4; i++) {
            if (i == correctBox) {
                buttons[i].setImageResource(glyphs[correct]);
            } else {
                buttons[i].setImageResource(glyphs[randomNumbers[i]]);
            }
        }
        questionBox.setText(glyphNames[correct]);
    }

    public void checkAnswer(View view) {
        int duration;
        int colour;
        if (view == buttons[correctBox]) {
            duration = 300;
            colour = Color.GREEN;
        } else {
            duration = 750;
            colour = Color.RED;
        }

        questionBox.setBackgroundColor(colour);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newButtons();
            }
        }, duration);
    }
}