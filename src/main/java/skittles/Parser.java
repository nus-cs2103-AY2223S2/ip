package skittles;

import skittles.instruction.*;
import skittles.instruction.Instruction;


public class Parser {
    private final ListOfStuff skittlesList;
    private final Data data;

    /**
     * Constructor for Parser
     * @param skittlesList Lists of Tasks taken in.
     * @param data Data object.
     */
    public Parser(ListOfStuff skittlesList, Data data) {
        this.skittlesList = skittlesList;
        this.data = data;
    }

    /**
     * Interpret what the user has entered as an input and categorises it into a Command.
     * @param input User's input
     * @return The correct command that is interpreted from the user input.
     */
    public Instruction inputToCommand(String input) {
        String[] inputs = input.split(" ", 2);
        String command = inputs[0].toLowerCase();
        switch (command) {
            case "bye":
                return new ExitInstruction();
            case "list":
                return new ListInstruction(skittlesList);
            case "delete":
                return new DeleteInstruction(skittlesList);
            case "mark":
                return new DoneInstruction(skittlesList);
            case "unmark":
                return new UndoneInstruction(skittlesList);
            case "find":
                return new FindInstruction(skittlesList);
            case "sort":
                return new SortInstruction(skittlesList);
            case "deadline":
            case "event":
            case "todo":
                return new AddInstruction(skittlesList, data, command);
            default:
                //If no cases above are entered,skittles will not understand the command and prompt the user.
                return new WrongInstruction();
        }
    }

}
