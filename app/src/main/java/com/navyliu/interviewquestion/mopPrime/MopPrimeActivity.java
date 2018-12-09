package com.navyliu.interviewquestion.mopPrime;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.navyliu.interviewquestion.R;

public class MopPrimeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mop_prime);

        textView = (TextView) this.findViewById(R.id.textview);


        new Runnable(){
            @Override
            public void run() {

                String str = "猫扑素数有：";
                for (int i = 1; i < 1000000; i++) {
                    if (isMopNumber(i)) {
                        if (isPrime3(i)) {
                            str = str + "," + i;
                        }
                    }
                }
                textView.setText(str);
            }
        }.run();
    }


    /**
     * 是否为猫扑数
     * 猫扑数，即是以2为首位，后面跟N个3的自然数
     *
     * @param m
     */
    private boolean isMopNumber(int m) {
        if (m < 10) return m == 2;
        return isMopNumber(m / 10) && (m % 10 == 3);
    }

    /**
     * 是否为质数（素数）
     * 素数,除了1和本身以外没有其他因数的自然数
     *
     * @param n
     * @return
     */
    private boolean isPrime1(int n) {
        for (int i = 2; i < n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    /**
     * 判断是否为素数
     * 素数的第一个大于1的因数一定小于等于其平方根
     *
     * @param n
     * @return
     */
    private boolean isPrime2(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否为素数
     * 任何一个自然数都能写成6N，6N+1,6N+2,6N+3,6N+4,6N+5的形式，
     * 其中在N大于0的时候，只有6N+1和6N+5可能为素数
     *
     * @param n
     * @return
     */
    private boolean isPrime3(int n) {
        if (n < 6) return n != 4;
        if (n % 6 == 1 || n % 6 == 5) {
            return isPrime2(n);
        }
        return false;
    }
}
