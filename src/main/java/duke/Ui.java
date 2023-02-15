package duke;

import javafx.application.Platform;

import java.util.Scanner;

public class Ui {
    private final Scanner sc;
    private MainWindow mainWindow;

    /**
     * constructor of the Ui class and initialise scanners sc
     */
    public Ui() {
        sc = new Scanner(System.in);
        mainWindow = null;
    }

    /**
     * greets the user
     */
    public void greet() {
        System.out.println("Hello I'm duke.\nWhat can I do for you?");
        mainWindow.passDukeResponse("Hello I'm duke.\nWhat can I do for you?");
    }

    /**
     * displays the logo
     */
    public void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }


    /**
     * prints out text
     * @param text to be print
     */
    public void printText(String text) {

        System.out.println(text);

        if(mainWindow!=null) {
            mainWindow.passDukeResponse(text);
        }
    }

    /**
     * Set the Mainwindow so that Ui can interact with the GUI
     * @param mainWindow for setting the MainWindow
     */
    public void setMainWindow(MainWindow mainWindow) {
        assert mainWindow != null: "MainWindow not found in Ui";
        this.mainWindow = mainWindow;
    }

    public void exitProgram(){
        Platform.exit();
    }
}

