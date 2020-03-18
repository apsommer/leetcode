/*

You have a list of words and a pattern, and you want to know which words in words matches the pattern.

A word matches the pattern if there exists a permutation of letters p so that after replacing every letter x in the pattern with p(x), we get the desired word.

(Recall that a permutation of letters is a bijection from letters to letters: every letter maps to another letter, and no two letters map to the same letter.)

Return a list of the words in words that match the given pattern.

You may return the answer in any order.

Example 1:

Input: words = ["abc","deq","mee","aqq","dkd","ccc"], pattern = "abb"
Output: ["mee","aqq"]
Explanation: "mee" matches the pattern because there is a permutation {a -> m, b -> e, ...}.
"ccc" does not match the pattern because {a -> c, b -> c, ...} is not a permutation,
since a and b map to the same letter.

Note:

1 <= words.length <= 50
1 <= pattern.length = words[i].length <= 20

*/

class Solution {
    public List<String> findAndReplacePattern(String[] words, String pattern) {

        // initialize final result
        List<String> output = new ArrayList<>();

        // for each word in the input array
        for (String word : words) {

            // initialize a blank permutation
            List<Character> perm = new ArrayList<>();
            List<Character> track = new ArrayList<>();

            // for each character in the word
            for (int i = 0; i < word.length(); i++) {

                char charP = pattern.charAt(i);
                char charW = word.charAt(i);

                // pattern has duplicate
                if (track.contains(charP)) {

                    // get index of existing pattern character
                    int j = track.indexOf(charP);

                    // valid permutation
                    if (word.charAt(i) == word.charAt(j)) {

                        track.add(charP);
                        perm.add(charW);

                    // invalid permutation
                    } else {

                        perm = new ArrayList<>();
                        track = new ArrayList<>();
                        break;
                    }

                // this is a new letter in the pattern
                // check that this is also a new letter in the permutation
                } else if (!perm.contains(charW)){

                    track.add(charP);
                    perm.add(charW);

                // invalid permutation
                } else {

                    perm = new ArrayList<>();
                    track = new ArrayList<>();
                    break;
                }

            }

            // all characters in permutation valid, convert to required string output format
            if (perm.size() == pattern.length()) {

                StringBuilder sb = new StringBuilder();
                for (Character c : perm) {
                    sb.append(c);
                }

                output.add(sb.toString());
            }
        }

        return output;
    }
}
