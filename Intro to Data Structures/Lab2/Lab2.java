//Jose Salinas <salinasj14>

import java.util.Scanner;
public class Lab2
{
public static void main(String[] args)
  {
    System.out.println("Enter number of seconds");
    Scanner keyboard = new Scanner(System.in);
    int seconds = keyboard.nextInt();
    int days = seconds/86400;
    int daysLeft = seconds%86400;
    int hours = daysLeft/3600;
    int hoursLeft = daysLeft%3600;
    int mins = hoursLeft/60;
    int minsLeft = hoursLeft%60;
    int secs = minsLeft;
    
    System.out.println(days + " Days " + hours + " Hours " + mins +" Minutes " +secs + " Seconds ");

  }
}
