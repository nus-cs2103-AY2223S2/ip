import java.util.Scanner;

public class UI {
    private static Scanner sc = new Scanner(System.in);
    public UI() {}

    public void welcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);


        String intro = "_____________________________________\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "_____________________________________\n";
        System.out.println(intro);
    }

    public void showLoadingError() {
        System.out.println("Unable to start up program.");
        System.exit(-1);
    }

    public void displayError(DukeException dukeError){
        System.out.println(dukeError.errorMessage);
    }

    public static void end() {
        System.out.println(
                "_____________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "_____________________________________\n"
        );
        sc.close();
        System.exit(0);
    }

    public String getInput(){
        return sc.nextLine();
    }
}
