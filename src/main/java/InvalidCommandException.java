public class InvalidCommandException extends LuluException {
    public InvalidCommandException() {
        super("(=✖ ᆺ ✖=) That's not a valid command.");
    }

    @Override
    public String toString() {
        return this.getMessage();
    }
}
