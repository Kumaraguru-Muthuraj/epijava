package epi;

import java.util.Random;
import java.util.Stack;

public class StacksAndQueues {
    /* 9.1 - Stack with Max API.
     */
    public static void testStackWithMaxAPI() {
        StackWithMax stk = new StackWithMax();
        Random r = new Random();
        for (int i = 0; i < 15; i++) {
            stk.push(r.nextInt(10));
        }
        stk.print();
        System.out.println();
        while (!stk.isEmpty()) {
            System.out.println("Max - " + stk.max() + " - " + stk.pop());
        }
    }

    /* 9.3 - Check a sequence of (), {}, [] for well-formedness.
     */
    public static boolean bracketsBalanced(String sequence) {
        System.out.println(sequence);
        Stack<Character> buff = new Stack<>();
        int idx = 0;
        while (idx < sequence.length()) {
            if (sequence.charAt(idx) == '(' ||
                    sequence.charAt(idx) == '{' ||
                    sequence.charAt(idx) == '[') {
                buff.push(sequence.charAt(idx));
            } else {
                if (buff.isEmpty()) {
                    return false;
                }
                Character topChar = buff.pop();
                if ((sequence.charAt(idx) == ')' && topChar != '(') ||
                        (sequence.charAt(idx) == '}' && topChar != '{')||
                        (sequence.charAt(idx) == ']' && topChar != '[')) {
                    return false;
                }
            }
            idx++;
        }
        if (!buff.isEmpty()) {
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(bracketsBalanced("()[]{}([{}])"));
        if (true) {
            return;
        }
        // 9.1 - Stack with Max API.
        testStackWithMaxAPI();
    }
}
