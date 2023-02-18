# User Guide

CatBot is a simple CLI interface for keeping track of your tasks!

## Features

* Todo Tasks
* Deadline Tasks
* Event Tasks
* Recurring Tasks
* Listing all tasks
* Deleting Tasks
* Marking tasks as done
* Finding tasks which match a given keyword
* Making CatBot repeat a message
* Understand dates in natural language
* Store Tasks persistently

### Todo Tasks

Todo Tasks are tasks which need to be completed by an unspecified time.\
Format: `todo <description>` \
Example: `todo Do the laundry.`

### Deadline Tasks

Deadline tasks are tasks which need to be completed by a set deadline.\
Format: `deadline <description> \by <time>`\
Example: `deadline Do the laundry \by Friday 6pm`

### Event Tasks

Events are tasks which take place over a specified period of time.
Format: `event <description> \from <time> \to <time>`\
Example: `event Do the laundry \from Friday 6pm \to Friday 7pm`

Note: The day must be repeated even if it is the same.

### Recurring Tasks

Recurring tasks are tasks which repeat at a set interval.\
Format: `recurring <description> \at <time> \every <duration>` \
or `recurring <description> \on <time> \every <duration>` \
Example: `recurring Practice Japanese \at 6pm \every 1 day`\

Note: 1 is required for single units of time eg. `1 day`, `1 week`, `1 hour`, etc.

### Listing Tasks

Get a list of all the tasks in the order they were added in.\
Format: `list`

### Deleting tasks

Delete tasks by their position in the list.\
Format: `delete <position>`\
Example: `delete 1`

### Marking tasks as done

Mark tasks as done so that they display with a checkmark.\
Format: `mark <position>`\
Example: `mark 1`

Unmark tasks which were previously marked as done.\
Format: `unmark <position>`\
Example: `unmark 1`

### Finding tasks

Search for tasks which contain a given keyword.\
Format: `find <keyword>`\
Example: `find laundry`

### Making CatBot repeat a message

Echo a given message just for fun.\
Format: `echo <message>`\
Example: `echo meow!`