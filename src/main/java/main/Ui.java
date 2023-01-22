package main;

import java.util.Scanner;

public class Ui {

    private static Scanner scan = new Scanner(System.in);

    public String readCommand() {
        return scan.nextLine();
    }

    public void greet() {
        String res = "Greetings, I'm Sebastian" + "\n" +
                "I'm at your service";
        this.printFormattedString(res);
    }

    public void showError(String errorMessage) {
        this.printFormattedString(errorMessage);
    }

    public static void lineBreak(){
        String line = "-";
        String res = space() + line.repeat(80);
        System.out.println(res);
    }

    public static String space() {
        String space = " ";
        return space.repeat(5);
    }

    public void printFormattedString(String str) {
        lineBreak();
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lines.length; i++) {
            if(i == 0 && lines.length > 1) {
                sb.append(space()).append(lines[i]).append("\n") ;
            } else if (i == lines.length - 1) {
                sb.append(space()).append(lines[i]);
            } else {
                 sb.append(space()).append(space()).append(lines[i]).append("\n");
            }
        }
        System.out.println(sb);
        lineBreak();
    }
    /**
     * Echo whatever the user has typed in
     * @param instruction the user input
     * @return the same user input
     */
    public String echo(String instruction){
        return space() + instruction;
    }



}
