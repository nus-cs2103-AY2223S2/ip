package duke;
public class Parser {
    public Parser() {
        
    }

    public boolean checkForExit(String s) {
        return s.equalsIgnoreCase("bye");
    }

    //TODO: Abstract out printing to UI elements
    public void executeCommand(String inputCommand, TaskList tasks, Storage storage, Ui ui) throws DukeException{
        int selectedNum;
        String[] inputCmdArr = inputCommand.split(" ");
        switch(inputCmdArr[0]) {
            case "list":
                tasks.printTask();
                break;
            case "todo":
                String todoDesc;
                try {
                    todoDesc = inputCommand.split(" ", 2)[1];
                } catch (Exception e) {
                    throw new DukeException("Description of todo cannot be empty!!");
                }
                tasks.addTask(todoDesc, false);
                tasks.printNewestTask();
                storage.save(tasks);
                break;
            case "deadline":
                String deadlineInput;
                try {
                    deadlineInput = inputCommand.split(" ", 2)[1];
                } catch (Exception e) {
                    throw new DukeException("Description of deadline cannot be empty!!");
                }
                String[] deadlineDesc = deadlineInput.split(" /by ");
                tasks.addTask(deadlineDesc[0], deadlineDesc[1], false);
                tasks.printNewestTask();
                storage.save(tasks);
                break;
            case "event":
                String eventInput;
                try {
                    eventInput = inputCommand.split(" ", 2)[1];
                } catch (Exception e) {
                    throw new DukeException("Description of event cannot be empty!!");
                }
                String[] eventDescArr = eventInput.split(" /from ");
                String eventDesc = eventDescArr[0];
                String[] eventTimeArr = eventDescArr[1].split(" /to ");
                String eventFrom = eventTimeArr[0];
                String eventTo = eventTimeArr[1];
                tasks.addTask(eventDesc, eventFrom, eventTo, false);
                tasks.printNewestTask();
                storage.save(tasks);
                break;
            case "mark":
                selectedNum = Integer.parseInt(inputCmdArr[1]);
                tasks.markTask(selectedNum);
                storage.save(tasks);
                break;
            case "unmark":
                selectedNum = Integer.parseInt(inputCmdArr[1]);
                tasks.unMarkTask(selectedNum);
                storage.save(tasks);
                break;
            case "delete":
                int numToDelete;
                try {
                    numToDelete = Integer.parseInt(inputCommand.split(" ", 2)[1]);
                } catch (Exception e) {
                    throw new DukeException("Please enter a valid number to delete!");
                }
                tasks.deleteTask(numToDelete);
                storage.save(tasks);
                break;
            case "search":
                String searchStr;
                try {
                    searchStr = inputCommand.split(" ", 2)[1];
                } catch (Exception e) {
                    throw new DukeException("Description of todo cannot be empty!!");
                }
                tasks.searchTask(searchStr);
                break;
            case "Storage":
                //System.out.println("I RAN HERE!");
                tasks = storage.load();
                break;
            case "Save":
                storage.save(tasks);
                break;
            case "bye":
                break;
            default:
                throw new DukeException("I don't get it!");
        }
    }
}