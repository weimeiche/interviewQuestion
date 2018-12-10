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
    public void numberOf1Between1AndNTest() {
        int num = 546016;
        System.out.println(num + "中的1有" + numberOf1Between1AndN_solution1(num) + "个");
        System.out.println(num + "中的1有" + numberOf1Between1AndN_solution2(num) + "个");
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


    @Test
    public void testMopPrimeNumber() {
        String str = "猫扑素数有：";
        ArrayList<Integer> primes = new ArrayList<Integer>();
        primes.add(2);
        primes.add(3);
        for (int i = 5; i < 100000; i = i + 2) {
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