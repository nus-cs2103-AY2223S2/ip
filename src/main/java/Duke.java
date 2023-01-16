import commands.*;
import exceptions.DukeException;
import tasks.ITask;
import uitilties.UserInterface;

import java.util.ArrayList;

import static uitilties.UserInterface.receptor;

public class Duke {
    static ArrayList<ITask> tasks = new ArrayList<>();

    public static void main(String[] args) {
        UserInterface.greeting();
        boolean done = false;
        while (!done)
            try {
                ICommand cmd = receptor(tasks);
                done = cmd.run();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                UserInterface.Speak("Type any command to continue...");
            }

    }
}
