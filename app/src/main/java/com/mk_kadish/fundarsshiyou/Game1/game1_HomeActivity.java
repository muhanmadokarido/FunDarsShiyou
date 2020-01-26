package com.mk_kadish.fundarsshiyou.Game1;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.mk_kadish.fundarsshiyou.AllSharedPrefernces;
import com.mk_kadish.fundarsshiyou.DatabaseRelations.SchoolDbHelper;
import com.mk_kadish.fundarsshiyou.R;
import com.mk_kadish.fundarsshiyou.indexActivity;

import java.util.ArrayList;
import java.util.Locale;

public class game1_HomeActivity extends AppCompatActivity {
    private String sharedTimer;
    private AllSharedPrefernces allSharedPrefernces;

    View view1;
    int position1;
    private int height;
    private int width;
    private static final long START_TIME_IN_MILLIS = 120000;
    private long mtimeUsed;
    private TextView mTextViewCountDown;
    private  TextView textViewPairs;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private Long pointsCounter;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    ArrayList<Game1KeyValue> allpairs = new ArrayList<>();
    boolean isMatch=false;
    ImageAdapter imageAdapter2;
    ImageView curView = null;
    private int countPair = 0;
    TextView tv6;
    TextView myPoints;
    public int[] drawable=new int[]
            {
                    R.drawable.no,
                    R.drawable.no_ar,
                    R.drawable.yes_ar,
                    R.drawable.yes,

            };
    int[] pos={0,1,2,3};
    int maxnum=2;

    int currentPos = -1;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        allSharedPrefernces = new AllSharedPrefernces(getApplicationContext());
        setContentView(R.layout.activity_game1__home);

        allpairs.add(new Game1KeyValue(R.drawable.yes,R.drawable.yes_ar));
        allpairs.add(new Game1KeyValue(R.drawable.no ,R.drawable.no_ar));

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        tv6 = findViewById(R.id.g2_tv4_pairsCount);
        myPoints=findViewById(R.id.g2_tv6_Ponits);
        pointsCounter=getPoints();
        myPoints.setText(pointsCounter+"");

        tv6.setText(""+drawable.length /2);
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

