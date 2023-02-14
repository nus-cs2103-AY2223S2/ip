package duke.util;

import java.util.Scanner;

import duke.task.Task;

/**
 * Represents the user interface of the program.
 * The Ui object is responsible for printing messages to the user.
 */
public class Ui {
    private static final String DASHED_LINE = "____________________________________________________________";
    private static final String LS = System.lineSeparator();
    private static final String LINE_PREFIX = "    ";
    private static final String MESSAGE_WELCOME = "You've found the Tohtoro!";
    private static final String MESSAGE_GOODBYE = "Meowww. Hope to see you soon!\n"
            + "THE TOHTORO PROGRAM HAS STOPPED";
    private static final String LOGO = ".                   *3k~               <Ry.              .\n"
            + ".                  x#86D.             T#$6h              .\n"
            + ".                  Q#666y            *#Q66D              .\n"
            + ".                 ,#BOO6D;          -Q#D66O              .\n"
            + ".                 !#BO66D^          c##06Rv              .\n"
            + ".                  0#866Qx:~*)((r^=,k#QR$)               .\n"
            + ".                  x##Q9009666OO66690$0g6'               .\n"
            + ".                 rD#Q06OO6OOOOOOOOOOO69R|`              .\n"
            + ".               =M#B$KeM9OOOOO666O66OOseOR3'             .\n"
            + ".      _:=~:-` ~###8*vByeO6E$####$66DrBG=E6s`:^^~!`      .\n"
            + ".   `...``',~^d##@#RwxyVdOOO6gQQ$6O6EITxzRgQQ)_-----.    .\n"
            + ".   `_:!=~~~~m@@##QO6666OOOOOO666OO6O6666ORB#QyY|<!:,`   .\n"
            + ".     -:!==~rQ####866OOOOOOOOOOOOOOO666OOOOR0$3~=;~!,    .\n"
            + ".    ._.`  ~8#####BO6OOOOO6OO6EDOO6OOOOOOOOOO66Z<        .\n"
            + ".        =5#######g6609bMjyVVyjhwykMWdE66O66OOOORw`      .\n"
            + ".      _3########gOsc}^~~~~~~~~~~~~~~~vxy5966OOOOOh:     .\n"
            + ".     _Q########Wx<~~~~~~~^TsGHk\\~~~~~*]i~*yGROOOOO9^    .\n"
            + ".    _Q######@Dx<}adbX|~~<OZXTTkH^~~iGOaWbi~~uM980OOO,   .\n"
            + ".    d####@##diiOMcxxY)~~~~~~~~~~^*~rr~~~vK^~~~hQQOOOZ.  .\n"
            + ".   c####@#Bewrc)~~~~~^}zhc*~~<VH0gZc<~~~^x]|~~~}gDOO6c  .\n"
            + ".  _#####@#MoVjMbKv~~v3wYTky~~*Ti(*v}^~~vO5PRG*~~xg6OO0. .\n"
            + ".  )#####@doIdkx*)^~~~~~~~~~~~~~~~~~~~~~~~~~<})~~~M6OO6* .\n"
            + ".  W#####QooT~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~U6OO6\\ .\n"
            + ".  D#####Uook*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~kEOOOv .\n"
            + ".  a####Boojz*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~VRO6D\" .\n"
            + ".  :####Boojo)~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~k6O6h  .\n"
            + ".   a###goojo}~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~<M6OM-  .\n"
            + ".   `y##8oojojx^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|uD6b_   .\n"
            + ".     hg9joo3RgQDMI}r~~~~~~~~~~~~~~~~~~\\oZMdddzyjo8b.    .\n"
            + ".     :(VooH##QE66O6gc*~~~~~~~~~~~~~~*R#Q966OOO8U3Xv     .\n"
            + ".       ~ajB#B96O9669DEx~~~~~~~~~~~~xQ#BOOO6OOORO)`      .\n"
            + ".        hmB#B6E6g$Egg6$Txr*<^;~~~^\\B##BDgO6O6O$K`       .\n"
            + ".        `\\5##g$8gg8$8$$doojkkwVcyzW##QBgg$8ROOk`        .\n"
            + "xvTyIa5MbZbQ#@#BQ06gQQ8Q9oehP5W3KHHQ#Q$DR0DQD6QMVY)~_    .\n"
            + "##############@@QgB##8R@#Q#########@#QQBBDO8Q########B$5jy\n"
            + "################@@##B6Q@############@#$9#BB###############\n"
            + "Q$D9OOO6E0$$0EOM3szwkV########000$g8QwXWDQB###############\n"
            + ".                     M######86666OOj      -:<rYkG0B######\n"
            + ".                     Y######Q66OOO6r               -!^)vY\n"
            + ".                     'E######86O6RT                     .\n"
            + ".                      `d#######BQ}                      .\n"
            + ".                        !lG0$0GV,                       .";
    private final Scanner sc;

    /**
     * Represents the constructor to create a new Ui object.
     * Scanner is used to read user input and is initialised here.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty();
    }

    /**
     * Returns the user input as a string array split by spaces.
     *
     * @return String array of user input.
     */
    public String[] getUserCommand() {
        showToUser("Meowww... enter command:");
        String fullInputLine = sc.nextLine();
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = sc.nextLine();
        }
        assert fullInputLine != null;
        showToUser("[Meowww you've entered " + fullInputLine + "]");
        return fullInputLine.split(" ", 2);
    }

    /**
     * Method that prints the welcome message.
     */
    public void showWelcomeMessage() {
        showToUser(DASHED_LINE,
                LOGO,
                MESSAGE_WELCOME,
                DASHED_LINE);
    }

    /**
     * Method that prints the goodbye message.
     */
    public String showGoodbyeMessage() {
        showToUser(DASHED_LINE,
                MESSAGE_GOODBYE,
                DASHED_LINE);
        return formatMessage(DASHED_LINE,
                    MESSAGE_GOODBYE,
                    DASHED_LINE);
    }

    /**
     * Method that prints the message to the user with the given prefix.
     * Each windows separator is replaced with a line separator.
     *
     * @param message Message to be printed.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    /**
     * Returns the String of a message after formatting it.
     * Formats the message to be printed to the user with the given prefix.
     * Each windows separator is replaced with a line separator.
     *
     * @param message of Messages to be printed.
     * @return Formatted message as a string.
     */
    public String formatMessage(String... message) {
        String formattedMessage = "";
        for (String m : message) {
            formattedMessage += LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX);
        }
        return formattedMessage;
    }

    /**
     * Returns the String of the task left in a Task List.
     * Prints the list of tasks to the user.
     *
     * @param tl TaskList object containing the list of tasks.
     */
    public String showToUserList(TaskList tl) {
        String toShow;
        if (tl.isEmpty()) {
            toShow = "There does not seem to be any tasks meow, would you care to add some?";
            showToUser(toShow);
        } else {
            toShow = "Here are the tasks in your list:\n";
            for (int i = 0; i < tl.size(); i++) {
                Task t = tl.get(i);
                toShow += String.format("%s. %s", i + 1, t);
                if (i + 1 < tl.size()) {
                    toShow += "\n";
                }
            }
            showToUser(toShow);
        }
        return toShow;
    }

    /**
     * Returns the formatted String of task left.
     * Prints the string of remaining tasks the user has in the list.
     *
     * @param tl TaskList object containing the list of tasks.
     * @return Formatted message as a string.
     */
    public String stringOfTaskNumbers(TaskList tl) {
        String toShow = String.format("Now you have %d tasks in your list", tl.size());
        return toShow;
    }
}
