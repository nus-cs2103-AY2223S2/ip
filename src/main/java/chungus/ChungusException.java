package chungus;

class ChungusException extends RuntimeException {
    public ChungusException(String msg) {
        super(msg);
    }

    public ChungusException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

class TaskNotFoundException extends ChungusException {
    public TaskNotFoundException(int idx) {
        super(String.format("Task %d does not exist", idx));
    }
}

class TaskMarshalException extends ChungusException {
    public TaskMarshalException(String s) {
        super(String.format("Bad marshal format: \"%s\"", s));
    }
}
