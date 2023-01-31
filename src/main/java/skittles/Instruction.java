package skittles;
import java.util.Locale;

public enum Instruction {
    UNRECOGNISED,
    LIST,
    MARK,
    TODO,
    DEADLINE,
    EVENT,
    DELETE,
    BYE;

    public static Instruction scanUserTyped(String userTyped) {
        String scannedWord = userTyped.toLowerCase();
        Instruction newInstruction;
        switch (scannedWord) {
            case "list":
                newInstruction = Instruction.LIST;
                break;
            case "mark":
                newInstruction = Instruction.MARK;
                break;
            case "todo":
                newInstruction = Instruction.TODO;
                break;
            case "deadline":
                newInstruction = Instruction.DEADLINE;
                break;
            case "event":
                newInstruction = Instruction.EVENT;
                break;
            case "delete":
                newInstruction = Instruction.DELETE;
                break;
            case "bye":
                newInstruction = Instruction.BYE;
                break;
            default:
                newInstruction = Instruction.UNRECOGNISED;
                break;
        }
        return newInstruction;
    }
}
