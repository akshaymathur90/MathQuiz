package edu.sjsu.mathquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Random;

public class QuizQuestion extends AppCompatActivity {
    private final String TAG = getClass().toString();
    AppCompatTextView textView_timer,textViewNum1,textViewNum2,textViewQuesNo,textView_operator;
    AppCompatEditText editTextAnswer;
    int totalQuestions,currentQuestion,correctAnswers;
    int num1,num2;
    AppCompatButton btn_enter;
    String category;
    private static CountDownTimer cdt;
    boolean rotate_flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_question);

        totalQuestions = 10;
        currentQuestion = 0;
        correctAnswers = 0;

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);

        category = getIntent().getExtras().getString("Category");
        Log.d(TAG,"The category selected is -->"+category);

        View view_question = getFragmentManager().findFragmentById(R.id.fragment_question).getView();
        textViewNum1 = (AppCompatTextView) view_question.findViewById(R.id.tv_num1);
        textViewNum2 = (AppCompatTextView) view_question.findViewById(R.id.tv_num2);
        textView_timer = (AppCompatTextView) view_question.findViewById(R.id.tv_timer);
        textViewQuesNo = (AppCompatTextView) view_question.findViewById(R.id.tv_questionnumber);
        textView_operator =(AppCompatTextView) view_question.findViewById(R.id.tv_operator);

        if(category.equals("Addition")){
            textView_operator.setText("+");
        }
        else if(category.equals("Subtraction")){
            textView_operator.setText("-");
        }
        else if(category.equals("Production")){
            textView_operator.setText("X");
        }

        editTextAnswer = (AppCompatEditText) view_question.findViewById(R.id.ed_answer);

        if(savedInstanceState!=null){
            rotate_flag = true;
            Log.d(TAG,"Retrieved state answer--> "+savedInstanceState.getString("answer"));
            editTextAnswer.setText(savedInstanceState.getString("answer"));
            cdt =  new CountDownTimer(Integer.parseInt(savedInstanceState.getString("timer"))*1000, 1000) {

                public void onTick(long millisUntilFinished) {
                    textView_timer.setText(millisUntilFinished / 1000+"");
                }

                public void onFinish() {
                    textView_timer.setText("0");
                    Log.d(TAG,"rotation timer finish()");
                    newQuestion();

                }
            };
            currentQuestion = savedInstanceState.getInt("currentquestion");
            num1 = savedInstanceState.getInt("num1");
            num2 = savedInstanceState.getInt("num2");
            textViewNum1.setText(num1+"");
            textViewNum2.setText(num2+"");
            textViewQuesNo.setText("Q."+currentQuestion+" of 10");
            correctAnswers = savedInstanceState.getInt("correctanswers");
            textView_timer.setText(savedInstanceState.getString("timer"));
            cdt.start();
        }
        else{
            cdt =  new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    textView_timer.setText(millisUntilFinished / 1000+"");
                }

                public void onFinish() {
                    textView_timer.setText("0");
                    newQuestion();
                }
            };

        }

        View view_numpad = getFragmentManager().findFragmentById(R.id.fragment_numericpad).getView();
        btn_enter = (AppCompatButton) view_numpad.findViewById(R.id.btn_enter);

        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int answer = Integer.parseInt(editTextAnswer.getText().toString());
                boolean flag = checkAnswer(answer);
                if(!flag){
                    newQuestion();
                }


            }
        });

        editTextAnswer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().equals("")){
                    checkAnswer(Integer.parseInt(charSequence.toString()));
                }


            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });



    }

    public boolean checkAnswer(int answer){

        if(category.equals("Addition")){
            if(answer == (num1+num2)){
                correctAnswers++;
                Toast.makeText(QuizQuestion.this, "Correct", Toast.LENGTH_SHORT).show();
                newQuestion();
                return true;

            }
        }
        else if(category.equals("Subtraction")){
            if(answer == (num1-num2)){
                correctAnswers++;
                Toast.makeText(QuizQuestion.this, "Correct", Toast.LENGTH_SHORT).show();
                newQuestion();
                return true;
            }
        }
        else if(category.equals("Production")){
            if(answer == (num1*num2)){
                correctAnswers++;
                Toast.makeText(QuizQuestion.this, "Correct", Toast.LENGTH_SHORT).show();
                newQuestion();
                return true;
            }
        }
        return false;

    }
    @Override
    protected void onStart() {
        super.onStart();

        if(!rotate_flag){
            newQuestion();
        }


    }

    public void newQuestion(){

        if(currentQuestion<10){
            cdt.cancel();
            cdt =  new CountDownTimer(5000, 1000) {

                public void onTick(long millisUntilFinished) {
                    textView_timer.setText(millisUntilFinished / 1000+"");
                }

                public void onFinish() {
                    textView_timer.setText("0");
                    Log.d(TAG,"newQuestion finish()");
                    newQuestion();
                }
            };
            currentQuestion++;

            textViewQuesNo.setText("Q."+currentQuestion+" of 10");
            editTextAnswer.setText("");
            Random rand = new Random();


            num1 = rand.nextInt(10);
            num2 = rand.nextInt(10);
            if(num2>num1 && category.equals("Subtraction")){
                int t = num1;
                num1 = num2;
                num2 = t;
            }

            textViewNum2.setText(num2+"");
            textViewNum1.setText(num1+"");

            cdt.start();

        }
        else{
            currentQuestion=-99;
            startActivity(new Intent(this,ResultActivity.class).putExtra("correct_answers",correctAnswers));
            finish();
        }



    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This is called when the Home (Up) button is pressed
                // in the Action Bar.
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                builder.setMessage("Do you want to quit the quiz?")
                        .setTitle("Quit");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User clicked OK button
                        Intent parentActivityIntent = new Intent(getApplicationContext(), MainActivity.class);
                        parentActivityIntent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_TOP |
                                        Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(parentActivityIntent);
                        cdt.cancel();
                        currentQuestion=-99;
                        finish();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

                AlertDialog dialog = builder.create();

                dialog.show();

                return true;


        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("answer", editTextAnswer.getText().toString());
        Log.d(TAG,"timer string --> "+textView_timer.getText().toString());
        Log.d(TAG,"saving timer value as --> "+textView_timer.getText().toString().split(" ")[0]);
        outState.putString("timer", textView_timer.getText().toString().split(" ")[0]);
        outState.putInt("currentquestion", currentQuestion);
        outState.putInt("num1", num1);
        outState.putInt("num2", num2);
        outState.putInt("correctanswers", correctAnswers);
        cdt.cancel();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        cdt.cancel();
        currentQuestion=-99;
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("Do you want to quit the quiz?")
                .setTitle("Quit");

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked OK button
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
        //super.onBackPressed();
    }
}
