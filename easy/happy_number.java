/*

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example:

Input: 19
Output: true
Explanation:
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1

*/

class Solution {
    public boolean isHappy(int n) {

        // add each summation to a set, if a number is reached again the solution is divergent
        Set set = new HashSet();

        // loop until the happy ending
        int sum = n;
        while (sum != 1) {

            // convert to string so we can examine each character
            String nString = String.valueOf(sum);
            sum = 0;

            // each letter in the string is a numerical digit (int)
            for (int i = 0; i < nString.length(); i ++) {

                char charDigit = nString.charAt(i);
                int digit = Integer.parseInt(String.valueOf(charDigit));
                sum += digit*digit;
            }

            // happy number
            if (sum == 1) return true;

            // solution is divergent, not a happy number
            if (set.contains(sum)) return false;
            set.add(sum);
        }

        // catch trivial edge case: n = 1
        return true;
    }
}
