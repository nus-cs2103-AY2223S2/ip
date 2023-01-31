package duke;
import duke.dukeexceptions.DukeExceptions;

public class Ui {

    public void showWelcome() {
        String introduction = "  ________________________________\n"
                + "  Hello! I'm Duke\n"
                + "  What can I do for you?\n"
                + "  ________________________________\n";
        System.out.println(introduction);
    }

    public void outro() {
        String bye = "  Bye! have a great day\n";
            System.out.println(bye);
    }

    public void showError(DukeExceptions exceptions) {
        String reply = exceptions.toString();
        System.out.println(reply);
    }


    public void showLine(){
        System.out.println("  ________________________________");
    }
}
