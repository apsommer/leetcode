/*
Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are represented with '.'s. You may assume the following rules:
You receive a valid board, made of only battleships or empty slots.
Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.

Example:

X..X
...X
...X
In the above board there are 2 battleships.

Invalid Example:

...X
XXXX
...X
This is an invalid board that you will not receive - as battleships will always have a cell separating between them.

Follow up:

Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
*/

class Solution {
    public int countBattleships(char[][] board) {

        // code traverses in a single pass!

        // trivial case: '.' simply step over this point, continue loop

        // 'X' could be horizontal or vertical
        // check x+1 for horizontal right
        // check y+1 for vertical top
        // increment the counter, flip each X to . after checking

        // count the battleships
        int count = 0;

        // length is instance variable of arrays
        int x = board.length;
        int y = board[0].length;

        // columns on outer loop, reading left-to-right and bottom-to-top, like a trajectory plot
        for (int j = 0; j < y; j++) {

            // rows on inner loop
            for (int i = 0; i < x; i++) {

                // '.' dead space, skip
                if (board[i][j] == '.') {
                    continue;
                }

                // 'X' hit!
                else {

                    // flip this character since we checked it
                    board[i][j] = '.';

                    // never need to check left or bottom since we checked those already

                    // right
                    if (i+1 < x && board[i+1][j] == 'X') {
                        continue;
                    }

                    // top
                    if (j+1 < y && board[i][j+1] == 'X') {
                        continue;
                    }

                    // this is the end of a single, contigous ship
                    count ++;
                }
            }
        }

    return count;
    }
}
