public class Parser {
    public String getCommandWord(String userInput) {
        return userInput.split(" ")[0];
    }

    public String getTaskDescription(String userInput) {
        return userInput.substring(userInput.indexOf(" ") + 1);
    }

    public String[] getTaskDetails(String userInput) {
        // For commands that include more details like deadline and event tasks
        return userInput.split(" /");
    }
    public Task parseTask(String userInput, String type) throws JoeException {
        String taskInfo = userInput.substring(userInput.indexOf(" ") + 1);

        switch (type) {
            case "todo":
                return new ToDo(taskInfo);
            case "deadline":
                String[] deadlineParts = taskInfo.split(" /by ");
                if (deadlineParts.length < 2) throw new JoeException("Come on cupcake, you have to tell me when your deadline is. Here's a hint, you can use the format: deadline [description] /by [deadline time]");
                return new Deadline(deadlineParts[0], deadlineParts[1]);
            case "event":
                // Adjusting the split logic to match the "event [event name] /from [start time] /to [end time]" format
                String[] eventParts = taskInfo.split(" /from ");
                if (eventParts.length < 2) throw new JoeException("Come on cupcake, you have to tell me when your event is. Here's a hint, you can use the format: event [description] /from [start time] /to [end time]");
                String eventName = eventParts[0];
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) throw new JoeException("Come on cupcake, you have to tell me when your event is. Here's a hint, you can use the format: event [description] /from [start time] /to [end time]");
                String startTime = timeParts[0];
                String endTime = timeParts[1];
                return new Event(eventName, startTime, endTime);
            default:
                throw new JoeException("Unknown task type.");
        }
    }


    public int parseTaskIndex(String userInput) throws JoeException {
        try {
            String indexPart = userInput.split(" ")[1]; // Assume index is the second part
            return Integer.parseInt(indexPart) - 1; // Convert to zero-based index
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new JoeException("Honey, that is NOT a number. How should I know what to do when you don't provide me with correct information??????");
        }
    }
}
