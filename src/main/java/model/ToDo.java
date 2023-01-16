package model;

public class ToDo extends Task {
    public static final String TAG = "[T]";

    public ToDo(String title) {
        super(title);
    }

    @Override
    public String toString() {
        return ToDo.TAG + super.toString();
    }
}
