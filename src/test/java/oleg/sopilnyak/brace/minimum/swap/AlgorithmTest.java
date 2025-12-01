package oleg.sopilnyak.brace.minimum.swap;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

class AlgorithmTest {

    @Test
    void shouldCalculateMinSwapsToBalance() {

        assertEquals(0, Algorithm.minSwapsToBalance("{{}}{}"));
        assertEquals(1, Algorithm.minSwapsToBalance("}}{{"));
        assertEquals(1, Algorithm.minSwapsToBalance("{}}{}{"));
    }

    @Test
    void shouldNotCalculateMinSwapsToBalance() {

        assertEquals(-1, Algorithm.minSwapsToBalance("}{{}}{{{"));
    }

    @Test
    void name() {
        assertEquals(1, swapCount("{}}{}{"));
    }
    static int  swapCount(String s){
        char[] chars = s.toCharArray();
        int countLeft = 0, countRight = 0;

        // swap stores the number of swaps required
        //imbalance maintains the number of imbalance pair
        int swap = 0 , imbalance = 0;

        for(int i =0; i< chars.length; i++) {
            if(chars[i] == '{'){
                countLeft++;
                if(imbalance > 0){

                    // swaps count is last swap count + total
                    // number imbalanced brackets
                    swap += imbalance;

                    // imbalance decremented by 1 as it solved
                    // only one imbalance of Left and Right
                    imbalance--;
                }
            } else if(chars[i] == '}' ) {
                countRight++;

                // imbalance is reset to current difference
                // between Left and Right brackets
                imbalance = (countRight-countLeft);
            }
        }
        return swap;
    }
    public static int  swapCount3(String s){

        // Keep track of '['
        List<Integer> pos = new ArrayList<>();
        for(int i = 0; i < s.length(); ++i)
            if (s.charAt(i) == '{')
                pos.add(i);

        // To count number of encountered '['
        int count = 0;

        // To track position of next '[' in pos
        int p = 0;

        // To store result
        int  sum = 0;
        char[] S = s.toCharArray();
        for(int i = 0; i < s.length(); ++i){

            // Increment count and move p
            // to next position
            if (S[i] == '{')
            {
                count++;
                p++;
            }
            else if (S[i] == '}')
                count--;

            // unbalanced part of string
            if (count < 0)
            {
                sum += pos.get(p) - i;
                char temp = S[i];
                S[i] = S[pos.get(p)];
                S[pos.get(p)] = temp;
                p++;

                // Reset count to 1
                count = 1;
            }
        }
        return sum;
    }

    static int swapCount2(String s) {
        int ans = 0;

        //To store count of '['
        int count = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '{')
                count++;
            else
                count--;

            //When count becomes less than 0
            if (count < 0) {

                //Start searching for '[' from (i+1)th index
                int j = i + 1;
                while (j < n) {

                    //When jth index contains '['
                    if (s.charAt(j) == '{')
                        break;
                    j++;
                }

                //Increment answer
                ans += j - i;

                //Set Count to 1 again
                count = 1;

                //Bring character at jth position to ith position
                //and shift all character from i to j-1
                //towards right
                char ch = s.charAt(j);
                StringBuilder newString = new StringBuilder(s);
                for (int k = j; k > i; k--) {
                    newString.setCharAt(k, s.charAt(k - 1));
                }
                newString.setCharAt(i, ch);
                s = newString.toString();
            }
        }

        return ans;
    }
}