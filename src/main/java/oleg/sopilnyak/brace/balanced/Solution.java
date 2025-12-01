package oleg.sopilnyak.brace.balanced;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String expression = bufferedReader.readLine().trim();

        boolean isBalanced = Algorithm.isBalancedParentheses(expression);
        System.out.print(expression + " is balanced :");
        System.out.println(isBalanced ? "YES" : "NO");

        bufferedReader.close();
    }
}
