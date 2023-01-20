import java.util.ArrayList;
import java.util.LinkedHashMap;
public class Command {

    enum Argument {
        BYE,
        TODO,
        DEADLINE,
        BY,
        EVENT,
        FROM,
        TO,
        LIST,
        MARK,
        DELETE,
        NO_OP
    }

    /** Arguments of the command */
    private final LinkedHashMap<Argument, String> arguments;

    /**
     * Constructs a new command.
     *
     * @param input input string of the command.
     */
    public Command(String input) {
        this.arguments = new LinkedHashMap<>();
        for (String term : input.strip().split(" /")) {
            int firstSpace = term.indexOf(" ");
            Argument argument = parseArgument((firstSpace == -1
                    ? term
                    : term.substring(0, firstSpace)));
            String value = (firstSpace == -1
                    ? ""
                    : term.substring(firstSpace + 1));
            this.arguments.put(argument, value);
        }
        this.checkArguments();
    }

    /**
     * Returns the type of command.
     *
     * @return the type of command.
     */
    public Argument getName() {
        return this.arguments.entrySet().iterator().next().getKey();
    }

    /**
     * Returns the value of an argument.
     *
     * @param arg the argument to retrieve value of.
     * @return the value of the argument.
     */
    public String getArgumentValue(Argument arg) {
        return this.arguments.get(arg);
    }

    private Argument parseArgument(String arg) {
        if (arg.equals("")) {
            return Argument.NO_OP;
        }
        try {
            return Argument.valueOf(arg.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Command "
                    + arg + " not recognised");
        }
    }

    private void checkArguments() {
        ArrayList<Argument> requiredArgs = new ArrayList<>();
        ArrayList<Argument> requiredValues = new ArrayList<>();
        switch (this.getName()) {
        case BYE:
            // Fallthrough
        case LIST:
            break;
        case MARK:
            requiredValues.add(Argument.MARK);
            break;
        case DELETE:
            requiredValues.add(Argument.DELETE);
            break;
        case TODO:
            requiredValues.add(Argument.TODO);
            break;
        case DEADLINE:
            requiredArgs.add(Argument.BY);
            requiredValues.add(Argument.DEADLINE);
            requiredValues.add(Argument.BY);
            break;
        case EVENT:
            requiredArgs.add(Argument.FROM);
            requiredArgs.add(Argument.TO);
            requiredValues.add(Argument.EVENT);
            requiredValues.add(Argument.FROM);
            requiredValues.add(Argument.TO);
            break;
        }
        checkHasOnlyArgs(requiredArgs);
        checkHasValues(requiredValues);
    }

    private void checkHasOnlyArgs(ArrayList<Argument> args) {
        if (this.arguments.size() != args.size() + 1) {
            throw new IllegalArgumentException("Command " + this.getName()
                    + " takes in " + (args.size() + 1) + " argument(s) but "
                    + this.arguments.size() + " were given");
        }
        for (Argument arg : args) {
            if (!this.arguments.containsKey(arg)) {
                throw new IllegalArgumentException("Command " + this.getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }

    private void checkHasValues(ArrayList<Argument> args) {
        for (Argument arg : args) {
            if (!this.arguments.containsKey(arg)
                    || this.arguments.get(arg).equals("")) {
                throw new IllegalArgumentException("Command " + this.getName()
                        + " requires argument " + arg + " but was not given");
            }
        }
    }
}
