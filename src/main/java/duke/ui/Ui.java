package duke.ui;

import java.util.Scanner;


/**
 * Class of UI which gives the user a friendly interface.
 */
public class Ui {
    /**
     * This method is the welcome display when the chatbot first starts.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("I am Duke the Chatbot!\nHow may i help you today?\n");
    }

    /**
     * This method indents a dotted straight line.
     */
    public void showLine() {
        System.out.println("--------------------------------------------------------------------");
    }

    /**
     * This method reads the next input line by the user.
     *
     * @return String - Returns the command of the next line.
     */
    public String readCommand(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * This method shows the loading error based on the string input error.
     *
     * @param e - Message to be display as an error.
     */
    public void showLoadingError(String e) {
        System.out.println(e);
    }
}
