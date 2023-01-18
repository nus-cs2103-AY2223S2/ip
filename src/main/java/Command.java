import java.util.ArrayList;
import java.util.Arrays;

public class Command {

    /** Arguments of the command */
    private ArrayList<ArrayList<String>> arguments;

    public Command(String input) {
        String[] terms = input.strip().split(" /");
        this.arguments = new ArrayList<ArrayList<String>>();
        for (String term : terms) {
            int firstSpace = term.indexOf(" ");
            String key = (firstSpace == -1
                    ? term
                    : term.substring(0, firstSpace));
            String value = (firstSpace == -1
                    ? ""
                    : term.substring(firstSpace + 1));
            this.arguments.add(new ArrayList<String>(
                    Arrays.asList(key, value)));
        }
        this.checkArgs();
    }

    /**
     * Returns the type of command.
     * @return the type of command.
     */
    public String getType() {
        return this.arguments.get(0).get(0);
    }

    public String getArg(String key) {
        for (int i = 0; i < this.arguments.size(); i++) {
            if (this.arguments.get(i).get(0).equals(key)) {
                return this.arguments.get(i).get(1);
            }
        }
        throw new IllegalArgumentException("Command " + this.arguments.get(0).get(0)
                + " does not contain keyword argument " + key);
    }

    private void checkArgs() {
        if (this.arguments.size() == 0) {
            return;
        }
        switch (this.arguments.get(0).get(0)) {
        case "bye":
            // Fallthrough
        case "list":
            // Fallthrough
        case "mark":
            // Fallthrough
        case "todo":
            checkHasOnly(new String[] {});
            break;
        case "deadline":
            checkHasOnly(new String[] {"by"});
            break;
        case "event":
            checkHasOnly(new String[] {"from", "to"});
            break;
        default:
            throw new IllegalArgumentException("Command " + this.arguments.get(0).get(0)
                    + " not recognised");
        }
    }

    private void checkHasOnly(String[] args) {
        if (this.arguments.size() > 1 + args.length) {
            throw new IllegalArgumentException("Command " + this.arguments.get(0).get(0)
                    + " takes in " + args.length + " argument(s) but "
                    + this.arguments.size() + " were given");
        }
        for (String arg : args) {
            if (!contains(arg)) {
                throw new IllegalArgumentException("Command " + this.arguments.get(0).get(0)
                        + " requires argument " + arg + " but was not given");
            }
        }
    }

    private boolean contains(String arg) {
        for (int i = 1; i < this.arguments.size(); i++) {
            if (this.arguments.get(i).get(0).equals(arg)) {
                return true;
            }
        }
        return false;
    }
}
