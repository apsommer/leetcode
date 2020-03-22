/*

Given a valid (IPv4) IP address, return a defanged version of that IP address.

A defanged IP address replaces every period "." with "[.]".

Example 1:

Input: address = "1.1.1.1"
Output: "1[.]1[.]1[.]1"
Example 2:

Input: address = "255.100.50.0"
Output: "255[.]100[.]50[.]0"

Constraints:

The given address is a valid IPv4 address.

*/

class Solution {
    public String defangIPaddr(String address) {

        // initialize a blank string
        String output = "";

        // loop through every character in input string
        for (int i = 0; i < address.length(); i++) {

            // check if the character is . not the single quotes ''
            if (address.charAt(i) == '.') output += "[.]";
            else output += address.charAt(i);

        }

        return output;
    }
}
