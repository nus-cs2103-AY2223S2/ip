package shigure;

import java.util.Scanner;

public class Ui {
    private boolean asciiOnly;
    private boolean autoDiv = true;

    private Scanner sc = new Scanner(System.in);

    private static final String DIV = "    ____________________________________________________________________________";

    public Ui(boolean asciiOnly) {
        this.asciiOnly = asciiOnly;
    }

    public String readLine() {
        System.out.print(">");
        return sc.nextLine();
    }

    public void printDiv() {
        System.out.println(DIV);
    }

    public void printAutoDiv() {
        if (autoDiv) {
            printDiv();
        }
    }

    public void print(String s) {
        System.out.println("     " + s);
    }

    public void printIntro() {
        printAutoDiv();
        String username = System.getProperty("user.name");
        print("in honour / fuzuki miki / 2020 | 2021");
        if (!asciiOnly) {
            print("\uD83C\uDF80✨");
            print("Hello " + username + " !! Konmiki! ＼(￣▽￣)/");
        } else {
            print("01 f3 80 / 27 28");
            print("Hello " + username + " !! Konmiki! \\(^v^)/");
        }
        printAutoDiv();
    }

}
