package edu.sjsu.mathquiz;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by akshaymathur on 9/30/16.
 */
public class NumericPadFragment extends Fragment {
    AppCompatButton one,two,three,four,five,six,seven,eight,nine,zero;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.numericpadlayout,container,false);

        one = (AppCompatButton) view.findViewById(R.id.btn_one);
        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(1);
            }
        });
        two = (AppCompatButton) view.findViewById(R.id.btn_two);
        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(2);
            }
        });
        three = (AppCompatButton) view.findViewById(R.id.btn_three);
        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(3);
            }
        });
        four = (AppCompatButton) view.findViewById(R.id.btn_four);
        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(4);
            }
        });
        five = (AppCompatButton) view.findViewById(R.id.btn_five);
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(5);
            }
        });
        six = (AppCompatButton) view.findViewById(R.id.btn_six);
        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(6);
            }
        });
        seven = (AppCompatButton) view.findViewById(R.id.btn_seven);
        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(7);
            }
        });
        eight = (AppCompatButton) view.findViewById(R.id.btn_eight);
        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(8);
            }
        });
        nine = (AppCompatButton) view.findViewById(R.id.btn_nine);
        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(9);
            }
        });
        zero = (AppCompatButton) view.findViewById(R.id.btn_zero);
        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setValueUsingNumPad(0);
            }
        });

        return view;
    }

    public void setValueUsingNumPad(int num){
        AppCompatEditText ans = (AppCompatEditText) getFragmentManager().findFragmentById(R.id.fragment_question).getView().findViewById(R.id.ed_answer);
        ans.setText(ans.getText().toString()+num);

    }

}
