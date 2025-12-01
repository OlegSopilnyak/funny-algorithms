package oleg.sopilnyak.brace.minimum.swap;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String expression = bufferedReader.readLine().trim();

        int swapToBalanced = Algorithm.minSwapsToBalance(expression);
        System.out.print("Requires " + swapToBalanced + " to balance expression :" + expression);

        bufferedReader.close();
    }
}
