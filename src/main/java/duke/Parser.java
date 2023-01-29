package duke;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.ToDo;
import duke.tasktypes.Task;

public class Parser {
    
    public Parser() {

    }

    public void readInput(String input, TaskList listOfTasks) throws DukeExceptions {
        
        try {

            if (input.startsWith("todo")) {
                handleToDoTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("find")) {
                handleFindTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("deadline")) {
                handleDeadlineTask(input, listOfTasks);
                return;
            }

            if (input.startsWith("event")) {
                handleEventTask(input, listOfTasks);
                return;
            }

            if (input.equals("list")) {
                handleList(listOfTasks);
                return;
            }

            if (input.startsWith("delete")) {
                handleDelete(input, listOfTasks);
                return;
            }

            if (input.startsWith("checkdue")) {
                handleCheckDue(input, listOfTasks);
                return;
            }

            if (input.startsWith("mark")) {
                handleMark(input, listOfTasks);
                return;
            }

            if (input.startsWith("unmark")) {
                handleUnmark(input, listOfTasks);
                return;
            }

            throw new DukeExceptions("");
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleFindTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] checkForKeywordArr = input.split(" ");
            if (checkForKeywordArr.length < 2) {
                throw new DukeExceptions("find");
            }
            String keyword = input.substring(4);
            listOfTasks.findTasks(keyword);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleToDoTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(4);
            Task toAdd = new ToDo(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleDeadlineTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(8);
            Task toAdd = new Deadlines(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleEventTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String useForInit = input.substring(5);
            Task toAdd = new Events(useForInit);
            listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleList(TaskList listOfTasks) {
        listOfTasks.toRead();
    }

    public void handleDelete(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions(" ");
            }
            listOfTasks.deleteTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleCheckDue(String input, TaskList listOfTasks) {
        String[] commandSplit = input.split(" ");
        listOfTasks.checkDueDate(Integer.parseInt(commandSplit[1]));
    }
    
    public void handleMark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            listOfTasks.markTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    public void handleUnmark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            listOfTasks.unmarkTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }
}
