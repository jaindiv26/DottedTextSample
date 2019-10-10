package com.e.dottedtextsample;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        String string = "Android is a mobile operating system based on a modified version of the Linux kernel and other open source software, designed primarily for touchscreen mobile devices such as smartphones and tablets. ";

        String textToUnderline = "modified version of the Linux kernel";

        SpannableString text = new SpannableString(string);

        int[] range = getStartingAndEndOfSentence(string, textToUnderline);

        DottedLineSpan dottedLineSpan = new DottedLineSpan(R.color.colorPrimary, textToUnderline, this);
        text.setSpan(dottedLineSpan, range[0], range[1], Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(text);
    }

    int[] getStartingAndEndOfSentence(String wholeString, String partOfAString) {

        int[] range = new int[2];

        String[] s1 = wholeString.split("\\s+");
        String[] s2 = partOfAString.split("\\s+");

        if (s2.length == 1) {
            String word = s2[0];
            range[0] = wholeString.indexOf(word);
            range[1] = range[0] + word.length();
        } else {
            int length = 0;
            for (int i = 0; i < s1.length; i++) {
                length = length + s1[i].length() + 1;
                if (s1[i].equals(s2[0])) {
                    if(s1[i+1].equals(s2[1])) {
                        range[0] = length - (s1[i].length() + 1);
                        range[1] = range[0] + partOfAString.length();
                        break;
                    }
                }
            }
        }
        return range;
    }
}
