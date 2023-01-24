import java.util.ArrayList;

public class Request {
    private final String description;

    public Request (String description) {
        this.description = description;
    }

    public Request createRequest(String description) {
        return new Request(description);
    }

    @Override
    public String toString() {
        return description;
    }


}
