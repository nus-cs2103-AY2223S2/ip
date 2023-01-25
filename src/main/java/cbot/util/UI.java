package cbot.util;

import cbot.task.Task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    private final Scanner sc;
    
    // Frequently Used Strings
    private static String BLANK     = "        ";
    private static String INDENT    = "      ~ ";
    private static String WARNING   = "     !! ";
    private static String ERROR     = WARNING + "<Error> ";
    private static String PROMPT    = "\n v v\n";
    private static String STALL     = "\n   o\n   o\n   o\n\n";
    
    public UI() {
        this.sc = new Scanner(System.in);
    }
    
    public String askUser() throws BadInputException {
        System.out.println(PROMPT);
        String userInput = this.sc.nextLine();
        System.out.println();
        
        if (userInput.contains(Task.SEP)) {
            throw new BadInputException("Please avoid using: \"" + Task.SEP + "\"");
        }
        
        return userInput;
    }
    
    public static void sayNewFile(FileStuff fs) {
        System.out.println(WARNING + "No save file found. Making a new save at " + fs.getPath());
    }
    
    public static void sayHi() {
        String dukeLogo = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        
        String cbotLogo = "  _____  _            _\n"
                + " /  ___]| |   ^-^   _| |_\n"
                + "|  / :D | |___  ___[_ + _]\n"
                + "|  \\___ | / . \\/ . \\ | |\n"
                + " \\_____]|_,_*_/\\_*_/ |_/\n";
        
        System.out.println(STALL + INDENT + "Hey! D:< I'm not\n" + dukeLogo);
        System.out.println(INDENT + "I'm\n" + cbotLogo);
        System.out.println(STALL + INDENT
                + "How can I help you today?");
    }
    
    public static void sayBye() {
        System.out.println(STALL + INDENT + "See you again!");
    }
    
    public static void warn(Exception e) {
        System.out.println(WARNING + e.getMessage());
    }
    
    public static void warnBad(Exception e) {
        System.out.println(ERROR + e.getMessage());
    }
    
    public static void warnTime() {
        System.out.println(ERROR + "Sorry, I don't know how to interpret that datetime");
        System.out.println(WARNING + "Try something in the form: yyyy-MM-dd HH:mm");
    }

    static void say(String str) {
        System.out.println(INDENT + str);
    }
    
    static void printMany(ArrayList<String> arr) {
        for (String s : arr) {
            System.out.println(BLANK + s);
        }
    }
}