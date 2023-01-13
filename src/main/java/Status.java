public enum Status {
    DONE("X"),
    NOT_DONE(" ");

    private final String message;

    Status(String message) {
        this.message = message;
    }

    @Override
    public String toString(){
        return "[" + this.message + "]";
    }
}

