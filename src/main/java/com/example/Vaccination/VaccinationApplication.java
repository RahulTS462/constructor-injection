package com.example.Vaccination;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class VaccinationApplication {

    public static void main(String[] args) {

        /*
		You need to complete this application as mentioned in the problem 
		statement build your own logic and perform the following tasks.

		 Tasks:
		1. Fetch context from ApplicationContext.xml and initiate Scanner.
		2. Fetch vaccine and User type choice.
		3. Get the required bean from context.
		4. Get the appointment details form user
		5. Display the appointment details
		6. Run the loop again to book for another user or else exit.
		 */
        ClassPathXmlApplicationContext context =new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("Welcome to Vaccination application.");
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("Please choose your vaccine preference: \n1. Covid.\n2. Typhoid.\n3. Polio.");
            int vaccineChoice = scanner.nextInt();
            String vaccine = "";
            switch (vaccineChoice){
                case 1-> vaccine = "Covid";
                case 2-> vaccine = "Typhoid";
                case 3-> vaccine = "Polio";
                default -> {
                    System.out.println("Invalid choice");
                    continue;
                }
            }
            System.out.println("Whom do you want to vaccinate: \n1. Self.\n2. Father.\n3. Mother.\n4. Spouse.\n5. Exit");
            int userChoice = scanner.nextInt();
            scanner.nextLine();
            String user = "";
            switch (userChoice){
                case 1-> user = "self";
                case 2-> user = "father";
                case 3-> user = "mother";
                case 4-> user = "spouse";
                case 5-> {
                    return;
                }
                default -> {
                    System.out.println("Invalid choice");
                    continue;
                }
            }
            String userVaccine = user + vaccine;
            User userBean = (User) context.getBean(userVaccine);
            if (userBean.IsVaccinated()){
                System.out.println("User is already Vaccinated.");
                System.out.println("Do you want to register for someone Else\n1. Yes\n2. No");
                int continueVaccination = scanner.nextInt();
                scanner.nextLine();
                switch (continueVaccination){
                    case 1-> {continue;}
                    case 2-> {
                        return;
                    }
                }
            }
            System.out.println("Please enter " + user + " details");
            System.out.println("Name: ");
            String userName = scanner.nextLine();
            System.out.println("Age: ");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Appointment Date (YYYY-MM-DD): ");
            String date = scanner.nextLine();
            System.out.println("Appointment Time (HH:MM AM/PM): ");
            String time = scanner.nextLine();
            System.out.println("Appointment Location: ");
            String location = scanner.nextLine();
            TimeAndLocation timeAndLocation = (TimeAndLocation) context.getBean("timeAndLocation");
            timeAndLocation.setDetails(time, location, date);
            userBean.setUserDetails(userName, age, timeAndLocation);
            userBean.setAppointment();
            System.out.println("Do you want to register for someone Else\n1. Yes\n2. No");
            int continueVaccination = scanner.nextInt();
            scanner.nextLine();
            switch (continueVaccination){
                case 1-> {continue;}
                case 2-> {
                    return;
                }
            }
        }

    }
}