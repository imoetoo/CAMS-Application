package CampApplication.Utilities;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
/**
 * Check for date input function
 *
 * @author Group2
 * @version 23/11/2023
 * @since 01/11/2023
 */
public class GetDate {
    public static LocalDate getDate(String displayMessage) {
        Scanner sc = new Scanner(System.in);
        LocalDate localDate = null;
        while (localDate == null) {
            System.out.println(displayMessage);
            String input = sc.nextLine();
            try {
                localDate = LocalDate.parse(input);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid input. Please enter a date in the format yyyy-mm-dd.");
            }
        }

        return localDate;

    }
}
