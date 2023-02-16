package duke;

import duke.tasktypes.Deadlines;
import duke.tasktypes.Events;
import duke.tasktypes.Task;
import duke.tasktypes.ToDo;

/**
 * Class used to interpret the user's input for Duke chatbot.
 */
public class Parser {
    private String outputToReturn;

    public Parser() {}

    /**
     * Function to redirect user's input to the relevant functions.
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @return String representation of output.
     * @throws DukeExceptions if the user inputs an invalid command.
     */
    public String readInput(String input, TaskList listOfTasks, TagList listOfTags) throws DukeExceptions {
        try {

            if (input.startsWith("findtag")) {
                handleFindTag(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("tag")) {
                handleTag(input, listOfTasks, listOfTags);
                return this.outputToReturn;
            }

            if (input.startsWith("todo")) {
                handleToDoTask(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("find")) {
                handleFindTask(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("deadline")) {
                handleDeadlineTask(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("event")) {
                handleEventTask(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.equals("list")) {
                handleList(listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("delete")) {
                handleDelete(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("checkdue")) {
                handleCheckDue(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("mark")) {
                handleMark(input, listOfTasks);
                return this.outputToReturn;
            }

            if (input.startsWith("unmark")) {
                handleUnmark(input, listOfTasks);
                return this.outputToReturn;
            }

            throw new DukeExceptions("");
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
            return this.outputToReturn;
        }
    }

    /**
     * Function to find tasks which match the given tag.
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks to check for matching tag.
     */
    public void handleFindTag(String input, TaskList listOfTasks) {
        String tagName = input.split(" ")[1];
        String toSet = "Here are the tasks matching the tag ["
                + tagName + "]:\n";
        Integer indexToPrint = 1;
        for (int i = 0; i < listOfTasks.getListOfTasks().size(); i++) {
            Task currTask = listOfTasks.getListOfTasks().get(i);
            if (tagName.equals(currTask.getTag())) {
                String appendThis = indexToPrint.toString() + "." + currTask.toString()
                        + " " + currTask.getTag() + "\n";
                toSet += appendThis;
                indexToPrint++;
            }
        }
        if (indexToPrint == 1) {
            toSet = "Hey, it appears there are no tasks that match such a tag!";
        }
        this.outputToReturn = toSet;
    }

    /**
     * Function to help handle tagging command
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks used by Duke chatbot.
     * @param listOfTags The list of tags used by Duke chatbot associated with tasks.
     */
    public void handleTag(String input, TaskList listOfTasks, TagList listOfTags) {
        try {
            String[] tagDetailsInArr = input.split(" ");
            if (tagDetailsInArr.length < 3) {
                throw new DukeExceptions("bad tag");
            }
            int indexToUse = Integer.parseInt(tagDetailsInArr[1]) - 1;
            String tagName = tagDetailsInArr[2];
            if (indexToUse >= listOfTags.getTagListFull().size()) {
                throw new DukeExceptions("bad tag");
            }
            listOfTasks.getListOfTasks().get(indexToUse).setTag(tagName);
            String toOutput = listOfTags.setTag(indexToUse, tagName);
            this.outputToReturn = toOutput;
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to handle find command from the user input
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if the keyword given is invalid.
     */
    public void handleFindTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] checkForKeywordArr = input.split(" ");
            if (checkForKeywordArr.length < 2) {
                throw new DukeExceptions("find");
            }
            String keyword = input.substring(4);
            this.outputToReturn = listOfTasks.findTasks(keyword);
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to handle ToDo tasks from the user input.
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if name of the task is not properly given by user.
     */
    public void handleToDoTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String todoDescription = input.substring(4);
            Task toAdd = new ToDo(todoDescription);
            this.outputToReturn = listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to handle Deadlines tasks from the user's input.
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if date of task is wrongly given or if name of task is not properly given by user.
     */
    public void handleDeadlineTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String deadlineDescription = input.substring(8);
            Task toAdd = new Deadlines(deadlineDescription);
            this.outputToReturn = listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to handle Events tasks from the user's input.
     *
     * @param input String representation of the user's input.
     * @param listOfTasks The list of tasks which has been loaded into the Duke chatbot instance.
     * @throws DukeExceptions if event details (from and to dates) are not properly given by user.
     */
    public void handleEventTask(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String eventDescription = input.substring(5);
            Task toAdd = new Events(eventDescription);
            this.outputToReturn = listOfTasks.addTask(toAdd);
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to redirect listing out the list of tasks when user input is "list".
     *
     * @param listOfTasks The arraylist containing the tasks from Duke chatbot.
     */
    public void handleList(TaskList listOfTasks) {
        this.outputToReturn = listOfTasks.toRead();
    }

    /**
     * Function to handle deleting tasks from user input.
     *
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index indicated in the input string is invalid (<= 0 or greater than list size)
     */
    public void handleDelete(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions(" ");
            }
            this.outputToReturn = listOfTasks.deleteTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to redirect checking task's due date when user input is "checkdue"
     *
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     */
    public void handleCheckDue(String input, TaskList listOfTasks) {
        String[] commandSplit = input.split(" ");
        this.outputToReturn = listOfTasks.checkDueDate(Integer.parseInt(commandSplit[1]));
    }

    /**
     * Function to redirect marking task as done.
     *
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index in the input is invalid (<= 0 or greater than list size)
     */
    public void handleMark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            this.outputToReturn = listOfTasks.markTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }

    /**
     * Function to redirect marking task as not done.
     *
     * @param input String representation of user input.
     * @param listOfTasks The list of tasks which has been loaded from the Duke chatbot instance.
     * @throws DukeExceptions if the index in the input is invalid (<= 0 or greater than list size)
     */
    public void handleUnmark(String input, TaskList listOfTasks) throws DukeExceptions {
        try {
            String[] commandSplit = input.split(" ");
            if (commandSplit.length <= 1) {
                throw new DukeExceptions("");
            }
            this.outputToReturn = listOfTasks.unmarkTask(Integer.parseInt(commandSplit[1]));
        } catch (DukeExceptions DE) {
            this.outputToReturn = DE.toString();
        }
    }
}
