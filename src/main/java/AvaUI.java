import Exceptions.AvaException;

import java.util.Locale;
import java.util.Scanner;

public class AvaUI {
    //ASCII Emoticons
    private final String AVA_EYES_OPEN = "(｡◕‿◕｡)";
    private final String AVA_EYES_CLOSE = "( ^_^)";
    private final String BOUNDARY = "﹋";
    private final String FORMAT_SPACE = "         ";
    //Boundary Constants
    private int BNUM = 50;
    //Introduction Constant
    private final String INTRO_MESSAGE = "Heya! I am Ava.";
    private final String GREET = "Nice to meet you!!";
    private final String ASK_MESSAGE = "How can I brighten you're day ?";
    private final String LIST_MESSAGE = "You've got a busy day ahead:";
    private final String ADD_TASK_MESSAGE = "Added this task for you:";
    private final String MARK_TASK_MESSAGE = "Yayy! One more task done:";
    private final String UNMARK_TASK_MESSAGE = "Ahh! More work to be done on this task:";
    private final String EXIT_MESSAGE = "Have a nice day !!";

    //App Variables
    private TaskList tasks = new TaskList();

    /**
     * Calls the Intialisation function
     */
    public AvaUI(){
        this.intialise();
    }

    /**
     * Intialises the UI
     */
    public void intialise() {
        this.displayIntro();
        boolean done = false;
        Scanner myObj = new Scanner(System.in);

        while (!done) {
            try {
                this.ask();
                String input = myObj.nextLine().toLowerCase();
                if (input.contains("bye")){
                    done = true;
                    this.displayExit();
                } else if(input.contains("list")) {
                    String output = this.LIST_MESSAGE + "\n" + this.tasks.formatTasks(FORMAT_SPACE);
                    this.displayOutput(output);
                } else if (input.contains("unmark")){
                    String output = this.UNMARK_TASK_MESSAGE + "\n" + this.tasks.toggleTask(input, FORMAT_SPACE);
                    this.displayOutput(output);
                } else if (input.contains("mark")) {
                    String output = this.MARK_TASK_MESSAGE + "\n" + this.tasks.toggleTask(input, FORMAT_SPACE);
                    this.displayOutput(output);
                }  else {
                    String output = this.ADD_TASK_MESSAGE + "\n" + this.tasks.addTasks(input, FORMAT_SPACE);
                    this.displayOutput(output);
                }
            } catch (AvaException e){
                this.displayOutput(e.getMessage());
            }
        }
    }

    /**
     * Prints the Intro message
     */
    private void displayIntro(){
            this.displayBoundary();
            this.printOutput(INTRO_MESSAGE);
            this.printOutput(GREET);
            this.printOutput(ASK_MESSAGE);
            this.displayBoundary();
        }

    /**
     * Prints the exit message.
     */
    private void displayExit() {
        this.displayBoundary();
        this.printOutput(EXIT_MESSAGE);
        this.displayBoundary();
    }

    /**
     * Prints a Boilerplate to ask user the command.
     */
    private void ask() {
        System.out.print("\n"+ this.AVA_EYES_OPEN + ": ");
    }

    /**
     * Displays Output with boundaries
     */
    private void displayOutput(String output) {
        this.displayBoundary();
        printOutput(output);
        this.displayBoundary();
    }

    /**
     * PRINTING MESSAGE WITH A SPECIAL EMOTICON
     */
    private void printOutput(String emote, String message ) {
        System.out.println(emote + message);
    }

    /**
     * Default Way to print a message with AVA_EYES_CLOSE EMOTICON
     */
    private void printOutput(String message) {
        System.out.println(this.AVA_EYES_CLOSE +" "+message);
    }

    /**
     * Displays Boundary According to Constant BOUNDARY and the number is determined by BNUM
     */
    private void displayBoundary() {
        String res = "\n";
        for (int i = 0; i < BNUM ; i++) {
            res += this.BOUNDARY;
        }
        System.out.println(res);
    }



}
