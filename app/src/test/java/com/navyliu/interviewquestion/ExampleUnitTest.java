package com.navyliu.interviewquestion;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void teset() {
        String str = "猫扑素数有：";
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        for (int i = 5; i < 10000000; i = i + 2) {
            if (isPrime4(primes, i)) {
                primes.add(i);
                if (isMopNumber(i)) str = str + "," + i;
            }
        }
        System.out.println(str);
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