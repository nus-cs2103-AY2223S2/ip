public class Bot {
    private static final String BYE_QUERY = "bye";
    private static final String LIST_QUERY = "list";
    private static final String GOODBYE_RES = "GOOD BYE\n";
    private static final int HISTORY_CAPACITY = 100;

    private int historyCount = 0;
    String[] history = new String[HISTORY_CAPACITY];

    public BotResult process(String query) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status;
        StringBuilder response;

        switch(query) {
        case LIST_QUERY:
            if (historyCount < 1) {
                response = new StringBuilder("No items!");
            } else {
                response = new StringBuilder("\n");
                for (int i = 0; i < historyCount; i++) {
                    response.append(i+1).append(". ").append(history[i]).append("\n");
                }
            }
            status = BotResult.BotStatus.Successful;
            break;
        case BYE_QUERY:
            status = BotResult.BotStatus.Exit;
            response = new StringBuilder(GOODBYE_RES);
            break;
        default:
            status = BotResult.BotStatus.Successful;
            history[historyCount] = query;
            response = new StringBuilder("Added - " + query);
            historyCount++;
        }
        return new BotResult(status, formatter.format(response.toString()));
    }
}
