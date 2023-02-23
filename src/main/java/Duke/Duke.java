package Duke;

import Duke.Exception.ProgramException;
import Duke.Tasks.TaskList;
import Duke.Commands.Command;


/**
 * Main driver class for Duke.
 * @author Bryan Juniano
 */

public class Duke{

    private TaskList taskList = new TaskList();
    private Handler handler = new Handler();
    private Saver saver = new Saver();

    public Duke(){
        try {
            saver.load(taskList);
        } catch (ProgramException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getResponse(String input) {
        try {
            Command userCommand = handler.processCommand(input, taskList);
            return userCommand.run(taskList,saver);
        } catch (ProgramException e) {
            return e.getMessage();
        }
    }

}