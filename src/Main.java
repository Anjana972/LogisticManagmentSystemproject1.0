import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);

        //System.out.println("Hello from Logistics Management System!");

        System.out.printf("======================================================%n");
        System.out.printf("LOGISTIC MANAGEMENT SYSTEM%n");
        System.out.println();
        System.out.println("------------------------menu-----------------------------");

        int choice;
        //creating the menu

        do{
            System.out.println("1) City Management");
            System.out.println("2)Distance Management");
            System.out.println("3)Vehicle Management");
            System.out.println("4)Delivery Request Handling");
            System.out.println("5)Reports (Cost,Fuel calculations)");
            System.out.println("6)Exit");
            System.out.println();

            System.out.println("Enter your choice: ");;
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("selected City Management......");
                    break;

                case 2:
                    System.out.println("selected Distance Management............");
                    break;

                case 3:
                    System.out.println("Selected vehicle Management............");
                    break;

                case 4:
                    System.out.println("Selected Delivery Request Handling........");
                    break;

                case 5:
                    System.out.println("Selected Reports.......");
                    break;

                case 6:
                    System.out.println("Exiting...................");

                default:
                    System.out.println("Invalid input!!!Please Try Again...");
            }
        }while(choice!=5);
    }
}
