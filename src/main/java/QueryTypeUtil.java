public class QueryTypeUtil {
    private static final String BYE_QUERY_TYPE = "bye";
    private static final String LIST_QUERY_TYPE = "list";
    private static final String MARK_QUERY_TYPE = "mark";
    private static final String UNMARK_QUERY_TYPE = "unmark";
    private static final String DELETE_QUERY_TYPE = "delete";
    private static final String TODO_QUERY_TYPE = "todo";
    private static final String DEADLINE_QUERY_TYPE = "deadline";
    private static final String EVENT_QUERY_TYPE = "event";

    public static QueryType GetQueryTypeFromString(String queryTypeStr) {
        switch(queryTypeStr) {
            case TODO_QUERY_TYPE:
                return QueryType.TODO;
            case DEADLINE_QUERY_TYPE:
                return QueryType.DEADLINE;
            case EVENT_QUERY_TYPE:
                return QueryType.EVENT;
            case LIST_QUERY_TYPE:
                return QueryType.LIST;
            case MARK_QUERY_TYPE:
                return QueryType.MARK;
            case UNMARK_QUERY_TYPE:
                return QueryType.UNMARK;
            case DELETE_QUERY_TYPE:
                return QueryType.DELETE;
            case BYE_QUERY_TYPE:
                return QueryType.EXIT;
            default:
                return QueryType.UNKNOWN;
        }
    }
}
