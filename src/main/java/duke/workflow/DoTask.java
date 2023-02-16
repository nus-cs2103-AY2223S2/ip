package duke.workflow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import duke.io.input.exception.DukeException;
import duke.io.input.exception.UserInputException;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;
import duke.util.service.Deadline;
import duke.util.service.ScheduledEvent;
import duke.util.service.ToDo;


/**
 * A more specific implementation of {@code Event}.
 *
 * Part of the workflow where the chatbot determines the next
 * possible action based on the user's input
 */

public class DoTask extends Event {
    private boolean firstGreet;
    private String lastCommand;
    private TaskList taskList;
    private Task removedTask;
    private Storage storage;
    private TaskList foundList;

    /**
     * Constructs the {@code DoTask} as the user interacts
     * with Duke.
     */
    public DoTask() {
        super(false);
        this.firstGreet = true;
        this.lastCommand = "";
        this.taskList = new TaskList();
        this.storage = new Storage();
        this.foundList = new TaskList();
    }

    /**
     * Determines the next possible action after the user has entered an input.
     *
     * @return a new event that follows from the last user input
     */

    public Event toNext() {
        Scanner sc = new Scanner(System.in);
        String nextTask = sc.nextLine();
        if (nextTask.equals("BYE")) {
            return new Ending(this.taskList);
        } else {
            try {
                UserInputException.checkUserInput(nextTask, this.taskList.getSize());
                if (nextTask.equals("LIST")) {
                    this.firstGreet = false;
                    this.lastCommand = nextTask;
                    return this;
                } else {
                    String[] command = nextTask.split(" ");
                    List<String> words = Arrays.asList(command);
                    if (words.get(0).equals("MARK")) {
                        this.firstGreet = false;
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.markDone(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("UNMARK")) {
                        this.firstGreet = false;
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.unMark(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("TODO")) {
                        String[] toDoTask = nextTask.split("TODO ");
                        List<String> toDoAction = Arrays.asList(toDoTask);
                        this.firstGreet = false;
                        this.lastCommand = toDoAction.get(1);
                        ToDo newTask = new ToDo(toDoAction.get(1));
                        this.taskList = this.taskList.addTask(newTask);
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        return this;
                    }
                    if (words.get(0).equals("DEADLINE")) {
                        String[] toDoTask = nextTask.split(" /BY ");
                        List<String> deadlineList = Arrays.asList(toDoTask);
                        String deadlineAction = deadlineList.get(0);
                        String[] deadlinePhraseArray = deadlineAction.split("DEADLINE ");
                        List<String> deadlinePhraseList = Arrays.asList(deadlinePhraseArray);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        Deadline newTask = new Deadline(
                                LocalDateTime.parse(deadlineList.get(1), format),
                                deadlinePhraseList.get(1));
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        this.firstGreet = false;
                        this.lastCommand = deadlinePhraseList.get(1);
                        this.taskList = this.taskList.addTask(newTask);
                        return this;
                    }
                    if (words.get(0).equals("EVENT")) {
                        String[] splitFrom = nextTask.split(" /FROM ");
                        List<String> eventActionSplit = Arrays.asList(splitFrom);
                        String timeLinePhrase = eventActionSplit.get(1);
                        String eventAction = eventActionSplit.get(0);
                        String[] eventPhraseArray = eventAction.split("EVENT ");
                        List<String> eventPhraseList = Arrays.asList(eventPhraseArray);
                        String[] timeFrame = timeLinePhrase.split(" /TO ");
                        List<String> timeLineSplit = Arrays.asList(timeFrame);
                        String eventBegin = timeLineSplit.get(0);
                        String eventEnd = timeLineSplit.get(1);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        ScheduledEvent newTask = new ScheduledEvent(
                                LocalDateTime.parse(eventBegin, format),
                                LocalDateTime.parse(eventEnd, format),
                                eventPhraseList.get(1));
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        this.firstGreet = false;
                        this.lastCommand = eventPhraseList.get(1);
                        this.taskList = this.taskList.addTask(newTask);
                        return this;
                    }
                    if (words.get(0).equals("DELETE")) {
                        this.firstGreet = false;
                        this.removedTask = this.taskList.getTask(Integer.valueOf(words.get(1)) - 1);
                        String[] splitRemovedTask = this.removedTask.toString().split(" ");
                        List<String> removedTaskArray = Arrays.asList(splitRemovedTask);
                        for (String i : removedTaskArray) {
                            this.storage = this.storage.removeFromStorage(i, this.removedTask);
                        }
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.removeTask(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("FIND")) {
                        String[] toFind = nextTask.split("FIND ");
                        List<String> keywords = Arrays.asList(toFind);
                        TaskList findList = this.storage.getTaskList(keywords.get(1));
                        this.firstGreet = false;
                        this.foundList = findList;
                        this.lastCommand = nextTask;
                        return this;
                    }
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            } catch (Exception exception) {
                System.out.println("ERRRR ERROR ERRR. SYSTEM FAILURE. UNKNOWN EXCEPTION. ERR ERR");
            }
        }
        return this;
    }

    public Event toNextGui(String nextTask) {
        if (nextTask.equals("BYE")) {
            return new Ending(this.taskList);
        } else {
            try {
                UserInputException.checkUserInput(nextTask, this.taskList.getSize());
                if (nextTask.equals("LIST")) {
                    this.firstGreet = false;
                    this.lastCommand = nextTask;
                    return this;
                } else {
                    String[] command = nextTask.split(" ");
                    List<String> words = Arrays.asList(command);
                    if (words.get(0).equals("MARK")) {
                        this.firstGreet = false;
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.markDone(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("UNMARK")) {
                        this.firstGreet = false;
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.unMark(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("TODO")) {
                        String[] toDoTask = nextTask.split("TODO ");
                        List<String> toDoAction = Arrays.asList(toDoTask);
                        this.firstGreet = false;
                        this.lastCommand = toDoAction.get(1);
                        ToDo newTask = new ToDo(toDoAction.get(1));
                        this.taskList = this.taskList.addTask(newTask);
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        return this;
                    }
                    if (words.get(0).equals("DEADLINE")) {
                        String[] toDoTask = nextTask.split(" /BY ");
                        List<String> deadlineList = Arrays.asList(toDoTask);
                        String deadlineAction = deadlineList.get(0);
                        String[] deadlinePhraseArray = deadlineAction.split("DEADLINE ");
                        List<String> deadlinePhraseList = Arrays.asList(deadlinePhraseArray);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        Deadline newTask = new Deadline(
                                LocalDateTime.parse(deadlineList.get(1), format),
                                deadlinePhraseList.get(1));
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        this.firstGreet = false;
                        this.lastCommand = deadlinePhraseList.get(1);
                        this.taskList = this.taskList.addTask(newTask);
                        return this;
                    }
                    if (words.get(0).equals("EVENT")) {
                        String[] splitFrom = nextTask.split(" /FROM ");
                        List<String> eventActionSplit = Arrays.asList(splitFrom);
                        String timeLinePhrase = eventActionSplit.get(1);
                        String eventAction = eventActionSplit.get(0);
                        String[] eventPhraseArray = eventAction.split("EVENT ");
                        List<String> eventPhraseList = Arrays.asList(eventPhraseArray);
                        String[] timeFrame = timeLinePhrase.split(" /TO ");
                        List<String> timeLineSplit = Arrays.asList(timeFrame);
                        String eventBegin = timeLineSplit.get(0);
                        String eventEnd = timeLineSplit.get(1);
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
                        ScheduledEvent newTask = new ScheduledEvent(
                                LocalDateTime.parse(eventBegin, format),
                                LocalDateTime.parse(eventEnd, format),
                                eventPhraseList.get(1));
                        for (String i : words) {
                            this.storage = this.storage.addToStorage(i, newTask);
                        }
                        this.firstGreet = false;
                        this.lastCommand = eventPhraseList.get(1);
                        this.taskList = this.taskList.addTask(newTask);
                        return this;
                    }
                    if (words.get(0).equals("DELETE")) {
                        this.firstGreet = false;
                        this.removedTask = this.taskList.getTask(Integer.valueOf(words.get(1)) - 1);
                        String[] splitRemovedTask = this.removedTask.toString().split(" ");
                        List<String> removedTaskArray = Arrays.asList(splitRemovedTask);
                        for (String i : removedTaskArray) {
                            this.storage = this.storage.removeFromStorage(i, this.removedTask);
                        }
                        this.lastCommand = nextTask;
                        this.taskList = this.taskList.removeTask(Integer.valueOf(words.get(1)) - 1);
                        return this;
                    }
                    if (words.get(0).equals("FIND")) {
                        String[] toFind = nextTask.split("FIND ");
                        List<String> keywords = Arrays.asList(toFind);
                        TaskList findList = this.storage.getTaskList(keywords.get(1));
                        this.firstGreet = false;
                        this.foundList = findList;
                        this.lastCommand = nextTask;
                        return this;
                    }
                }
            } catch (DukeException exception) {
                System.out.println(exception);
            } catch (Exception exception) {
                System.out.println("ERRRR ERROR ERRR. SYSTEM FAILURE. UNKNOWN EXCEPTION. ERR ERR");
            }
        }
        return this;
    }






    public TaskList getTaskList() {
        return this.taskList;
    }

    @Override
    public String toString() {
        String toPrintOut = "";
        if (this.firstGreet) {
            toPrintOut += "INTERESTING. VERY INTERESTING. WHAT'S YOUR PLANS?";
        } else {
            if (lastCommand.equals("LIST")) {
                toPrintOut += this.taskList.toString();
            } else {
                String[] command = lastCommand.split(" ");
                List<String> words = Arrays.asList(command);
                if (words.get(0).equals("MARK")) {
                    toPrintOut += "MARKED. ONE STEP CLOSER..." + '\n';
                    toPrintOut += this.taskList.getTask(Integer.valueOf(words.get(1)) - 1).toString() + '\n';
                } else if (words.get(0).equals("UNMARK")) {
                    toPrintOut += "HAVING OTHER PLANS I SEE..." + '\n';
                    toPrintOut += this.taskList.getTask(Integer.valueOf(words.get(1)) - 1).toString() + '\n';
                } else if (words.get(0).equals("DELETE")) {
                    toPrintOut += "KABOOM. GONE. REDUCED TO ATOMS. HOW EXCITING!" + '\n';
                    toPrintOut += '\n' + "TASK " + this.removedTask.toString() + " NO LONGER EXISTED" + '\n';
                    int numberOfTasks = this.taskList.getSize();
                    if (numberOfTasks > 1) {
                        toPrintOut += numberOfTasks + " TASKS LEFT. BETTER HURRY." + '\n';
                    } else {
                        toPrintOut += "ONLY " + numberOfTasks + " TASK LEFT. BORING DAYS AHEAD." + '\n';
                    }
                } else if (words.get(0).equals("FIND")) {
                    toPrintOut += "YOU'RE STARTING TO FORGET..." + '\n';
                    if (this.foundList.getSize() == 0) {
                        toPrintOut += "THIS WAS NEVER IN YOUR PLANS";
                    } else {
                        toPrintOut += '\n' + "LET ME REMIND YOU OF WHAT YOU STARTED" + '\n';
                        for (int i = 0; i < this.foundList.getSize(); i++) {
                            toPrintOut += this.foundList.getTask(i).toString() + '\n';
                        }
                    }
                } else {
                    toPrintOut += "SO YOU WANT TO ADD " + '"' + this.lastCommand + '"' + ". VERY WELL..." + '\n';
                    toPrintOut += '\n' + "ADDED: " + lastCommand + '\n';
                    int numberOfTasks = this.taskList.getSize();
                    if (numberOfTasks > 1) {
                        toPrintOut += numberOfTasks + " TASKS. BETTER HURRY." + '\n';
                    } else {
                        toPrintOut += "ONLY " + numberOfTasks + " TASK. BORING DAYS AHEAD." + '\n';
                    }
                    toPrintOut += '\n' + "WHAT ELSE?";
                }
            }
        }
        return toPrintOut;
    }
}
