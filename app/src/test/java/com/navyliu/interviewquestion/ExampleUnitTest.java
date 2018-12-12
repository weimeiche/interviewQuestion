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
    public void arrayFind() {
        int[][] array = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10},
                {6, 8, 11, 15}
        };
        System.out.println("array[3][3] is:" + arrayFind1(array, 2));
        System.out.println("array[3][3] is:" + arrayFind2(array, 5));
        System.out.println("array[3][3] is:" + arrayFind3(array, 11));
    }

    /**
     * 每一行都按照从左到右递增的顺序排序，把每一行看成有序递增数组
     * 利用二分查找
     * 通过遍历每一行查找答案
     * 时间复杂度mlog（n）
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind3(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            int begin = 0;
            int end = array[i].length - 1;
            while (begin <= end) {
                int mid = (begin + end) / 2;
                if (keyword > array[i][mid]) {
                    begin = mid + 1;
                } else if (keyword < array[i][mid]) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 利用二位数组由上到下，由左到右递增的规律，
     * 选取左下角或者右上角的元素a[i][j]与keyword进行比较，
     * 当keyword大于a[i][j]的时候，keyword一定在a[i][j]的右边，
     * 即j++
     * 当keyword小于a[i][j]的时候，keyword一定在a[i][j]的上边，
     * 即i--；
     * 时间复杂度m+n
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind2(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        // 左下角
        int i = array.length - 1;
        int j = 0;
        while (i >= 0 && j < array[i].length) {
            if (keyword == array[i][j]) {
                return true;
            } else if (keyword > array[i][j]) {
                j++;
            } else { // keyword < array[i][j])
                i--;
            }
        }
        return false;
    }

    /**
     * 暴力查找
     * 时间复杂度mn
     *
     * @param array
     * @param keyword
     * @return
     */
    private boolean arrayFind1(int[][] array, int keyword) {
        if (array == null || array.length == 0 || (array.length == 1 && array[0].length == 0)) {
            return false;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (keyword == array[i][j]) {
                    return true;
                }
            }
        }
        return false;
    }


    @Test
    public void word_reverse() {
        String str = "i am a student.";
        String reverse_str = reverseChar(str);
        System.out.println("reverse char is:" + reverse_str);
        System.out.println("reverse word is:" + reverseSentence(str));
        System.out.println("reverse left 3 is:" + leftRotateStr(str, 3));
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