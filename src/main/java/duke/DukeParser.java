package duke;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import duke.constant.DukeCommand;
import duke.constant.Message;
import duke.exception.IllegalDukeCommandArgumentException;
import duke.exception.InvalidTaskTypeException;
import duke.exception.NoSuchDukeCommandException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class DukeParser {
    private DukeCommand command;

    /* Container for task args */
    private int taskId;
    private boolean isDone;
    private String title;
    private String op1;
    private String op2;

    public DukeParser(DukeCommand command) {
        this.command = command;
        this.isDone = false;
        this.op1 = "";
        this.op2 = "";
    }

    public DukeCommand getCommand() {
        return command;
    }

    public void setCommand(DukeCommand command) {
        this.command = command;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    /**
     * Parse "todo 1" -> DukeParser object
     * 
     * @param input
     * @return
     * @throws NoSuchDukeCommandException
     * @throws IllegalDukeCommandArgumentException
     */
    public static DukeParser parseInput(String input)
            throws NoSuchDukeCommandException, IllegalDukeCommandArgumentException {
        String[] ops = input.toLowerCase().split(" ", 2);
        try {
            DukeCommand dc = DukeCommand.valueOf(ops[0].toUpperCase());
            DukeParser dp = new DukeParser(dc);

            // command without args
            if (dc.equals(DukeCommand.BYE) || dc.equals(DukeCommand.LIST)) {
                return dp;
            }

            String[] args = ops[1].split(" /[a-z]*[^ ] ");

            switch (dc) {
                // commands with special args
                case TODO:
                    if (args.length != 1) {
                        throw new IllegalDukeCommandArgumentException(Message.EXCEPTION_INVALID_TODO_CMD);
                    }
                    dp.setTitle(args[0]);
                    break;
                case DEADLINE:
                    if (args.length != 2) {
                        throw new IllegalDukeCommandArgumentException(Message.EXCEPTION_INVALID_DEADLINE_CMD);
                    }
                    dp.setTitle(args[0]);
                    dp.setOp1(args[1]);
                    break;
                case EVENT:
                    if (args.length != 3) {
                        throw new IllegalDukeCommandArgumentException(Message.EXCEPTION_INVALID_EVENT_CMD);
                    }
                    dp.setTitle(args[0]);
                    dp.setOp1(args[1]);
                    dp.setOp2(args[2]);
                    break;

                // commands with only 1 args
                default:
                    if (ops.length > 2) {
                        throw new IllegalDukeCommandArgumentException(Message.EXCEPTION_NOSUCH_COMMAND);
                    }
                    dp.setTaskId(Integer.parseInt(ops[1]));
            }
            return dp;
        } catch (NumberFormatException e) {
            throw new IllegalDukeCommandArgumentException(Message.EXCEPTION_INVALID_TODO_ID);
        } catch (IllegalArgumentException e) {
            throw new NoSuchDukeCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }
    }

    /**
     * Parse "T,1,..." -> DukeParser object
     * 
     * @param input
     * @return
     * @throws NoSuchDukeCommandException
     */
    public static DukeParser parseCsv(String input) throws NoSuchDukeCommandException {
        String[] ops = input.split(",");
        try {
            DukeParser dp;

            switch (ops[0]) {
                case "T":
                    dp = new DukeParser(DukeCommand.TODO);
                    if (ops.length != 3) {
                        throw new NoSuchDukeCommandException(Message.EXCEPTION_INVALID_TODO_CMD);
                    }
                    dp.setIsDone(ops[1].equals("1"));
                    dp.setTitle(ops[2]);
                    break;
                case "D":
                    dp = new DukeParser(DukeCommand.DEADLINE);
                    if (ops.length != 4) {
                        throw new NoSuchDukeCommandException(Message.EXCEPTION_INVALID_DEADLINE_CMD);
                    }
                    dp.setIsDone(ops[1].equals("1"));
                    dp.setTitle(ops[2]);
                    dp.setOp1(ops[3]);
                    break;
                case "E":
                    dp = new DukeParser(DukeCommand.EVENT);
                    if (ops.length != 5) {
                        throw new NoSuchDukeCommandException(Message.EXCEPTION_INVALID_EVENT_CMD);
                    }
                    dp.setIsDone(ops[1].equals("1"));
                    dp.setTitle(ops[2]);
                    dp.setOp1(ops[3]);
                    dp.setOp2(ops[4]);
                    break;
                default:
                    throw new NoSuchDukeCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
            }

            return dp;
        } catch (IllegalArgumentException e) {
            throw new NoSuchDukeCommandException(Message.EXCEPTION_NOSUCH_COMMAND);
        }
    }

    /**
     * Converts DukeParser to the corresponding task object.
     * 
     * @param dp DukeParser
     * @return Task object
     * @throws InvalidTaskTypeException
     */
    public static Task toTask(DukeParser dp) throws InvalidTaskTypeException {
        switch (dp.getCommand()) {
            case TODO:
                return new Todo(dp.getTitle(), dp.getIsDone());
            case DEADLINE:
                return new Deadline(dp.getTitle(), dp.getIsDone(), dp.getOp1());
            case EVENT:
                return new Event(dp.getTitle(), dp.getIsDone(), dp.getOp1(), dp.getOp2());
            default:
                throw new InvalidTaskTypeException(Message.EXCEPTION_INVALID_TASK_TYPE);
        }
    }

}
