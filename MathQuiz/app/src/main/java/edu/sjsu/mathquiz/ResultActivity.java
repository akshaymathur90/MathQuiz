package edu.sjsu.mathquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

public class ResultActivity extends AppCompatActivity {
    AppCompatTextView textView_correctAnswers;
    AppCompatButton btn_resultOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView_correctAnswers = (AppCompatTextView) findViewById(R.id.tv_correct_answers);
        btn_resultOk = (AppCompatButton) findViewById(R.id.btn_result_ok);

        int correctAnswers = getIntent().getExtras().getInt("correct_answers");

        textView_correctAnswers.setText(correctAnswers+"");

        btn_resultOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
