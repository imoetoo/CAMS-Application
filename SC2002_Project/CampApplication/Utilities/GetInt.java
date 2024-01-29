package CampApplication.Utilities;

import java.util.Scanner;
/**
 * Check for integer input function
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class GetInt {
    public static int getInt(String displayMessage) {
        Scanner sc = new Scanner(System.in);
        int result;
        while (true) {
            try {
                System.out.println(displayMessage);
                result = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }
        return result;
    }
}
