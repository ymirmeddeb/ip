import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ╔╦═╦══╦═╦╗\n" +
                      " ║╚╗║╔╗║═╣║\n" +
                      " ║╔╣║╚╝║═╣║\n" +
                      " ║╚═╩══╩═╝║\n" +
                      " ╚════════╝\n";
        System.out.println("Hiiii, my name is\n" + logo );
        System.out.println("I am here to help :3\n");
        String line;
        String[] tasks = new String[100];
        int nextTask = 0;
        Scanner in = new Scanner(System.in);
        System.out.print("Yes darling, what can i do for you?\n");
        line = in.nextLine();
        while (!line.equals("bye")){
            if (line.equals("list")){
                printTasks(tasks, nextTask);
            }
            if (!line.equals("list")) {
                tasks[nextTask] = line;
                nextTask++;
                System.out.println("I just added " + line + " to your list!!!");
            }
            System.out.print("Yes darling, what can i do for you?\n");
            line = in.nextLine();
        }
        System.out.println("Love ya <3 See ya!!\n");
    }

    public static void echo(String word){
        System.out.println(word);
        return;
    }

    public static void printTasks(String[] tasks, int nextTask){
        if (tasks[0] == null){
            System.out.println("Your list is empty ;_;");
            return;
        }
        for (int i = 0; i < nextTask; i++) {
            System.out.println(i+1 + ".   " + tasks[i] + "\n");
        }
    }

}
