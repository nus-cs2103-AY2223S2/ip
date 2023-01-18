public class Command {

    /** Arguments of the command */
    private String[][] arguments;
    private int numArgs;

    public Command(String input) {
        String[] terms = input.strip().split(" /");
        this.arguments = new String[terms.length][];
        this.numArgs = 0;
        for (String term : terms) {
            int firstSpace = term.indexOf(" ");
            String key = (firstSpace == -1
                    ? term
                    : term.substring(0, firstSpace));
            String value = (firstSpace == -1
                    ? ""
                    : term.substring(firstSpace + 1));
            this.arguments[this.numArgs] = new String[] {key, value};
            this.numArgs += 1;
        }
        this.checkArgs();
    }

    /**
     * Returns the type of command.
     * @return the type of command.
     */
    public String getType() {
        return this.arguments[0][0];
    }

    public String getArg(String key) {
        for (int i = 0; i < this.arguments.length; i++) {
            if (this.arguments[i][0].equals(key)) {
                return this.arguments[i][1];
            }
        }
        throw new IllegalArgumentException("Command " + this.arguments[0][0]
                + " does not contain keyword argument " + key);
    }

    private void checkArgs() {
        if (this.arguments.length == 0) {
            return;
        }
        switch (this.arguments[0][0]) {
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
            throw new IllegalArgumentException("Command " + this.arguments[0][0]
                    + " not recognised");
        }
    }

    private void checkHasOnly(String[] args) {
        if (this.arguments.length > 1 + args.length) {
            throw new IllegalArgumentException("Command " + this.arguments[0][0]
                    + " takes in " + args.length + " argument(s) but "
                    + this.arguments.length + " were given");
        }
        for (String arg : args) {
            if (!contains(arg)) {
                throw new IllegalArgumentException("Command " + this.arguments[0][0]
                        + " requires argument " + arg + " but was not given");
            }
        }
    }

    private boolean contains(String arg) {
        for (int i = 1; i < this.arguments.length; i++) {
            if (this.arguments[i][0].equals(arg)) {
                return true;
            }
        }
        return false;
    }

}
