
public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String getStatusIcon() {
        return "[T]" + super.getStatusIcon();
    }
}
