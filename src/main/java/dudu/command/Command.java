package dudu.command;

import dudu.exception.DuduException;
import dudu.task.TaskList;
import dudu.util.Storage;

public abstract class Command {
    public enum Instruction {
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        LIST("list"),
        DELETE("delete"),
        MARK("mark"),
        UNMARK("unmark"),
        BYE("bye"),
        FIND("find");

        private final String instruction;

        Instruction(String instruction) {
            this.instruction = instruction;
        }

        public boolean equals(String type) {
            return instruction.equals(type);
        }
    }
    private Instruction instruction;
    private String input;
    public Command(Instruction instruction, String input) {
        this.instruction = instruction;
        this.input = input;
    }

    public abstract void execute(TaskList list, Storage storage) throws DuduException;


}
