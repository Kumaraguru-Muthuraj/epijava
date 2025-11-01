package epi.util;

public class ABSqrt2 implements Comparable<ABSqrt2> {
    public ABSqrt2(int a, int b) {
        this.a = a;
        this.b = b;
        this.value = a + b * Math.sqrt(2);
    }
    public int a;
    public int b;
    private double value;

    @Override
    public int compareTo(ABSqrt2 o) {
        return Double.compare(value, o.value);
    }

    @Override
    public String toString() {
        return "a - " + a + " b - " + b + " value - " + this.value;
    }

}
