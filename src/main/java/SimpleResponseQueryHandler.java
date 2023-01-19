public class SimpleResponseQueryHandler implements IQueryHandler {
    String response;

    public SimpleResponseQueryHandler(String response) {
        this.response = response;
    }

    @Override
    public String processQuery(String query) throws DukeException {
        return response;
    }
}
