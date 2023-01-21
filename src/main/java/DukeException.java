class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}

class markException extends DukeException{
    public markException() {
        super("I don't know which task to mark...");
    }
}

class taskNotFoundException extends DukeException{
    public taskNotFoundException() {
        super("Task not found...");
    }
}

class deleteException extends DukeException{
    public deleteException() {
        super("I don't know which task to delete...");
    }
}

class todoException extends DukeException{
    public todoException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}

class deadlineException extends DukeException{
    public deadlineException() {
        super("☹ OOPS!!! The description of a deadline cannot be empty.");
    }
}

class eventException extends DukeException{
    public eventException() {
        super("☹ OOPS!!! The description of a event cannot be empty.");
    }
}


