package Duke;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String s  = "T|0|bonjour s";
        String[] s_part = s.split("\\|");
        System.out.println(Arrays.toString(s_part));
    }
}


