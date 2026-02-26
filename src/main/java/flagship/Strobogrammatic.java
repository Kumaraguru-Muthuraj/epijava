package flagship;

public class Strobogrammatic {
    public static char getStrobo(char c) {
        if (c == '0') {
            return '0';
        }
        if (c == '1') {
            return '1';
        }
        if (c == '6') {
            return '9';
        }
        if (c == '8') {
            return '8';
        }
        if (c == '9') {
            return '6';
        }
        return 'X';
    }
    public static boolean isStrobogrammatic (String num)
    {
        int l = num.length() - 1;
        for (int s = 0, e = l; s <= l; s++, e--) {
            if (num.charAt(s) != getStrobo(num.charAt(e))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStrobogrammatic("3"));
    }
}
