import duke.exceptions.OutOfIndexException;
import duke.exceptions.VagueInputException;
import duke.exceptions.WrongBooleanException;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {
    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                + str + "\n"
                + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }

    public static void doGreeting() {
        String greeting = formatStr("Hello! I'm Muse!\n"
                + "What can I do for you?");
        System.out.println(greeting);
    }

    public static void doFarewell() {
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

}
