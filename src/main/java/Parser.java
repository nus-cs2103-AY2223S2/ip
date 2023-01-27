

public class Parser {
    
    Parser() {

    }

    public void readInput(String input, TaskList listOfTasks) throws DukeExceptions {

        if (input.startsWith("todo")) {
            handleToDoTask(input, listOfTasks);
        }

        if (input.startsWith("deadline")) {
            handleDeadlineTask(input, listOfTasks);
        }

        if (input.startsWith("event")) {
            handleEventTask(input, listOfTasks);
        }

        if (input.equals("list")) {
            handleList(listOfTasks);
        }

        if (input.startsWith("delete")) {
            handleDelete(input, listOfTasks);
        }

        if (input.startsWith("checkdue")) {
            handleCheckDue(input, listOfTasks);
        }

        if (input.startsWith("mark")) {
            handleMark(input, listOfTasks);
        }

        if (input.startsWith("unmark")) {
            handleUnmark(input, listOfTasks);
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
