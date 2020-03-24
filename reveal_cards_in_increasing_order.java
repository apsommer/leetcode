/*

In a deck of cards, every card has a unique integer.  You can order the deck in any order you want.

Initially, all the cards start face down (unrevealed) in one deck.

Now, you do the following steps repeatedly, until all cards are revealed:

Take the top card of the deck, reveal it, and take it out of the deck.
If there are still cards in the deck, put the next top card of the deck at the bottom of the deck.
If there are still unrevealed cards, go back to step 1.  Otherwise, stop.
Return an ordering of the deck that would reveal the cards in increasing order.

The first entry in the answer is considered to be the top of the deck.

Example 1:

Input: [17,13,11,2,3,5,7]
Output: [2,13,3,11,5,17,7]
Explanation:
We get the deck in the order [17,13,11,2,3,5,7] (this order doesn't matter), and reorder it.
After reordering, the deck starts as [2,13,3,11,5,17,7], where 2 is the top of the deck.
We reveal 2, and move 13 to the bottom.  The deck is now [3,11,5,17,7,13].
We reveal 3, and move 11 to the bottom.  The deck is now [5,17,7,13,11].
We reveal 5, and move 17 to the bottom.  The deck is now [7,13,11,17].
We reveal 7, and move 13 to the bottom.  The deck is now [11,17,13].
We reveal 11, and move 17 to the bottom.  The deck is now [13,17].
We reveal 13, and move 17 to the bottom.  The deck is now [17].
We reveal 17.
Since all the cards revealed are in increasing order, the answer is correct.

Note:

1 <= A.length <= 1000
1 <= A[i] <= 10^6
A[i] != A[j] for all i != j

*/

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {

        // trivial case
        if (deck.length == 1) return deck;

        // sort the passed deck in default ascending order
        Arrays.sort(deck);

        // trivial case
        if (deck.length == 2) return deck;

        // initialize final output
        int[] output = new int[deck.length];

        // determine if passed deck has even or odd number of cards
        Boolean isEven = false;
        if (deck.length % 2 == 0) isEven = true;

        // the first half of the deck is trivial ascending order
        int j = 0;
        for (int i = 0; i < deck.length/2; i++) {

            output[j] = deck[i];
            j += 2;
        }

        // get the remaining cards to be shuffled
        int[] remainder = new int[deck.length/2];
        if (!isEven) {
            output[output.length - 1] = deck[deck.length/2];
            remainder = Arrays.copyOfRange(deck, (deck.length/2) + 1, deck.length);

        } else {
            remainder = Arrays.copyOfRange(deck, deck.length/2, deck.length);
        }

        // save the original card values and build a simple ascending list of numbers
        List<Integer> shuffle = new ArrayList<>();
        List<Integer> originals = new ArrayList<>();
        for (int i =0; i < remainder.length; i++) {
            shuffle.add(i);
            originals.add(remainder[i]);
        }

        // odd number of cards needs one more flip to follow proceedure
        if (!isEven) shuffle.add(shuffle.remove(0));

        // shuffle the cards
        List<Integer> ready = new ArrayList<>();
        while (!shuffle.isEmpty()) {

            ready.add(shuffle.remove(0));
            if (shuffle.isEmpty()) break;
            shuffle.add(shuffle.remove(0));
        }

        // refer back to original card values for final placement
        shuffle = new ArrayList<>(ready);
        for (int i = 0; i < ready.size(); i ++) {

            int position = ready.get(i);
            shuffle.set(position, originals.get(i));
        }

        // place shuffled cards into final deck
        j = 1;
        for (int i = 0; i < shuffle.size(); i ++) {
            output[j] = shuffle.get(i);
            j += 2;
        }

        return output;
    }
}
