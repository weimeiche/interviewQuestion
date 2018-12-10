package com.navyliu.interviewquestion.NumberOf1Between1AndN;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.navyliu.interviewquestion.R;

public class NumberOf1Between1AndN extends AppCompatActivity implements View.OnClickListener {

    private EditText endEdit;
    private Button confirmBtn;
    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_of_1_between_1_and_n);
        init();
    }

    private void findViewId() {
        endEdit = (EditText) this.findViewById(R.id.edit_end);
        confirmBtn = (Button) this.findViewById(R.id.btn_confirm);
        textView = (TextView) this.findViewById(R.id.txt_1);
        confirmBtn.setOnClickListener(this);
    }

    private void init() {
        findViewId();
    }

    @Override
    public void onClick(View view) {
        String end = endEdit.getText().toString();
        if (TextUtils.isEmpty(end)) {
            end = "0";
        }
        int num = Integer.parseInt(end);
        if (num == 0) {
            Toast.makeText(this, "请输入合法的数值", Toast.LENGTH_SHORT).show();
            return;
        }
        int count = numberOf1Between1AndN_solution2(num);
        textView.setText("从1到" + num + "包含1的个数为" + count);
    }


    /**
     * @param n
     * @return
     */
    private int numberOf1Between1AndN_solution1(int n) {
        int count = 0;
        int temp_num = 0;
        for (int i = 0; i <= n; i++) {
            temp_num = i;
            while (temp_num != 0) {
                if (temp_num % 10 == 1) {
                    count++;
                }
                temp_num /= 10;
            }
        }
        return count;
    }

    /**
     * 找出从 1 到 n 整数中1出现的次数
     * 主要实现思路：
     * 1、设定整数点（1/10/100等）作为设置点i（对应n的个位、十位、百位...），分别对每个数位上包含多少个1进行分析统计；
     * 2、根据设定的整数位置，对n进行分割，分为两部分，高位n/i,低位n%i；
     * 3、当i 表示百位，且百位对应的数大于等于2，如31456，i=100，则a=314，b=56，此时，百位位1的次数为a/100+1 = 32(最高两位0~31)，
     * 每次都包含100个连续的点，即共有（a%10+1）*100个 百位为1的情况；
     * 4、当i 表示百位，且百位对应的数为1，如31156，i=100，则a=311，b=56，此时，百位对应的就是1，则共有a%10 = 31（最高两位0~30）次，
     * 当最高位为31（即是311），本次对应的只有0~56次，共b+1次，所有加起来共有（a%10*100）+（b+1）
     * 5、当i 表示百位，且百位对应的数是0，如31056，i=100，则a=310，b=56，此时，百位为1的次数有a/10 = 31(最高两位0~30)
     * 6、综合以上三种情况，当百分位对应0或者>=2的时候，有(a+8)/10次所有100个数，当百位为1（a%10 == 1），需要加上低位的数b+1；
     * 7、之所以补8，是因为当百位为0的时候不进位，大于等于2的时候要进位
     *
     * @param n
     * @return
     */
    private int numberOf1Between1AndN_solution2(int n) {
        int count = 0;
        for (int i = 1; i <= n; i *= 10) {
            int high = n / i;
            int low = n % i;
            count += (high + 8) / 10 * i + (high % 10 == 1 ? low + 1 : 0);
        }
        return count;
    }
}
