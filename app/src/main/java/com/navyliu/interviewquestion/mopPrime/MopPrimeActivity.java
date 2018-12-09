package com.navyliu.interviewquestion.mopPrime;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.navyliu.interviewquestion.R;

import java.util.ArrayList;

public class MopPrimeActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mop_prime);

        textView = (TextView) this.findViewById(R.id.textview);


        new Runnable() {
            @Override
            public void run() {
                String str = "猫扑素数有：";
                ArrayList<Integer> primes = new ArrayList<Integer>();
                primes.add(2);
                primes.add(3);
                for (int i = 5; i < Integer.MAX_VALUE; i = i + 2) {
                    if (isPrime4(primes, i)) {
                        primes.add(i);
                        if (isMopNumber(i)) str = str + "," + i;
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
        int sqrt = (int) Math.floor(Math.sqrt(n));
        for (int i = 2; i <= sqrt; i++) {
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

    /**
     * 如果是质数，一定不能被小于等于本身平方根的质数整除
     *
     * @param n
     * @return
     */
    private boolean isPrime4(int[] prime, int n) {
        int sqrt = (int) Math.floor(Math.sqrt(n));
        for (int i = 0; i < prime.length; i++) {

        }
        return false;
    }

    /**
     * 判断是否为素数
     * 素数一定不能被小于平方根的素数整除
     *
     * @param primes
     * @param n
     * @return
     */
    private boolean isPrime4(ArrayList<Integer> primes, int n) {
        int s = (int) Math.floor(Math.sqrt(n));
        for (Integer i : primes) {
            if (i > s) return true;
            if (n % i == 0) return false;
        }
        return true;
    }

}
