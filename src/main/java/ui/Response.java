package ui;

/**
 * Various responses of UwU_TaskMaster
 */
public enum Response {

    INVALID_COMMAND {
        @Override
        public String toString() {
            return "Ohh nyoo I cannyot ^w^ undewstand *cries* what you mean, c-can you twy again?";
        }
    },
    MISSING_DEADLINE_DETAILS {
        @Override
        public String toString() {
            return "Oh nyoo did you fowget t-to name or add a date t-to this deadline?!? *blushes*";
        }
    },

    MISSING_EVENT_DETAILS {
        @Override
        public String toString() {
            return "Oh nyoo did you fowget t-to name or add a /from or /to t-to this event?!? *blushes*";
        }
    },

    MISSING_TASK_NAME {
        @Override
        public String toString() {
            return "You didnt teww me the name of youw t-task UwU";
        }
    },

    COMPLETED_TASK {
        @Override
        public String toString() {
            return "Wowww c-congwatuwations on compweting youw task!!";
        }
    },

    INCOMPLETE_TASK {
        @Override
        public String toString() {
            return "omg nyoooooo why you have nyot compwete youw t-task :(";
        }
    },


    NO_TASK {
        @Override
        public String toString() {
            return "Wooks wike you do not have any tasks! *screams* OwO";
        }
    },

    EVENT_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw e-eevent has been successfuwwy added!";
        }
    },

    DEADLINE_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw d-deadline has been successfuwwy added!";
        }
    },

    TODO_ADDED {
        @Override
        public String toString() {
            return "Y-Y-Youw t-to-do has been successfuwwy added!";
        }
    },

    TASK_NOT_FOUND {
        @Override
        public String toString() {
            return "I can't find youw t-task oh nyooo!";
        }
    },

    TASK_DELETED {
        @Override
        public String toString() {
            return "Okay your t-task is deweted!";
        }
    },
    BYE_BYE {
        @Override
        public String toString() {
            return "Bye byee!!! UwU";
        }
    },

    DATE_FORMAT_INCORRECT {
        @Override
        public String toString() {
            return "I can't wead your d-date!! Can you give i-it t-to me in the format of '31/12/2023 1730'?";
        }
    },

    NO_MATCHING_TASKS {
        @Override
        public String toString() {
            return "ahh I can't find any tasks matching that keyword! Perhaps twy again?";
        }
    },

    NO_FREE_SLOTS {
        @Override
        public String toString() {
            return "Noo I can't find any free slots for the duration you requested! "
                    + "Why you so busy? Perhaps twy again?";
        }
    },

    WELCOME_HELP {
        @Override
        public String toString() {
            return "Hewwo?!?1 I am UwU TaskMaster! Hewes how I c-can hewp!"
                    + "\n1. Get help: 'help'"
                    + "\n2. Find free times: 'free [numberOfDesiredHours]'"
                    + "\n3. See all tasks: 'list'"
                    + "\n4. Add a to-do: 'to-do [name of to-do]'"
                    + "\n5. Add a deadline: 'deadline [name of deadline] /by [dd/mm/yyyy HH:mm]'"
                    + "\n6. Add an event: 'event [name of event] /from [dd/mm/yyyy HH:mm] /to [dd/mm/yyyy HH:mm]'"
                    + "\n7. Delete a task: 'delete [taskNumber]'"
                    + "\n8. Mark a task as completed: 'mark [taskNumber]'"
                    + "\n9. Mark a task as incomplete: 'unmark [taskNumber]'"
                    + "\n10. Save and say goodbye: 'bye'";
        }
    },

    DEFAULT {
        @Override
        public String toString() {
            return "I don't understand! Type in a c-c-command and ill hewp *sweats* you out!";
        }
    }
}

