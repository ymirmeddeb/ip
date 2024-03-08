import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
                if (deadlineParts.length < 2) throw new JoeException("Invalid deadline format. Please use the format: deadline [description] /by yyyy-MM-dd HHmm");
                try {
                    // Attempt to parse the date string
                    LocalDateTime by = LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    return new Deadline(deadlineParts[0], deadlineParts[1]); // Use the original string, assuming your Deadline constructor handles the conversion.
                } catch (DateTimeParseException e) {
                    // Throw a custom exception if parsing fails
                    throw new JoeException("Invalid date format. Please use the format: yyyy-MM-dd HHmm");
                }
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