package duke.query;

/**
 * The QueryTypeUtil class provides utility functions for handling query types.
 */
public class QueryTypeUtil {
    public static final String GREET_QUERY_TYPE = "hi";
    public static final String HELP_QUERY_TYPE = "help";
    public static final String BYE_QUERY_TYPE = "bye";
    public static final String LIST_QUERY_TYPE = "list";
    public static final String MARK_QUERY_TYPE = "mark";
    public static final String UNMARK_QUERY_TYPE = "unmark";
    public static final String DELETE_QUERY_TYPE = "delete";
    public static final String TODO_QUERY_TYPE = "todo";
    public static final String DEADLINE_QUERY_TYPE = "deadline";
    public static final String EVENT_QUERY_TYPE = "event";
    public static final String FIND_QUERY_TYPE = "find";
    public static final String LOAN_QUERY_TYPE = "loan";

    /**
     * @param queryTypeStr string that represents a query type
     * @return QueryType enum that matches that string
     */
    public static QueryType getQueryTypeFromString(String queryTypeStr) {
        switch (queryTypeStr) {
        case GREET_QUERY_TYPE:
            return QueryType.GREET;
        case HELP_QUERY_TYPE:
            return QueryType.HELP;
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
        case FIND_QUERY_TYPE:
            return QueryType.FIND;
        case BYE_QUERY_TYPE:
            return QueryType.EXIT;
        case LOAN_QUERY_TYPE:
            return QueryType.LOAN;
        default:
            return QueryType.UNKNOWN;
        }
    }
}