                playAgain(v);
            }
        });
        updateCountDownText();

        final ImageAdapter imageAdapter = new ImageAdapter(this,height,width,drawable.length);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(2);
        gridView.setEnabled(false);

        RelativeLayout.LayoutParams parameter =  (RelativeLayout.LayoutParams) gridView.getLayoutParams();
        parameter.setMargins(width * 2 /7, parameter.topMargin, parameter.rightMargin, parameter.bottomMargin); // left, top, right, bottom
        gridView.setLayoutParams(parameter);

        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (currentPos < 0) {
                    currentPos = position;
                    curView = (ImageView) view;

                    MediaPlayer.create(game1_HomeActivity.this, R.raw.parrot).start();
                    ((ImageView) view).setImageResource(drawable[pos[position]]);

                    view1 = view;
                    position1 = position;

                } else {
                    if (currentPos == position) {
                        ((ImageView) view).setImageResource(R.drawable.questionbg);
                    } else {
                        for (Game1KeyValue d : allpairs) {
                            if ((d.key == drawable[currentPos]) || (d.value == drawable[currentPos])) {
                                if (d.key == drawable[currentPos]) {
                                    if (d.value == drawable[position]) {
                                        isMatch = true;
                                        d.setUsed(1);
                                    }
                                    break;
                                } else {
                                    if (d.value == drawable[currentPos]) {
                                        if (d.key == drawable[position]) {
                                            isMatch = true;
                                        }
                                        break;
                                    }
                                }
                            }
                        }

                        ((ImageView) view).setImageResource(drawable[pos[position]]);
                        view1 = view;

                        if (!isMatch) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    curView.setImageResource(R.drawable.questionbg);
                                    ((ImageView) view1).setImageResource(R.drawable.questionbg);
                                }
                            }, 600);
                            MediaPlayer mediaPlayer10 = MediaPlayer.create(game1_HomeActivity.this, R.raw.dog);
                            mediaPlayer10.start();
                        }
                        else if (isMatch) {
                            isMatch = false;
                            ((ImageView) view).setImageResource(drawable[pos[position]]);
                            switch (currentPos) {
                                case R.drawable.sample_0:
                                    MediaPlayer mediaPlayer2 = MediaPlayer.create(game1_HomeActivity.this, R.raw.rabbit);
                                    mediaPlayer2.start();
                                    break;
                                default:
                                    MediaPlayer mediaPlayer10 = MediaPlayer.create(game1_HomeActivity.this, R.raw.lion);
                                    mediaPlayer10.start();
                                    break;
                            }

                            countPair++;
                            pointsCounter=pointsCounter+1L;
                            myPoints.setText(pointsCounter+"");
                            textViewPairs.setText(countPair + "");
                            if(countPair != maxnum)
                            {
                                int curNum=drawable.length;

                                int[] newArray = new int[curNum-2];

                                int k=0;
                                for(int i=0;i<curNum;i++)
                                {
                                    if(i==position || i==currentPos)
                                    {

                                    }
                                    else
                                    {
                                        newArray[k] = drawable[i];
                                        k++;
                                    }
                                }
                                drawable=newArray;
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        removeFromGrid(drawable);
                                    }
                                }, 1000);

                            }
                            else
                            if (countPair == maxnum) {
                                MediaPlayer mediaPlayer3 = MediaPlayer.create(game1_HomeActivity.this, R.raw.sound1);
                                mediaPlayer3.start();

                                try
                                {
                                    Thread.sleep(500);
                                }
                                catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                passToNext(view);
                            }
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    currentPos = -1;
                }
            }
        });
    }

    private void removeFromGrid(int[] arr)
    {
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(null);
        imageAdapter2=new ImageAdapter(this,height,width,drawable.length);
        gridView.setNumColumns(2);
        gridView.setAdapter(imageAdapter2);
        currentPos = -1;
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

                AlertDialog.Builder builder = new AlertDialog.Builder(game1_HomeActivity.this);

                builder.setCancelable(true);
                builder.setTitle("            انتهت اللعبة                        ");
                builder.setMessage("حظ أوفر المرة القادمة");

                builder.setNegativeButton("الخروج من اللعبة", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       backToFirstMethod();

                    }
                });

                builder.setPositiveButton("العب مرة أخرى", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                builder.show();

            }
        }.start();
        mTimerRunning = true;
        mButtonStartPause.setText("إيقاف مؤقت");
        mButtonReset.setVisibility(View.INVISIBLE);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(2);
        gridView.setEnabled(true);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("استئناف");
        mButtonReset.setVisibility(View.VISIBLE);
        GridView gridView = findViewById(R.id.gridView);
        gridView.setNumColumns(2);
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
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(null);
        ImageAdapter imageAdapter = new ImageAdapter(this,height,width,drawable.length);
        gridView.setNumColumns(2);
        gridView.setAdapter(imageAdapter);
        gridView.setEnabled(false);
        currentPos = -1;
        countPair=0;
    }

    public void passToNext(View view)
    {
        allSharedPrefernces.writeTimer1(mTimeLeftInMillis);
        allSharedPrefernces.initPointCounter(getPoints());
        startActivity(new Intent(this,game1_secondActivity.class));
        finish();
    }

    public void playAgain(View view)
    {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void backToFirstMethod()
    {
        startActivity(new Intent(this, indexActivity.class));
        finish();
    }

    public Long getPoints()
    {
        SchoolDbHelper schoolDbHelper=new SchoolDbHelper(this);
        SQLiteDatabase mydatabase=schoolDbHelper.getReadableDatabase();
        Long c= schoolDbHelper.getCurrentPoints(11,mydatabase);
        return c;
    }
}