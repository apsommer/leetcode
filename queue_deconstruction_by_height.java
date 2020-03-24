/*
Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where h is the height of the person and k is the number of people in front of this person who have a height greater than or equal to h. Write an algorithm to reconstruct the queue.

Note:
The number of people is less than 1,100.

Example

Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]

*/

class Solution {
    public int[][] reconstructQueue(int[][] people) {

        // catch trivial case
        if (people.length == 0) return people;

        // temporary variables
        int tempHeight = 0;
        int[] tempPerson = new int[2];

        // get the passed array reasonably sorted based on the number of people ahead of them
        Arrays.sort(people, (x, y) -> x[1] - y[1]);

        // create a stack and push the first person on it
        Stack stack = new Stack();
        stack.push(people[0][0]);

        // output is sorted when the stack matches the original input length
        while (stack.size() != people.length) {

            // loop through each person
            for (int i = 1; i < people.length; i++) {

                // get attributes
                int[] person = people[i];
                int height = person[0];
                int numTaller = person[1];

                // loop through stack
                while (!stack.isEmpty() && numTaller >= 0) {

                    tempHeight = (int) stack.pop();

                    if (height > tempHeight) continue;
                    else numTaller --;
                }

                // person is in the correct position
                if (numTaller == 0) {

                    // rebuild the stack and move to the next person
                    for (int j = 0; j <= i; j++) {
                        stack.push(people[j][0]);
                    }
                    continue;
                }

                // person needs to move backward
                if (numTaller < 0) {

                    tempPerson = people[i];
                    people[i] = people[i-1];
                    people[i-1] = tempPerson;

                    // restart sort from beginning of the line
                    stack.clear();
                    stack.push(people[0][0]);

                    break;
                }

                // person needs to forward
                if (numTaller > 0 && i < people.length - 1) {

                    tempPerson = people[i];
                    people[i] = people[i+1];
                    people[i+1] = tempPerson;

                    // restart sort from beginning of the line
                    stack.clear();
                    stack.push(people[0][0]);
                    break;
                }
            }
        }

        return people;
    }
}
