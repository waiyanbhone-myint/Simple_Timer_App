package com.ps;
import java.nio.file.attribute.UserPrincipal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static LocalDate today = LocalDate.now();
    public static void main(String[] args) {

        System.out.println("What is the event you would like me to remind?: ");
        String eventName = scanner.nextLine();

        LocalDate eventDate = userEventDate(eventName);

        calculateDaysLeftOrNot(eventName, eventDate);

    }

    public static LocalDate userEventDate(String eventName){

        String[] possibleDatePatterns = {"M d yyyy", "M/d/yyyy", "M-d-yyyy", "M_d_yyyy",};

        System.out.println("What is your " + eventName + " date?\n\"M d yyyy\", \"M/d/yyyy\", \"M-d-yyyy\", \"M_d_yyyy\"");
        String userInputDate = scanner.nextLine();

        for(String pattern : possibleDatePatterns){
            try{
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                LocalDate parsedDate = LocalDate.parse(userInputDate, formatter);
                //System.out.println(parsedDate);
                return parsedDate;
            }
            catch (Exception e) {

            }
        }
        System.out.println("Please only enter the valid format!");
        return userEventDate(eventName);
    }

    private static void calculateDaysLeftOrNot(String eventName, LocalDate eventDate) {
        StringBuilder finalResult = new StringBuilder();

        long daysLeft = ChronoUnit.DAYS.between(today, eventDate);
        //long monthsLeft = ChronoUnit.MONTHS.between(today, userInputDate());

        if (daysLeft < 0){
            long formattedDaysLeft = Math.abs(daysLeft); //get rid of negative sign

            finalResult.append("Your ").append(eventName).append(" over on ");
            finalResult.append(eventDate.getDayOfWeek()).append(", ");
            finalResult.append(formattedDaysLeft).append(" days ago!");
            finalResult.append("!");
            System.out.println(finalResult.toString());
        }
        else{
            finalResult.append("Your ").append(eventName).append(" is on ");
            finalResult.append(eventDate.getDayOfWeek()).append(", ");
            finalResult.append(daysLeft).append(" days in ").append(eventDate);
            finalResult.append("!");
            System.out.println(finalResult.toString());
        }
    }


}