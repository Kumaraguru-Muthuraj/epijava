package epi;

public class PostingNode {
    public PostingNode(Integer id) {
        this.id = id;
        this.jumpNext = null;
        this.next = null;
        this.visited = false;
    }
    
    public Integer id;
    public PostingNode jumpNext;
    public PostingNode next;
    public boolean visited;
}