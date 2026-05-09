package epi;

import java.util.ArrayList;
//Attempted this for speed check - 45 mins. Already code is in Strings.java
public class Mnemonics {
    public static ArrayList<String> alphabets = new ArrayList<>();
    public static ArrayList<String> mnemonics = new ArrayList<>();
    public static StringBuilder mnemonic = new StringBuilder();
    public static void getMnemonics(String phoneNumber) {
        getSingleMnemonic(phoneNumber, 0);
    }

    public static void getSingleMnemonic(String phoneNumber, int cIdx) {
        if (cIdx < phoneNumber.length()) {
            int cNum = phoneNumber.charAt(cIdx) - '0';
            String cRow = alphabets.get(cNum);
            for (int i = 0; i < cRow.length(); i++) {
                mnemonic.append(cRow.charAt(i));
                getSingleMnemonic(phoneNumber, cIdx + 1);
                if (mnemonic.length() == phoneNumber.length()) {
                    mnemonics.add(mnemonic.toString());
                }
                mnemonic.deleteCharAt(mnemonic.length() - 1);
            }
        }
    }

    public static void main(String[] args) {
        alphabets.clear();
        alphabets.add("0");
        alphabets.add("1");
        alphabets.add("ABC");
        alphabets.add("DEF");
        alphabets.add("GHI");
        alphabets.add("JKL");
        alphabets.add("MNO");
        alphabets.add("PQRS");
        alphabets.add("TUV");
        alphabets.add("WXYZ");

        getMnemonics("9880");

    }

}
