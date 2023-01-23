public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ToDo) {
            ToDo target = (ToDo) o;
            return target.description.equals(this.description) ;
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
