# User Guide

## Features

### Feature-ABC

Description of the feature.

### Feature-XYZ

Description of the feature.

## Usage

### `todo` - Adds a new Todo item

This command allows you to add a new "Todo" item into the task list. A "Todo" item must have a non-empty description, and can have optional tags.

Command format:

```
todo DESCRIPTION [/tag TAGS]
```

Example usage:

```
todo Sweep the floor /tag Low Priority, Fast
```

### `deadline` - Adds a new Deadline item

This command allows you to add a new "Deadline" item into the task list. A "Deadline" item must have the following:

- a non-empty description
- a "by" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`
  Optional tags are supported as well.

Command format:

```
deadline DESCRIPTION /by BY_DATE [/tag TAGS]
```

Example usage:

```
deadline Project Assignemnt /by 19-2-2023 /tag High Priority, Deadline Soon
```

### `event` - Adds a new Event item

This command allows you to add a new "Event" item into the task list.

A "Event" item must have the following:

- a non-empty description
- a "start" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`
- an "end" date. This can be inputed as a arbitrary string, or a string of the following format: `day-month-year [HHmm]`. For example: `3-16-2002 1400` or `10-10-1995`
  Optional tags are supported as well.

Command format:

```
event DESCRIPTION /from FROM_DATE /to TO_DATE [/tag TAGS]
```

Example usage:

```
event Trip to Universal Studios /from 14-4-2023 1400 /to 15-4-2023 1000 /tag Family, Vacation
```
