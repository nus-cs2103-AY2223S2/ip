package leo.task;

/**
 * Custom Exceptions thrown by Leo.
 */
public class LeoTaskException extends Exception {
    public LeoTaskException(String msg) {
        super(msg);
    }
}

class EmptyFieldException extends LeoTaskException {
    EmptyFieldException(String msg) {
        super(msg);
    }

    EmptyFieldException() {
        super("Task description cannot be empty but...can you do it on a rainy night in Stoke?");
    }
}


class InvalidCommandException extends LeoTaskException {
    InvalidCommandException() {
        super("I'm sorry, I don't know what you want. ¿Que miras bobo?\n Use 'help' to see the list of commands.");
    }
}

class MissingDeadlineException extends EmptyFieldException {
    MissingDeadlineException() {
        super("When is it due bruv? I never make predictions, and I never will.");
    }
}

class MissingTimelineException extends EmptyFieldException {
    MissingTimelineException() {
        super("Not this again, indicate a from and to. I'm as happy as I can be—but I have been happier.");
    }
}

class EmptyDeletionException extends LeoTaskException {
    EmptyDeletionException() {
        super("Bruh, why are you trying to delete from an empty task list...");
    }
}
