package epi;

import java.util.Random;

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

    public static void main(String[] args) {
        // 9.1 - Stack with Max API.
        testStackWithMaxAPI();
    }
}
