import java.util.Scanner;
import java.io.*;   //.io package used in programs enabling it to interact with external data resources such as files


public class Main {

    static Scanner sc = new Scanner(System.in);

    static int cityCount = 0;

    static String[] cities = new String[30];

    static int[][] cityDistance = new int[30][30];

    static String[] vehicleTypes = {"Van", "Truck", "Lorry"};
    static int[] vehicleCapacity = {1000, 5000, 10000};
    static int[] ratePerKm = {30, 40, 80};  //LKR
    static int[] avgSpeed = {60, 50, 45};  //(km/h)
    static int[] fuelEfficiency = {12, 6, 4}; //(km/l)

    //for delivery records
    static String[] deliveryFrom = new String[50];
    static String[] deliveryTo = new String[50];
    static String[] deliveryVehicles = new String[50];
    static int[] deliveryDistance = new int[50];
    static double[] deliveryWeight = new double[50];
    static double[] deliveryCost = new double[50];
    static double[] deliveryProfit = new double[50];
    static double[] deliveryCharge = new double[50];
    static double[] deliveryTime = new double[50];
    static int deliveryCount;


    public static void main(String[] args) {

        loadDataFromFile();

        //System.out.println("Hello from Logistics Management System!"); this was just used as the 1st commit

        System.out.printf("======================================================%n");
        System.out.printf("LOGISTIC MANAGEMENT SYSTEM%n");
        System.out.printf("======================================================%n");
        System.out.println("------------------------MAIN MENU-----------------------------");

        int choice;
        //creating the  main menu

        do {
            System.out.println("1) City Management");
            System.out.println("2)Distance Management");
            System.out.println("3)Vehicle Management");
            System.out.println("4)Delivery Request Handling");
            System.out.println("5)Calculations(Cost,Fuel,time)");
            System.out.println("6)View Delivery History");
            System.out.println("7)Summary Report");
            System.out.println("8)Exit");
            System.out.println();

            System.out.println("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
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
                    displayCity();

                    System.out.println("Enter Starting City Index: ");
                    int s = sc.nextInt() - 1;

                    System.out.println("Enter the Index Of The destination: ");
                    int d = sc.nextInt() - 1;

                    System.out.println("Enter Package Weight(kg): ");
                    double w = sc.nextDouble();

                    displayVehicles();
                    System.out.println("Select The Vehicle Type: ");
                    int v = sc.nextInt();

                    calculateDeliveryCost(s, d, w, v);

                    break;

                case 6:
                    savedDeliveries();
                    break;

                case 7:
                    summaryReport();
                    break;

                case 8:
                    fileHandlingSection();

                    System.out.println("\n==============================================");
                    System.out.println("Exiting..................");
                    System.out.println(" Thank you for using the Logistics Management System!");
                    System.out.println(" All data saved successfully. Goodbye....");
                    System.out.println("==============================================\n");
                    break;

                default:
                    System.out.println("Invalid input!!!Please Try Again...");
            }
            fileHandlingSection();

        } while (choice != 8);
    }

