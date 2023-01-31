package duke.utils;

import java.util.Scanner;

public class Parser {
    private static Scanner sc = new Scanner(System.in);
    private String[] tokens;

    public String[] tokenise() {
        this.tokens = Parser.sc.nextLine().split(" ");
        return this.tokens;
    }
    
}
