package Face;

import java.util.*;
 
 
public class Solution {
    /**
     * 
     * @param s string×Ö·û´® 
     * @return intÕûĞÍ
     */
    public static int solve (String s) {
        // write code here
        int begin = 0;
        int end = 0;
        int result = 0;
        for(int i = 0; end < s.length(); i++) {
            if ( (Character.isDigit(s.charAt(i))) || (s.charAt(i) >= 'A' && s.charAt(i) <= 'F') ) {
                end++;
                result = Math.max(Integer.parseInt(s.substring(begin, end),16), result);
            } else {
                begin = i+1;
                end = begin;
            }
        }
        return result;
    }
    public static void main(String args[]) {
    	String s="122ahksdl776GHawd85asGHkl75";
    	System.out.print(solve(s));
    }
}