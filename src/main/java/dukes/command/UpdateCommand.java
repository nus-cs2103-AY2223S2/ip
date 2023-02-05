package dukes.command;

import dukes.util.*;

import dukes.task.Task;

import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UpdateCommand extends Command {
    protected String newContent;


    public UpdateCommand(boolean isExit, boolean isValid,
                       String header, String body, String newContent) {
        super(isExit, isValid, header, body);
        this.newContent = newContent;
    }


    public String runCommand(TaskList tasks, UI ui, Storage storage) throws DukeException {
        List<Task> taskList = tasks.getTaskList();
        int index = Integer.parseInt(body);
        try {
            Task theTask = taskList.get(index - 1);
            Parser parser = new Parser();
            // Currently it means if you want to update a command,
            // you have to update EVERYTHING of its content
            // update 5 taskName
            // update 5 taskName /by time
            // update 5 taskName /from time1 /to time2
            // For a deadline, can update name only or name and time

            switch (theTask.getTag()) {
                case "T":
                    // treat all content as taskName
                    theTask.setTaskName(this.newContent);
                    storage.save(tasks);
                    return ui.returnUpdate(theTask, tasks);
                case "D":
                    String[] splited = this.newContent.split(" ");
                    int splitTime = parser.findSplitter(splited, "/by");
                    if (splitTime == -1) {
                        // treat everything as new taskName
                        theTask.setTaskName(this.newContent);
                    } else {
                        String taskName = parser.parseHelper(splited, 0, splitTime);
                        String deadline = parser.parseHelper(splited, splitTime + 1, splited.length);
                        theTask.setTaskName(taskName);
                        try {
                            LocalDate theDate = parser.validateTime(deadline);
                            theTask.setDeadLine(theDate);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("Please enter date in the format dd/mm/yyyy");
                        }

                    }
                    storage.save(tasks);
                    return ui.returnUpdate(theTask, tasks);
                case "E":
                    splited = this.newContent.split(" ");
                    int splitStart = parser.findSplitter(splited, "/from");
                    int splitEnd = parser.findSplitter(splited, "/to");
                    if (splitStart == -1 && splitEnd == -1) {
                        theTask.setTaskName(this.newContent);
                    } else if (splitStart != -1 && splitEnd != -1) {
                        int fromIndex = parser.findSplitter(splited, "/from");
                        int toIndex = parser.findSplitter(splited, "/to");
                        String taskName = parser.parseHelper(splited, 0, fromIndex);
                        String startTime = parser.parseHelper(splited, fromIndex + 1, toIndex);
                        String endTime = parser.parseHelper(splited, toIndex + 1, splited.length);

                        theTask.setTaskName(taskName);
                        try {
                            LocalDate startDate = parser.validateTime(startTime);
                            LocalDate endDate = parser.validateTime(endTime);

                            theTask.setStart(startDate);
                            theTask.setEnd(endDate);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("Please enter date in the format dd/mm/yyyy");
                        }
                    } else if (splitStart != -1) {
                        int fromIndex = parser.findSplitter(splited, "/from");
                        String taskName = parser.parseHelper(splited, 0, fromIndex);
                        String startTime = parser.parseHelper(splited, fromIndex + 1, splited.length);

                        theTask.setTaskName(taskName);
                        try {
                            LocalDate startDate = parser.validateTime(startTime);
                            theTask.setStart(startDate);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("Please enter date in the format dd/mm/yyyy");
                        }
                    } else {
                        int toIndex = parser.findSplitter(splited, "/to");
                        String taskName = parser.parseHelper(splited, 0, toIndex);
                        String endTime = parser.parseHelper(splited, toIndex + 1, splited.length);

                        theTask.setTaskName(taskName); 
                        try {
                            LocalDate endDate = parser.validateTime(endTime);
                            theTask.setEnd(endDate);
                        } catch (DateTimeParseException ex) {
                            throw new DukeException("Please enter date in the format dd/mm/yyyy");
                        }
                    }
                    storage.save(tasks);
                    return ui.returnUpdate(theTask, tasks);
                default:
                    throw new DukeException("Task tag not recognised");
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new DukeException("You have entered an invalid index.");
        }
    }
}
