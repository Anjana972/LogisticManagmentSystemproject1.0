import java.util.Scanner;

public class Main {

    static Scanner sc=new Scanner(System.in);
    static int cityCount=0;
    static String[]cities=new String[30];
    static int[][]cityDistance=new int[30][30];

    public static void main(String[] args) {

        //System.out.println("Hello from Logistics Management System!"); this was just used as the 1st commit

        System.out.printf("======================================================%n");
        System.out.printf("LOGISTIC MANAGEMENT SYSTEM%n");
        System.out.println();
        System.out.println("------------------------MAIN MENU-----------------------------");

        int choice;
        //creating the  main menu

        do{
            System.out.println("1) City Management");
            System.out.println("2)Distance Management");
            System.out.println("3)Vehicle Management");
            System.out.println("4)Delivery Request Handling");
            System.out.println("5)Reports (Cost,Fuel calculations)");
            System.out.println("6)Exit");
            System.out.println();

            System.out.println("Enter your choice: ");
            choice=sc.nextInt();

            switch(choice){
                case 1:
                    cityManagement();
                    break;

                case 2:
                    distanceManagement();
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
    //1.0
    public static void cityManagement(){

        int subChoice1;
        do {
            System.out.println("\t\t\t\n1)City Management\n");
            System.out.println("\t\n------------------ 1)MENU-------------------");
            System.out.println("1.1.Display All Cities");
            System.out.println("1.2.Add A New City");
            System.out.println("1.3.Rename An Existing City");
            System.out.println("1.4.Remove An Existing City");
            System.out.println("1.5.Back To The Main Menu");

            System.out.println("\t\nEnter Your Choice: ");
            subChoice1 = sc.nextInt();

            switch (subChoice1) {

                case 1:
                    displayCity();
                    break;
                case 2:
                    addCity();
                    break;
                case 3:
                    renameCity();
                    break;
                case 4:
                    removeCity();
                    break;
                case 5:
                    System.out.println("Back To The Main Menu...");
                default:
                    System.out.println("Invalid Choice!!!");

            }
        }while(subChoice1!=5);
    }

    //1.1 Displaying all cities
    public static void displayCity(){
        if (cityCount == 0) {
            System.out.println("No cities available.");
            return;
        }
        System.out.println("\nCurrent Cities:");
        for (int i = 0; i < cityCount; i++) {
            System.out.println((i + 1) + ". " + cities[i]);
        }
    }

    //1.2.adding new cities
    public static void addCity(){

        if(cityCount>cities.length){
           System.out.println("City limit is reached..can not add more!!");
           return;
       }
        sc.nextLine();

           System.out.println("Enter City Name : ");
           String newCity = sc.nextLine();

           cities[cityCount++] = newCity;
           System.out.println(newCity + " is added successfully...");

    }

    //1.2.Renaming Already Existing City
    public static void renameCity(){
        displayCity();
        System.out.println();

        System.out.println("Enter the index number of the city you want to rename : ");
        int cityIndex=sc.nextInt()-1;
        sc.nextLine();

        if(cityIndex>cityCount){
            System.out.println("Entered City Index Is Incorrect!!! Check Again..");
            return;
        }
        System.out.println("Enter New Name For The City: ");
        String newCityName=sc.nextLine();
        cities[cityIndex]=newCityName;
        System.out.println("City Renamed Successfully!!");

    }

    //1.3.Removing an existing city
    public static void removeCity(){
        displayCity();
        System.out.println();

        System.out.println("Enter The Index Of The City You Want To Remove : ");
        int cityIndex=sc.nextInt()-1;
        sc.nextLine();

        if(cityIndex>cityCount){
            System.out.println("Entered City Index Is Incorrect!!! Check Again..");
            return;
        }
        for (int i = cityIndex; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }
        cityCount--;
        System.out.println("City removed successfully!");
    }
    //2.0
    public static void distanceManagement() {
        int subchoice;
        do {
            System.out.println("\n\t\t\t2)DISTANCE MANAGEMENT\n");
            System.out.println("\t\n------------------ MENU-------------------");
            System.out.println("2.1.Input Or Edit Distance Between Two Cities");
            System.out.println("2.2.View Distance Table");
            System.out.println("2.3.Back To The Main Menu\n");

            System.out.println("Enter Your Choice: ");
            subchoice = sc.nextInt();
            sc.nextLine();

            switch (subchoice) {
                case 1:
                    enterOrEditDistance();
                    break;
                case 2:
                    distanceTable();
                    break;

            }

        } while (subchoice != 3);
    }
        //2.1.input or edit distance between two cities
        public static void enterOrEditDistance () {
            displayCity();
            System.out.println();

            System.out.println("Entering Index Of The Starting city: ");
            System.out.println("From: ");
            int startingIndex = sc.nextInt() - 1;

            System.out.println("Entering Index Of The destination");
            System.out.println("To: ");
            int destinationIndex = sc.nextInt() - 1;

            if (startingIndex == destinationIndex) {
                System.out.println("Distance From a City To Itself is always 0 ");
            }
            if (startingIndex >= cityCount || destinationIndex >= cityCount) {
                System.out.println("City Index Is Invalid!!!Check Again...");
            }
            System.out.println("Enter Distance(in km): ");
            int distance = sc.nextInt();

            cityDistance[startingIndex][destinationIndex] = distance;
            cityDistance[destinationIndex][startingIndex] = distance;
            System.out.println("Distance Updated Successfully!!!");

        }
        //2.2.view distance table
        public static void distanceTable () {

        if(cityCount==0){
            System.out.println("No Cities Are Available...");
        }
        System.out.printf("%nDISTANCE TABLE%n");
        for(int i=0;i<cityCount;i++){
            System.out.printf("%-10s",cities[i]);
        }
            System.out.println();

        for(int i=0;i<cityCount;i++){
            System.out.printf("%-8s",cities[i]);
            for(int j=0;j<cityCount;j++){
                System.out.printf("%-10d",cityDistance[i][j]);
            }
            System.out.println();
        }
    }
    }


