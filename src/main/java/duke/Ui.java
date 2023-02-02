package duke;

import java.util.Scanner;

/**
 * Class that deals with printing messages to console.
 */
public class Ui {
    private static final String BANNER_LINE = "_".repeat(30);
    private static final String LOGO = " ,ggg, ,ggggggg,                                                               ,ggg,\n"
                                     + "dP\"\"Y8,8P\"\"\"\"\"Y8b              ,dPYb,                                         dP\"\"8I\n"
                                     + "Yb, `8dP'     `88              IP'`Yb                                        dP   8\n"
                                     + " `\"  88'       88              I8  8I      gg               gg              dP    88\n"
                                     + "     88        88              I8  8bgg,   \"\"               \"\"             ,8'    88\n"
                                     + "     88        88    ,gggg,gg  I8 dP\" \"8   gg    ,gggggg,   gg             d88888888   gg     gg    ,gggg,gg   ,ggg,,ggg,,ggg,    ,ggg,\n"
                                     + "     88        88   dP\"  \"Y8I  I8d8bggP\"   88    dP\"\"\"\"8I   88       __   ,8\"     88   I8     8I   dP\"  \"Y8I  ,8\" \"8P\" \"8P\" \"8,  i8\" \"8i\n"
                                     + "     88        88  i8'    ,8I  I8P' \"Yb,   88   ,8'    8I   88      dP\"  ,8P      Y8   I8,   ,8I  i8'    ,8I  I8   8I   8I   8I  I8, ,8I\n"
                                     + "     88        Y8,,d8,   ,d8b,,d8    `Yb,_,88,_,dP     Y8,_,88,_    Yb,_,dP       `8b,,d8b, ,d8I ,d8,   ,d8b,,dP   8I   8I   Yb, `YbadP'\n"
                                     + "     88        `Y8P\"Y8888P\"`Y888P      Y88P\"\"Y88P      `Y88P\"\"Y8     \"Y8P\"         `Y8P\"\"Y88P\"888P\"Y8888P\"`Y88P'   8I   8I   `Y8888P\"Y888\n"
                                     + "                                                                                            ,d8I'\n"
                                     + "                                                                                          ,dP'8I\n"
                                     + "                                                                                         ,8\"  8I\n"
                                     + "                                                                                         I8   8I\n"
                                     + "                                                                                         `8, ,8I\n"
                                     + "                                                                                          `Y8P\"\n";

    /**
     * Constructor for a UI object.
     */
    public Ui() {
    }

    /**
     * Prints the logo.
     */
    public void printLogo() {
        System.out.println("Hello from\n" + LOGO);
    }

    /**
     * Greets the user.
     */
    public void greet() {
        printLogo();
        printInBanner("Greetings humans~", "Tis I! Nakiri Ayame!", "What can I do for you?");
    }

    /**
     * Reads a command from the user and returns it.
     *
     * @return A String object that is the command from the user.
     */
    public String readCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Pretty prints some text.
     *
     * @param messages Strings to pretty print.
     */
    public void printInBanner(String... messages) {
        System.out.println(BANNER_LINE);
        for (String m : messages) {
            System.out.println(m);
        }
        System.out.println(BANNER_LINE);
    }

    private void printErrorMessage(ErrorEnum e) {
        switch (e) {
            case INVALID_INDEX:
                printInBanner("Can't do that yo~\nInvalid index supplied~");
                break;
            case EMPTY_BODY:
                printInBanner("What do you wanna do yo~");
                break;
            case FILE_SAVE_ERROR:
                printInBanner("Yabai! Could not save to file dayo...");
                break;
            case FILE_LOAD_ERROR:
                printInBanner("Yabai! Could not load from file dayo... dousyou...");
            case ILLEGAL_ARGUMENT:
            default:
                printInBanner("Wakandeyo!!! >:(");
                break;
        }
    }
}
