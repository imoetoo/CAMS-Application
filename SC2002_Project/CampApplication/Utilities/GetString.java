package CampApplication.Utilities;

import java.util.Scanner;
/**
 * Check for string input function
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class GetString {
    public static String getString(String displayMessage) {
        Scanner sc = new Scanner(System.in);
        String result;
        while (true) {
            try {
                System.out.println(displayMessage);
                result = sc.nextLine();
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                sc.nextLine();
            }
        }
        return result;
    }
}
