package helpers;

import duke.exceptions.OutOfIndexException;
import duke.exceptions.VagueInputException;
import duke.exceptions.WrongBooleanException;

import java.io.PrintWriter;
import java.util.Scanner;

public class Ui {

    /**
     * Returns lateral location of the specified position.
     * If the position is unset, NaN is returned.
     *
     * @param str String to be formatted, which will be shown in terminal.
     */
    public static String formatStr(String str) {
        String returnstr =  ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n"
                + str + "\n"
                + "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
        return returnstr;
    }

    /**
     * Prints greeting message upon startup.
     */
    public static void doGreeting() {
        String greeting = formatStr("Hello! I'm Muse!\n"
                + "What can I do for you?");
        System.out.println(greeting);
    }

    /**
     * Prints farewell message before program terminates.
     */
    public static void doFarewell() {
        String goodbyeMessage = formatStr("Bye. Come back again!");
        System.out.println(goodbyeMessage);
    }

}
