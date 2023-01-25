public enum TaskType {
    TODO, DEADLINE, EVENT;

    public String toString() {
        if (this == TODO) {
            return "todo";
        } else if (this == DEADLINE) {
            return "deadline";
        } else {
            return "event";
        }
    }
}