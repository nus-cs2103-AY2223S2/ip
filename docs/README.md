# Book User Guide

Welcome to
```
 ____
|  _ \  ___   ___  _
| |_| |/ _ \ / _ \| |  _
|  _ <| | | | | | | |/ /
| |_| | |_| | |_| |   <
|____/ \___/ \___/|_|\_\
```
a text-based task management application.

## Setup
1. Download `book.jar` under Releases
2. Double-click `book.jar`

## Commands (case sensitive):
* Create a Todo
    * `todo <description>`
* Create a Deadline
    * `deadline <description> /by <dd/MM/yy-HHmm>`
* Create an Event
    * `event <description /from <dd/MM/yy-HHmm> /to <dd/MM/yy-HHmm>`
* Delete a task
    * `delete <index>`
* Mark a task as complete
    * `mark <index>`
* Unmark a task as incomplete
    * `unmark <index>`
* Find a task that matches a keyword
    * `find <keyword>`
* List your tasks
    * `list`
* Sort your tasks alphabetically
    * `sort`
* Exit Book
    * `bye`

## Credits
[checkstyle.xml](https://github.com/se-edu/addressbook-level3/tree/master/config/checkstyle)  
[suppressions.xml](https://github.com/se-edu/addressbook-level3/tree/master/config/checkstyle)  
[gradle.yml](https://github.com/se-edu/duke/blob/full-template/.github/workflows/gradle.yml)