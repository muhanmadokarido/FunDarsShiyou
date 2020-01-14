package com.mk_kadish.fundarsshiyou.Game1;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.mk_kadish.fundarsshiyou.R;

import java.util.ArrayList;
import java.util.Locale;

public class game1_HomeActivity extends AppCompatActivity {

    private static final long START_TIME_IN_MILLIS = 120000;
    private long mtimeUsed;
    private TextView mTextViewCountDown;
    private  TextView textViewPairs;
    private TextView bestrecord;

    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    ArrayList<Game1KeyValue> allpairs = new ArrayList<>();
    boolean isMatch=false;


    ImageView curView = null;
    private int countPair = 0;
    final int[] drawable = new int[] {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
            R.drawable.sample_8,
            R.drawable.sample_9,
            R.drawable.sample_10,
            R.drawable.sample_11,
            R.drawable.sample_12,
            R.drawable.sample_13,
            R.drawable.sample_14,
            R.drawable.sample_15
    };
    int[] pos = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    int currentPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1__home);

        allpairs.add(new Game1KeyValue(0,11));
        allpairs.add(new Game1KeyValue(1,12));
        allpairs.add(new Game1KeyValue(2,8));
        allpairs.add(new Game1KeyValue(3,9));
        allpairs.add(new Game1KeyValue(4,14));
        allpairs.add(new Game1KeyValue(5,10));
        allpairs.add(new Game1KeyValue(6,13));
        allpairs.add(new Game1KeyValue(7,15));

        mTextViewCountDown = findViewById(R.id.g1_tv2_text_view_countdown);
        textViewPairs=findViewById(R.id.g1_tv2_pairsCount);

        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });
        updateCountDownText();

        ImageAdapter imageAdapter = new ImageAdapter(this);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setEnabled(false);

        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentPos < 0 )
                {
                    currentPos = position;
                    curView = (ImageView) view;
                    ((ImageView) view).setImageResource(drawable[pos[position]]);
                }
                else
                    {
                    if (currentPos == position)
                    {
                        ((ImageView) view).setImageResource(R.drawable.hidden);
                    }
                    else
                        {
                            for(Game1KeyValue d: allpairs) {
                                if ((d.key == currentPos) || (d.value == currentPos)) {
                                    if (d.key == currentPos) {
                                        if (d.value == position) {
                                            isMatch = true;
                                        }
                                        break;
                                    } else {
                                        if (d.value == currentPos) {
                                            if (d.key == position) {
                                                isMatch = true;
                                            }
                                            break;
                                        }
                                    }
                                }
                            }

                        if (!isMatch) {
                            curView.setImageResource(R.drawable.hidden);
                            Toast.makeText(game1_HomeActivity.this, "Not Match!", Toast.LENGTH_LONG).show();
                             } else {
                            isMatch=false;
                            ((ImageView) view).setImageResource(drawable[pos[position]]);
                            countPair++;
                            if(countPair != 8) {
                                MediaPlayer mediaPlayer3 = MediaPlayer.create(game1_HomeActivity.this, R.raw.sound3);
                                mediaPlayer3.start();
                            }
                            textViewPairs.setText(countPair + "");

                            if (countPair == 8) {
                                bestrecord();
                                pauseTimer();
                                resetTimer();

                                MediaPlayer mediaPlayer2 = MediaPlayer.create(game1_HomeActivity.this, R.raw.sound1);
                                mediaPlayer2.start();
                            }
                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);
            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);

        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setEnabled(true);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("Start");
        mButtonReset.setVisibility(View.VISIBLE);
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setEnabled(false);
    }

    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);
        resetGrid();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }

    private void resetGrid()
    {
        textViewPairs.setText("0");
        GridView gridView = (GridView)findViewById(R.id.gridView);
        gridView.setAdapter(null);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);
        gridView.setEnabled(false);
        currentPos = -1;
        countPair=0;
    }

    private void bestrecord()
    {
        mtimeUsed=  START_TIME_IN_MILLIS-mTimeLeftInMillis;
        bestrecord=findViewById(R.id.g2_tv6_bestRecord);
        if(bestrecord.getText().toString().equals("-"))
        {
            int minutes = (int) (mtimeUsed / 1000) / 60;
            int seconds = (int) (mtimeUsed / 1000) % 60;
            bestrecord.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
        }
        else
        {
            String str1=bestrecord.getText().toString();
            String[] arrOfStr = str1.split(":");
            int m1=Integer.parseInt(arrOfStr[0]);
            int s1=Integer.parseInt(arrOfStr[1]);

            int minutes = (int) (mtimeUsed / 1000) / 60;
            int seconds = (int) (mtimeUsed / 1000) % 60;

            if(m1 > minutes || (m1==minutes && s1>seconds))
            {

                bestrecord.setText(String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds));
            }
        }

    }
}
