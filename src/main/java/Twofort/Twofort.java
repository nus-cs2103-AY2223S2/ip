package Twofort;

import Twofort.Exception.ProgramException;
import Twofort.Tasks.TaskList;
import Twofort.Commands.Command;


/**
 * Main driver class for Twofort
 * @author Bryan Juniano
 */

public class Twofort {

    private TaskList taskList = new TaskList();
    private Handler handler = new Handler();
    private Saver saver = new Saver();

    public Twofort() {
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