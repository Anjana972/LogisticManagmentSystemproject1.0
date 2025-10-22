import java.util.Scanner;

public class Main {

    static Scanner sc=new Scanner(System.in);

    static int cityCount=0;

    static String[]cities=new String[30];

    static int[][]cityDistance=new int[30][30];

    static String[]vehicleTypes={"Van","Truck","Lorry"};
    static int[]vehicleCapacity={1000,5000,10000};
    static int[]ratePerKm={30,40,80};  //LKR
    static int[]avgSpeed={60,50,45};  //(km/h)
    static int[]fuelEfficiency={12,6,4}; //(km/l)

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
                    vehicleManagement();
                    break;

                case 4:
                    deliveryHandling();
                    break;

                case 5:
                    //System.out.println();
                    break;

                case 6:
                    System.out.println("Exiting...................");
                    break;

                default:
                    System.out.println("Invalid input!!!Please Try Again...");
            }
        }while(choice!=6);
    }
    //1.0
    public static void cityManagement(){

        int subChoice1;
        do {
            System.out.println("\t\t\t\n1)City Management\n");
            System.out.println("\t\n------------------ 1)MENU-------------------");
            System.out.println("1.Display All Cities");
            System.out.println("2.Add A New City");
            System.out.println("3.Rename An Existing City");
            System.out.println("4.Remove An Existing City");
            System.out.println("5.Back To The Main Menu");

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

        if(cityCount>=cities.length){
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

        if(cityIndex>=cityCount){
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

        if(cityIndex>=cityCount){
            System.out.println("Entered City Index Is Incorrect!!! Check Again..");
            return;
        }
        for (int i = cityIndex; i < cityCount - 1; i++) {
            cities[i] = cities[i + 1];
        }
        cityCount--;
        System.out.println("City removed successfully!");
    }
    //2.0 distance managmnet
    public static void distanceManagement() {
        int subchoice;
        do {
            System.out.println("\n\t\t\t2)DISTANCE MANAGEMENT\n");
            System.out.println("\t\n------------------ MENU-------------------");
            System.out.println("1.Input Or Edit Distance Between Two Cities");
            System.out.println("2.View Distance Table");
            System.out.println("3.Back To The Main Menu\n");

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
                case 3:
                    System.out.println("Returning Back To The Main Menu....");
                    break;
                default:
                    System.out.println("Invalid Choice!!!");

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
                return;
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
    //3.0 vehicle management
    public static void vehicleManagement() {
        int subChoice;
        do {
            System.out.println("\n\t\t\t3)VEHICLE MANAGEMENT");
            System.out.println("\t\n------------------ MENU-------------------");
            System.out.println("\n1.Display All Vehicle Types");
            System.out.println("2.Back To Main Menu");

            System.out.println("\nEnter Your Choice: ");
            subChoice=sc.nextInt();
            sc.nextLine();

            switch (subChoice) {
                case 1:
                    displayVehicles();
                    break;
                case 2:
                    System.out.println("Returning Back To The Main Menu...");
                    break;
                default:
                    System.out.println("Invalid Choice!!!");
            }
        }while (subChoice!=2);
    }

    //3.1 display all vehicle types
    public static void displayVehicles(){
        System.out.println("\n--------------------VEHICLE TYPES----------------------\n");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %n","| Type |","| Capacity(kg) |","| Rate Per km(LKR) |","| Avg Speed(km/h) |","| Fuel Efficiency(km/l) |");
        for(int i=0;i<vehicleTypes.length;i++){
            System.out.printf("%-10s %-10d %-10d %-10d %-10d",vehicleTypes[i],vehicleCapacity[i],ratePerKm[i],avgSpeed[i],fuelEfficiency[i]);
            System.out.println();
        }

    }
    //4.0 delivery request handling
    public static void deliveryHandling() {

        int subChoice;
        do {
            System.out.println("\n\t\t\t4)DELIVERY REQUEST HANDLING");
            System.out.println("\t\n------------------ MENU-------------------");
            System.out.println("1.Display Distance Table");
            System.out.println("2.Request A Delivery");
            System.out.println("3.Back to Main Menu");

            System.out.println("\n Enter Your Choice: ");
            subChoice=sc.nextInt();

            switch(subChoice) {
                case 1:
                    displayCity();
                    break;
                case 2:
                    deliveryRequest();
                    break;
                case 3:
                    System.out.println("Returning Back To The Main Menu...");
                default:
                    System.out.println("Invalid Choice!!!");
            }

        }while(subChoice!=3);

        //4.2.handling a delivery request
    }
    public static void deliveryRequest(){
        System.out.println("Enter The Index Of The Starting City: ");
        int startingIndex=sc.nextInt()-1;

        System.out.println("Enter The Index Of The Destination City: ");
        int destinationIndex=sc.nextInt()-1;

        if(startingIndex==destinationIndex){
            System.out.println("Starting City and Destination City Can Not Be The Same");
        }
        if(startingIndex>=cityCount||destinationIndex>=cityCount){
            System.out.println("Invalid Choice!!!Check Again...");
        }

        System.out.println("Enter The Weight Of The Package(Kg): ");
        double weight=sc.nextDouble();

        displayVehicles();
        System.out.println("Select The Vehicle Type From The Table(1=Van, 2=Truck, 3=Lorry): ");
        int vehicleIndex=sc.nextInt()-1;

        if(vehicleIndex>=vehicleTypes.length){
            System.out.println("Invalid Choice!!!Check Again...");
            return;
        }

        //4.2.1.weighth validating
        if(weight>vehicleCapacity[vehicleIndex]){
            System.out.println("Weight Can Not Exceed The Capacity!!!");
        }

        //4.2.2.distance
        int distance=cityDistance[startingIndex][destinationIndex];

        //4.2.3. confirming the customer that the order request has accepted
        System.out.println("\n Your Delivery Requested Was Successful");
        System.out.println("From: "+cities[startingIndex]);
        System.out.println("To: "+cities[destinationIndex]);
        System.out.println("Vehicle Type: "+vehicleTypes[vehicleIndex]);
        System.out.println("Total Distance(km) :"+distance+" km ");
        System.out.println("Weight Of The Package(Kg): "+weight+" kg");

        System.out.println("\n Make Sure That All The Details Of Your Delivery Is Correct...");

        //calling the delivery cost calculating method
        calculateDeliveryCost(startingIndex,destinationIndex,weight,vehicleIndex);


    }
    //5.0 Cost,Time,Fuel Calculations
    public static void calculateDeliveryCost(int startingIndex,int destinationIndex,double weight,int vehicleIndex){
        int D=cityDistance[startingIndex][destinationIndex];    //Distance D (from distance matrix)-km
        double fuelPrice=310.0;  //LKR per litere
        int R=ratePerKm[vehicleIndex];   //Rate per km R (from vehicle type)-LKR
        int S=avgSpeed[vehicleIndex];   //average speed-(km/h)
        int E=fuelEfficiency[vehicleIndex];  //fuel efficiency(km/l)

        //calculations
        double cost = D * R * (1 + (weight / 10000.0));
        double time = (double) D / S;
        double fuelUsed = (double) D / E;
        double fuelCost = fuelUsed * fuelPrice;
        double totalCost = cost + fuelCost;
        double profit = cost * 0.25;
        double customerCharge = totalCost + profit;

        //Displaying the  Results to the customer
        System.out.println("======================================================");
        System.out.println("\t\tDELIVERY COST ESTIMATION\n");
        System.out.println("------------------------------------------------------\n");

        System.out.println("From "+cities[startingIndex]);
        System.out.println("To :"+cities[destinationIndex]);
        System.out.println("Distance "+D+" km");
        System.out.println("Selected Vehicle: "+vehicleTypes[vehicleIndex]);
        System.out.println("Weight : "+weight+" km");
        System.out.println("------------------------------------------------------\n");

        System.out.printf("Base Cost: %.3f LKR%n ",cost);
        System.out.printf("Fuel Used: %.3f L%n",fuelUsed);
        System.out.printf("Fuel Cost: %.3f LKR%n",fuelCost);
        System.out.printf("Operational Cost: %.3f LKR%n",totalCost);
        System.out.printf("Profit: %.3f LKR%n",profit);
        System.out.printf("Customer Charge: %.3f LKR%n",customerCharge);
        System.out.printf("Estimated Time: %.2f hourse%n",time);
        System.out.println("======================================================\n");

    }
}