    //1.0
    public static void cityManagement() {

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
        } while (subChoice1 != 5);
    }

    //1.1 Displaying all cities
    public static void displayCity() {
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
    public static void addCity() {

        if (cityCount >= cities.length) {
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
    public static void renameCity() {
        displayCity();
        System.out.println();

        System.out.println("Enter the index number of the city you want to rename : ");
        int cityIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (cityIndex >= cityCount) {
            System.out.println("Entered City Index Is Incorrect!!! Check Again..");
            return;
        }
        System.out.println("Enter New Name For The City: ");
        String newCityName = sc.nextLine();
        cities[cityIndex] = newCityName;
        System.out.println("City Renamed Successfully!!");

    }

    //1.3.Removing an existing city
    public static void removeCity() {
        displayCity();
        System.out.println();

        System.out.println("Enter The Index Of The City You Want To Remove : ");
        int cityIndex = sc.nextInt() - 1;
        sc.nextLine();

        if (cityIndex >= cityCount) {
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
    public static void enterOrEditDistance() {
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
    public static void distanceTable() {

        if (cityCount == 0) {
            System.out.println("No Cities Are Available...");
        }
        System.out.printf("%nDISTANCE TABLE%n");
        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-10s", cities[i]);
        }
        System.out.println();

        for (int i = 0; i < cityCount; i++) {
            System.out.printf("%-8s", cities[i]);
            for (int j = 0; j < cityCount; j++) {
                System.out.printf("%-10d", cityDistance[i][j]);
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
            subChoice = sc.nextInt();
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
        } while (subChoice != 2);
    }

    //3.1 display all vehicle types
    public static void displayVehicles() {
        System.out.println("\n--------------------VEHICLE TYPES----------------------\n");
        System.out.printf("%-15s %-15s %-15s %-15s %-15s %n", "| Type |", "| Capacity(kg) |", "| Rate Per km(LKR) |", "| Avg Speed(km/h) |", "| Fuel Efficiency(km/l) |");
        for (int i = 0; i < vehicleTypes.length; i++) {
            System.out.printf("%-10s %-10d %-10d %-10d %-10d", vehicleTypes[i], vehicleCapacity[i], ratePerKm[i], avgSpeed[i], fuelEfficiency[i]);
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
            subChoice = sc.nextInt();

            switch (subChoice) {
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

        } while (subChoice != 3);

        //4.2.handling a delivery request
    }

    public static void deliveryRequest() {
        System.out.println("Enter The Index Of The Starting City: ");
        int startingIndex = sc.nextInt() - 1;

        System.out.println("Enter The Index Of The Destination City: ");
        int destinationIndex = sc.nextInt() - 1;

        if (startingIndex == destinationIndex) {
            System.out.println("Starting City and Destination City Can Not Be The Same");
        }
        if (startingIndex >= cityCount || destinationIndex >= cityCount) {
            System.out.println("Invalid Choice!!!Check Again...");
        }

        System.out.println("Enter The Weight Of The Package(Kg): ");
        double weight = sc.nextDouble();

        displayVehicles();
        System.out.println("Select The Vehicle Type From The Table(1=Van, 2=Truck, 3=Lorry): ");
        int vehicleIndex = sc.nextInt() - 1;

        if (vehicleIndex >= vehicleTypes.length) {
            System.out.println("Invalid Choice!!!Check Again...");
            return;
        }

        //4.2.1.weighth validating
        if (weight > vehicleCapacity[vehicleIndex]) {
            System.out.println("Weight Can Not Exceed The Capacity!!!");
        }

        //4.2.2.distance
        int distance = cityDistance[startingIndex][destinationIndex];

        //4.2.3. confirming the customer that the order request has accepted
        System.out.println("\n Your Delivery Requested Was Successful");
        System.out.println("From: " + cities[startingIndex]);
        System.out.println("To: " + cities[destinationIndex]);
        System.out.println("Vehicle Type: " + vehicleTypes[vehicleIndex]);
        System.out.println("Total Distance(km) :" + distance + " km ");
        System.out.println("Weight Of The Package(Kg): " + weight + " kg");

        System.out.println("\n Make Sure That All The Details Of Your Delivery Is Correct...");

        //calling the delivery cost calculating method
        calculateDeliveryCost(startingIndex, destinationIndex, weight, vehicleIndex);


    }

    //5.0 Cost,Time,Fuel Calculations
    public static void calculateDeliveryCost(int startingIndex, int destinationIndex, double weight, int vehicleIndex) {
        int D = cityDistance[startingIndex][destinationIndex];    //Distance D (from distance matrix)-km
        double fuelPrice = 310.0;  //LKR per litere
        int R = ratePerKm[vehicleIndex];   //Rate per km R (from vehicle type)-LKR
        int S = avgSpeed[vehicleIndex];   //average speed-(km/h)
        int E = fuelEfficiency[vehicleIndex];  //fuel efficiency(km/l)

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

        System.out.println("From " + cities[startingIndex]);
        System.out.println("To :" + cities[destinationIndex]);
        System.out.println("Distance " + D + " km");
        System.out.println("Selected Vehicle: " + vehicleTypes[vehicleIndex]);
        System.out.println("Weight : " + weight + " km");
        System.out.println("------------------------------------------------------\n");

        System.out.printf("Base Cost: %.3f LKR%n ", cost);
        System.out.printf("Fuel Used: %.3f L%n", fuelUsed);
        System.out.printf("Fuel Cost: %.3f LKR%n", fuelCost);
        System.out.printf("Operational Cost: %.3f LKR%n", totalCost);
        System.out.printf("Profit: %.3f LKR%n", profit);
        System.out.printf("Customer Charge: %.3f LKR%n", customerCharge);
        System.out.printf("Estimated Time: %.2f hourse%n", time);
        System.out.println("======================================================\n");

        //saving delivery records
        if (deliveryCount < deliveryFrom.length) {
            deliveryFrom[deliveryCount] = cities[startingIndex];
            deliveryTo[deliveryCount] = cities[destinationIndex];
            deliveryVehicles[deliveryCount] = vehicleTypes[vehicleIndex];
            deliveryDistance[deliveryCount] = D;
            deliveryWeight[deliveryCount] = weight;
            deliveryCost[deliveryCount] = totalCost;
            deliveryProfit[deliveryCount] = profit;
            deliveryCharge[deliveryCount] = customerCharge;
            deliveryTime[deliveryCount] = time;
            deliveryCount++;

            System.out.println("Your Delivery Record Saved Successfully... ");
        } else {
            System.out.println("Delivery Record Limit Reached!!!Can not Add More...");
        }
    }

    //6.0 viewing saved reports
    public static void savedDeliveries() {
        if (deliveryCount == 0) {
            System.out.println("No Deliveries have Been Recorded Yet...");
            return;
        }
        System.out.println("\n------------------------ALL DELIVERIES-----------------------------\n");
        for (int i = 0; i < deliveryCount; i++) {
            System.out.println("Delivery: " + (i + 1));
            System.out.println("From: " + deliveryFrom[i]);
            System.out.println("To :" + deliveryTo[i]);
            System.out.println("Delivery Vehicle: " + vehicleTypes[i]);
            System.out.println("Weight: " + deliveryWeight[i] + "kg");
            System.out.println("Distance: " + deliveryDistance[i] + " km");
        }
        System.out.println("Total Deliveries: " + deliveryCount);
    }

    //7.0 summary
    public static void summaryReport() {
        int subChoice;
        do {
            System.out.println("\n\t\t\t7)REPORTS");
            System.out.println("\t\n------------------ MENU-------------------");
            System.out.println("1.View Summary");
            System.out.println("2.Back To Main Menu\n");

            System.out.println("Enter Your choice: ");
            subChoice = sc.nextInt();
            sc.nextLine();

            switch (subChoice) {
                case 1:
                    displaySummaryReports();
                    break;
                case 2:
                    System.out.println("REtuning To The Main Menu...");
                    break;
                default:
                    System.out.println("Invalid Choice!!!");
            }

        } while (subChoice != 2);
    }

    //7.1. displaying summary reports
    public static void displaySummaryReports() {
        if (deliveryCount == 0) {
            System.out.println("\n No Deliveries Recorded Yet...");
            return;
        }
        double totalDistance = 0;
        double totalProfit = 0;
        double totalRevenue = 0;
        double totalTime = 0;

        int longestRoute = deliveryDistance[0];
        int shortestRoute = deliveryDistance[0];

        for (int i = 0; i < deliveryCount; i++) {
            totalDistance += deliveryDistance[i];
            totalProfit += deliveryProfit[i];
            totalRevenue += deliveryCharge[i];
            totalTime += deliveryTime[i];

            if (deliveryDistance[i] > longestRoute) {
                longestRoute = deliveryDistance[i];
            }

            if (deliveryDistance[i] < shortestRoute) {
                shortestRoute = deliveryDistance[i];
            }

            double averageTime = totalTime / deliveryCount;

            System.out.println("\n------------------------SUMMARY REPORTS-----------------------------\n");
            System.out.printf("%-20s:%d%n", "Total Deliveries", deliveryCount);
            System.out.printf("%-20s: %.2f km%n", "Total Distance", totalDistance);
            System.out.printf("%-20s: %.2f hours%n", "Average Time", averageTime);
            System.out.printf("%-20s: %.2f LKR%n", "Total Profit", totalProfit);
            System.out.printf("%-20s: %.2f LKR%n", "Total Revenue", totalRevenue);
            System.out.printf("%-20s: %d km%n", "Longest Delivery Route", longestRoute);
            System.out.printf("%-20s: %d km%n", "Shortest Delivery Route", shortestRoute);

            System.out.println("\n------------------------end of the report-----------------------------\n");
        }
    }
    //file handling
    public static void fileHandlingSection(){
        try{
            FileWriter cityWriter=new FileWriter("cities.txt");
            for(int i=0;i<cityCount;i++){
                cityWriter.write(cities[i]);
                System.out.println();
            }
            cityWriter.close();

            FileWriter distanceWriter=new FileWriter("City Distance.txt");
            for(int i=0;i<cityCount;i++){
                for(int j=0;j<cityCount;j++){
                    distanceWriter.write(cityDistance[i][j]+(j==cityCount-1?"": " "));
                }
                distanceWriter.write("\n");
            }
            distanceWriter.close();

            FileWriter deliveryWriter=new FileWriter("Deliveries.txt");
            for(int i=0;i<cityCount;i++){
                deliveryWriter.write(
                        deliveryFrom[i]+"+"+ deliveryTo[i]+"+"+deliveryVehicles[i]+"+"+deliveryDistance[i]+"+"+deliveryWeight[i]+"+"+deliveryCost[i]+"+"+deliveryProfit[i]+"+"+deliveryCharge[i]+"+"+deliveryTime[i]+"\n");
            }
            deliveryWriter.close();

            System.out.println("Data Saved Successfully!!!");
        }catch (IOException e){
            System.out.println("error saving data "+e.getMessage());
        }
    }
//loading data from files
         public static void loadDataFromFile() {
        try {
        // --- Load Cities ---
        File cityFile = new File("cities.txt");
        if (cityFile.exists()) {
            Scanner cityReader = new Scanner(cityFile);
            cityCount = 0;
            while (cityReader.hasNextLine()) {
                cities[cityCount++] = cityReader.nextLine();
            }
            cityReader.close();
        }

        // --- Load Distances ---
        File distanceFile = new File("distances.txt");
        if (distanceFile.exists()) {
            Scanner distanceReader = new Scanner(distanceFile);
            for (int i = 0; i < cityCount; i++) {
                for (int j = 0; j < cityCount; j++) {
                    if (distanceReader.hasNextInt()) {
                        cityDistance[i][j] = distanceReader.nextInt();
                    }
                }
            }
            distanceReader.close();
        }

        // --- Load Deliveries ---
        File deliveryFile = new File("deliveries.txt");
        if (deliveryFile.exists()) {
            Scanner deliveryReader = new Scanner(deliveryFile);
            deliveryCount = 0;
            while (deliveryReader.hasNextLine()) {
                String line = deliveryReader.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 9) {
                    deliveryFrom[deliveryCount] = parts[0];
                    deliveryTo[deliveryCount] = parts[1];
                    vehicleTypes[deliveryCount] = parts[2];
                    deliveryDistance[deliveryCount] = Integer.parseInt(parts[3]);
                    deliveryWeight[deliveryCount] = Integer.parseInt(parts[4]);
                    deliveryCost[deliveryCount] = Double.parseDouble(parts[5]);
                    deliveryProfit[deliveryCount] = Double.parseDouble(parts[6]);
                    deliveryCharge[deliveryCount] = Double.parseDouble(parts[7]);
                    deliveryTime[deliveryCount] = Double.parseDouble(parts[8]);
                    deliveryCount++;
                }
            }
            deliveryReader.close();
        }

        System.out.println("\nData loaded successfully from files to the system.");

    } catch (IOException e) {
        System.out.println(" Error loading data: " + e.getMessage());
    }
}


}