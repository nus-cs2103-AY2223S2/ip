public class Bot {
    private static final String BYE_QUERY = "bye";
    private static final String GOODBYE_RES = "GOOD BYE\n";

    public BotResult process(String query) {
        IFormatter formatter = new ResponseFormatter();
        BotResult.BotStatus status;
        String response;
        switch(query) {
            case BYE_QUERY:
                status = BotResult.BotStatus.Exit;
                response = GOODBYE_RES;
                break;
            default:
                status = BotResult.BotStatus.Successful;
                response = query;
        }
        return new BotResult(status, formatter.format(response));
    }
}
