import java.util.Scanner;

public class Joe {
    public static void main(String[] args) {
        int maxTasks = 100;
        Scanner scanner = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int taskCount = 0;
        String logo = " ╔╦═╦══╦═╦╗\n" +
                      " ║╚╗║╔╗║═╣║\n" +
                      " ║╔╣║╚╝║═╣║\n" +
                      " ║╚═╩══╩═╝║\n" +
                      " ╚════════╝\n";

        printWelcome(logo);
        printHelp();

        while (true) {
            userInput = scanner.nextLine();
            if (userInput.equalsIgnoreCase("bye")) {
                break;
            }
            else if (userInput.equalsIgnoreCase("list")) {
                printTasks(tasks, taskCount);
                printHelp();
            }
            else if (userInput.startsWith("mark ")) {
                markTask(tasks, userInput, taskCount);
                printHelp();
            }
            else if (userInput.startsWith("unmark ")) {
                unmarkTask(tasks, userInput, taskCount);
                printHelp();
            }
            else {
                if (taskCount >= maxTasks) {
                    printTasksFull();
                }
                else {
                    tasks[taskCount] = new Task(userInput);
                    taskCount++;
                    System.out.println("I just added " + userInput + " to your list!!!!");
                }
            }
        }
        printBye();
        scanner.close();
    }

    public static void printWelcome(String logo){
        System.out.println("Hiiii, my name is\n" + logo );
        System.out.println("I am here to help :3\n");
    }

    public static void printHelp(){
        System.out.println("How can i help you darling? \n");
    }

    public static void printBye(){
        System.out.println("Love ya <3 See ya!!\n");
    }
    public static void printTasksFull(){
        System.out.println("Honey, you have wayyyy too many tasks. Instead of adding more tasks, why don't you actually go do some of them \n");
    }
    public static void printTasks(Task[] tasks, int nextTask){
        if (tasks[0] == null){
            System.out.println("Your list is empty ;_;");
            return;
        }
        for (int i = 0; i < nextTask; i++) {
            System.out.println(i+1 + ".     " + tasks[i] + "\n");
        }
    }

    public static void markTask(Task[] tasks, String userInput, int taskCount){
        int index = Integer.parseInt(userInput.substring(5)) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsDone();
            System.out.println("Okayyy i marked it! \n" + tasks[index]);
        }
    }

    public static void unmarkTask(Task[] tasks, String userInput, int taskCount){
        int index = Integer.parseInt(userInput.substring(7)) - 1;
        if (index >= 0 && index < taskCount) {
            tasks[index].markAsNotDone();
            System.out.println("Okayyy i unmarked it! \n" + tasks[index]);
        }
    }
}
