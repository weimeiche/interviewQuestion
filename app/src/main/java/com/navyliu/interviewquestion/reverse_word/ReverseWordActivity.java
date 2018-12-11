package com.navyliu.interviewquestion.reverse_word;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.navyliu.interviewquestion.R;

public class ReverseWordActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText wordEdit;
    private TextView resultTxt;
    private Button wordBtn;
    private Button endBtn;
    private Button charBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reverse_word);
        findViewId();
        setListener();
    }

    private void findViewId() {
        wordEdit = (EditText) this.findViewById(R.id.edit_word);
        resultTxt = (TextView) this.findViewById(R.id.txt_result);
        wordBtn = (Button) this.findViewById(R.id.btn_word);
        endBtn = (Button) this.findViewById(R.id.btn_end);
        charBtn = (Button) this.findViewById(R.id.btn_char);
    }

    private void setListener() {
        wordBtn.setOnClickListener(this);
        charBtn.setOnClickListener(this);
        endBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_char:
                resultTxt.setText(reverseChar(wordEdit.getText().toString()));
                break;
            case R.id.btn_word:
                resultTxt.setText(reverseSentence(reverseChar(wordEdit.getText().toString())));
                break;
            case R.id.btn_end:
                resultTxt.setText(leftRotateStr(wordEdit.getText().toString(), 2));
                break;
            default:
                ;
        }
    }

    /**
     * 旋转字符串
     * ps：输入abcdefg 输出gfedcba
     *
     * @param str
     * @return
     */
    public String reverseChar(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < (chars.length + 1) / 2; i++) {
            char temp = chars[i];
            chars[i] = chars[chars.length - i - 1];
            chars[chars.length - i - 1] = temp;
        }
        return String.valueOf(chars);
    }

    /**
     * 旋转单词，遇到空格作为一个单词结束
     * ps：输入how are you 输出 woh era uoy
     *
     * @param sentence
     * @return
     */
    public String reverseSentence(String sentence) {
        if (sentence == null) return "";
        String sentenceReverse = reverseChar(sentence);
        String[] splitSentence = sentenceReverse.split(" ");
        String resultStr = "";
        for (String str : splitSentence) {
            resultStr = resultStr + reverseChar(str) + " ";
        }
        return resultStr;
    }

    /**
     * 左旋转N位到尾部
     * ps：传入abcdefg，2 输出 cdefgab
     *
     * @param str
     * @param index
     * @return
     */
    public String leftRotateStr(String str, int index) {
        if (str == null || index > str.length() || index < 0) return "";
//        String leftStr = str.substring(0, index);
//        String rightStr = str.substring(index);
//        return rightStr + leftStr;
        String[] strArr = {str.substring(0, index), str.substring(index)};
        String tempStr = "";
        for (String s : strArr) {
            tempStr += reverseChar(s);
        }
        return reverseChar(tempStr);
    }

}
