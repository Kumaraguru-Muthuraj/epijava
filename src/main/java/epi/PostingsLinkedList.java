package epi;

import java.util.Random;
import java.util.Stack;

public class PostingsLinkedList {
    public PostingNode add(Integer id) {
        if (head == null) {
            head = new PostingNode(id);
            return head;
        }
        PostingNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        PostingNode node = new PostingNode(id);
        cur.next = node;
        return node;
    }

    public PostingNode getHead() {
        return head;
    }

    public void setPostingsLink() {
        int k = 1;
        for (PostingNode cur = head; cur != null; cur = cur.next) {
            k ^= 1;
            if (k == 1 && cur.next != null && cur.next.next != null) {
                cur.jumpNext = cur.next.next;
            }
        }
    }

    public void printJumpOrder(PostingNode cur) {
        if (cur != null) {
            if (cur.visited)
                return;
            cur.visited = true;
            System.out.print(cur.id + ", ");
            printJumpOrder(cur.jumpNext);
            printJumpOrder(cur.next);
        }
    }

    Stack<PostingNode> stack = new Stack<>();
    public void clearVisited() {
        for (PostingNode cur = head; cur != null; cur = cur.next) {
            cur.visited = false;
        }
    }
    public void printJumpOrderHelper2() {
        while (!stack.isEmpty()) {
            PostingNode cur = stack.pop();
            if (!cur.visited) {
                cur.visited = true;
                System.out.print(cur.id + ", ");
                if (cur.next != null)
                    stack.push(cur.next);
                if (cur.jumpNext != null)
                    stack.push(cur.jumpNext);
            }
        }
    }
    public void printJumpOrder2(PostingNode cur) {
        System.out.println();
        stack.clear();
        stack.push(cur);
        printJumpOrderHelper2();
    }

    public void print() {
        for (PostingNode cur = head; cur != null; cur = cur.next) {
            System.out.print(cur.id + ", ");
        }
        System.out.println();
    }

    protected PostingNode head;

    public PostingNode getRandomNode() {
        Random r = new Random();
        PostingNode cur = head;
        while (cur != null) {
            if (r.nextBoolean()) {
                return cur;
            }
            cur = cur.next;
        }
        return head;
    }

    public static PostingsLinkedList getAPostingsList(int k) {
        PostingsLinkedList l = new PostingsLinkedList();
        Random r = new Random();
        for (int i = 1; i <= k; i++) {
            l.add(r.nextInt(50) + 1);
        }
        l.setPostingsLink();
        return l;
    }
}