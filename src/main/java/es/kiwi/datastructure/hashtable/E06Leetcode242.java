package es.kiwi.datastructure.hashtable;

import java.util.Arrays;

/**
 * 判断字母异位词
 * 小写字母组成
 */
public class E06Leetcode242 {

    /*
        输入: s = "anagram", t = "nagaram"
        输出: true

        1. 拿到字符数组，排序后比较字符数组
        2. 字符打散放入 int[26]，比较数组
         a                 g                 m  n           r
        [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]

     */

    public boolean isAnagram(String s, String t) {
        return Arrays.equals(getKey(s), getKey(t));
    }

    private int[] getKey(String s) {
        int[] array = new int[26];
        char[] chars = s.toCharArray();
        for (char c : chars) {
            array[c - 97]++;
        }
        return array;
    }
    private int[] getKey1(String s) {
        int[] array = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // 'a' - 97 = 0
            array[c - 97]++;
        }

        return array;
    }
}
