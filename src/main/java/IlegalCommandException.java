public class IlegalCommandException extends DukeException {

    Commands c;

    public IlegalCommandException(Commands c) {
        this.c = c;
    }

    @Override
    public String toString() {
        switch (this.c) {
            case LIST:
                return "Command Exception: Make sure that your list command is as such\n\nlist\n";
            case BYE:
                return "Command Exception: Make sure that your list command is as such\n\nbye\n\nHaving troubles saying bye huh ;)\n";
            case MARK:
                return "Command Exception: Make sure that your mark command is as such\n\nmark index-of-task\n";
            case UNMARK:
                return "Command Exception: Make sure that your unmark command is as such\n\nunmark index-of-task\n";
            case TODO:
                return "Command Exception: Make sure that your todo command is as such\n\ntodo name-of-task\n";
            case EVENT:
                return "Command Exception: Make sure that your event command is as such\n\nevent name-of-task /from start-date /to end-date\n";
            case DEADLINE:
                return "Command Exception: Make sure that your deadline command is as such\n\ndeadline name-of-task /by date-of-deadline\n";
            case DELETE:
                return "Command Exception: Make sure that your delete command is as such\n\ndelete index-of-task\n";
            case UNRECOGNIZED:
                return "Command Exception: That is not a recognized command!\n";
        }
        return super.toString();
    }
}
