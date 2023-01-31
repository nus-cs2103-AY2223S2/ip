package duke;

import java.util.ArrayList;

public class Command {
    private String userInput;
    private boolean isExit;

    public Command(String userInput) {
        this.userInput = userInput;
        isExit = false;
    }

    public void execute(TaskList tasks, Storage storage, Ui ui) {
        String userInput = this.userInput;
        String userCommand = Parser.getCommand(userInput);
        try {
            switch (userCommand) {
            case "bye":
                ui.sayGoodbye();
                isExit = true;
                break;
            case "list":
                ui.printTasks(tasks);
                break;
            case "delete":
                int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
                ui.informDeletion(tasks.getTask(taskIndex), tasks.getSize());
                tasks.deleteTask(taskIndex);
                Storage.saveTasksToTaskLog(tasks);
                break;
            case "mark":
                int toMark = userInput.charAt(5) - 48;
                Task toMarkTask = tasks.getTask(toMark - 1);
                toMarkTask.markTask();
                ui.informTaskIsMarked(toMarkTask);
                Storage.saveTasksToTaskLog(tasks);
                break;
            case "unmark":
                int toUnMark = userInput.charAt(7) - 48;
                Task toUnMarkTask = tasks.getTask(toUnMark - 1);
                toUnMarkTask.unmarkTask();
                ui.informTaskIsUnMarked(toUnMarkTask);
                Storage.saveTasksToTaskLog(tasks);
                break;
            case "find":
                String keyword = Parser.getFindKeyword(userInput);
                ArrayList<Task> foundTasks = tasks.filterTasks(keyword);
                ui.printFoundTasks(foundTasks);
                break;
            case "todo":
            case "deadline":
            case "event":
                Task newTask = Parser.translateUserInputToTask(userInput);
                tasks.addTask(newTask);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n\n");
            }
        } catch (DukeException e) {
            ui.showError(e);
        }
    }

    public boolean getExitStatus() {
        return isExit;
    }
}
