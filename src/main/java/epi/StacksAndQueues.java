package epi;

import java.util.LinkedList;
import java.util.Queue;
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

    /* 9.6 - Buildings with sunset view.
     */
    public static Stack<Building> getSunsetViewBuildings(LinkedList<Building> buildings) {
        Stack<Building> stk = new Stack<>();
        for (int idx = buildings.size() - 1; idx >= 0; idx--) {
            while (!stk.isEmpty() && buildings.get(idx).height > stk.peek().height) {
                stk.pop();
            }
            stk.push(buildings.get(idx));
        }
        return stk;
    }

    static class Building {
        public Building(Integer id, Integer height) {
            this.id = id;
            this.height = height;
        }
        public String toString() {
            return "Id - " + id + ", " + "Height - " + height;
        }
        Integer id;
        Integer height;
    }

    public static void print(Stack<Building> stack) {
        System.out.println("Printing buildings...");
        for (Building b: stack) {
            System.out.println(b);
        }
    }
    public static void testBuildingSunset() {
        LinkedList<Building> buildings = new LinkedList<>();
        buildings.add(new Building(1, 3));
        buildings.add(new Building(2, 5));
        buildings.add(new Building(3, 6));
        buildings.add(new Building(4, 8));
        buildings.add(new Building(5, 7));
        buildings.add(new Building(6, 6));
        buildings.add(new Building(7, 5));
        buildings.add(new Building(8, 9));

        Stack<Building> buildingsWithView = getSunsetViewBuildings(buildings);
        print(buildingsWithView);

        buildings.clear();
        buildings.add(new Building(1, 3));
        buildings.add(new Building(2, 5));
        buildings.add(new Building(3, 6));
        buildings.add(new Building(4, 8));
        buildings.add(new Building(5, 7));
        buildings.add(new Building(6, 6));
        buildings.add(new Building(7, 5));
        buildings.add(new Building(8, 4));
        buildings.add(new Building(9, 3));
        buildings.add(new Building(10, 1));
        buildings.add(new Building(11, 2));
        buildings.add(new Building(12, 3));
        buildings.add(new Building(13, 4));
        buildings.add(new Building(14, 5));
        buildings.add(new Building(15, 8));
        buildings.add(new Building(16, 9));

        buildingsWithView = getSunsetViewBuildings(buildings);
        print(buildingsWithView);
    }


    /* 9.7 - Level order traversing.
     */
    public static void printByLevels() {
        BinarySearchTree bst = getBST(15);
        Queue<Node2> nodes = new LinkedList<>();
        nodes.add(bst.root);

        while (!nodes.isEmpty()) {
            Node2 curNode = nodes.poll();
            if (curNode.left != null) {
                nodes.add(curNode.left);
            }
            if (curNode.right != null) {
                nodes.add(curNode.right);
            }
            System.out.print(curNode.value + ", ");
        }
    }

    public static BinarySearchTree getBST(int n) {
        BinarySearchTree bst = new BinarySearchTree();
        Integer val;
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            val = r.nextInt(1000) + 1;
            System.out.println("Adding " + val);
            bst.add(val);
        }
        bst.print();
        return bst;
    }

    /* 9.9 - Implement Queue with Stacks
     */
    public static void testQueueWithStacks(int n) {
        QueueWithStacks q = new QueueWithStacks();
        Random r1 = new Random();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            Integer k = r.nextInt(100);
            if (r1.nextBoolean()) {
                System.out.println("Adding " + k);
                q.add(k);
            } else {
                System.out.println("Polling " + q.poll());
            }
        }
        System.out.println("Done...");
        for (int i = 0; i < n; i++) {
            Integer p = q.poll();
            if (p != null) {
                System.out.println("Polling " + p);
            }
        }
    }

    /* 9.10 - Queue with Max API.
     */
    public static void testQueueWithMaxAPI(int n) {
        QueueWithMax queueWithMax = new QueueWithMax();
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            queueWithMax.add(r.nextInt(20));
            queueWithMax.print();
        }
    }

    public static void main(String[] args) {
        // 9.10 - Queue with Max API.
        testQueueWithMaxAPI(20);

        if (true) {
            return;
        }
        // 9.6 - Buildings with sunset view.
        testBuildingSunset();

        // 9.9 - Implement Queue with Stacks
        testQueueWithStacks(25);
        Integer k = null;
        System.out.print(k);

        // 9.7 - Level order traversing.
        printByLevels();

        // 9.3 - Check a sequence of (), {}, [] for well-formedness.
        System.out.println(bracketsBalanced("()[]{}([{}])"));

        // 9.1 - Stack with Max API.
        testStackWithMaxAPI();
    }
}
