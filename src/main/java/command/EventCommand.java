package command;

import duke.Ui;

import task.Event;
import task.TaskList;

/**
 * This class will carry the implementation of the Event Command
 * Takes in the command line command,parses as well as executing it.
 *
 * @author Bryan Ong
 *
 */
public class EventCommand {
    private String[] inputs;
    private static StringBuilder strBuild = new StringBuilder();

    public EventCommand(String[] inputs) {
        this.inputs = inputs;
    }

    /**
     * This method is used to create the Event command.
     * Parsing as well as creation of Event object is done here.
     *
     */
    public void create() {
        boolean isName = true;
        boolean isStart = false;
        boolean isEnd = false;
        String name = " ", start = " ", end = " ";
        for (int i = 1; i < inputs.length; i++) {
            if (isName) {
                if (!inputs[i + 1].equalsIgnoreCase("/from")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    isStart = true;
                    strBuild.append(inputs[i]);
                    name = strBuild.toString();
                    strBuild.setLength(0);
                    isName = false;
                    i++;
                }
            } else if (isStart) {
                if (!inputs[i + 1].equalsIgnoreCase("/to")) {
                    strBuild.append(inputs[i]);
                    strBuild.append(" ");
                } else {
                    isEnd = true;
                    strBuild.append(inputs[i]);
                    start = strBuild.toString();
                    strBuild.setLength(0);
                    isStart = false;
                    i++;
                }
            } else if (isEnd) {
                strBuild.append(inputs[i]);
                if (i + 1 != inputs.length) {
                    strBuild.append(" ");
                }
            }
        }
        end = strBuild.toString();
        strBuild.setLength(0);
        Event event = new Event(name, start, end, false);
        TaskList.addToList(event);
        Ui.printDefault(event);
    }
}
