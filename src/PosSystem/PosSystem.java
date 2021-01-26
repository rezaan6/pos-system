/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PosSystem;
import java.util.*;
/**
 *
 * @author User
 */
public class PosSystem {
   
    //Shared arrayList variables to cashiers, admins and products
    private static final ArrayList<String> cashieruserlist=new ArrayList<String>();
    private static final ArrayList<String> adminuserlist=new ArrayList<String>();
    private static final ArrayList<String> prodlist=new ArrayList<String>();
    private static final ArrayList<String> salesItems=new ArrayList<String>();
    
    //Concatanate to each product at beginning and stored in prodlist 
    static String rupee = "Rs: ";
    static String qty = "Quantity: ";
    static String item = "Item: ";
    static String newline = "\n";
    
    static Double DailSummaryValue=0.0;
       
    public static void main(String[] args) {
        
        //Adding default product to prodlist by concatanating above variables
        prodlist.add(item + "Bottles");
        prodlist.add(qty + "10");
        prodlist.add(rupee + "60" + newline);
        
        prodlist.add(item + "Apple");
        prodlist.add(qty + "20");
        prodlist.add(rupee + "40" + newline);
        
        prodlist.add(item + "Eggs");
        prodlist.add(qty + "15");
        prodlist.add(rupee + "40" + newline);
        
        
        //Adding default cashiers to cashierlist and admins to adminsuserlist
       
        adminuserlist.add("admin1");
        adminuserlist.add("admin2");
        
         cashieruserlist.add("A");
        cashieruserlist.add("B");
        cashieruserlist.add("C");
        
        PosSystem.getUserDetails();
               
    }
    
    public static void getUserDetails(){
        
        String user_name = null;
        int password=0;
        
        do{
        
            //catch exception
            try{
                //Get username and password using scanner class and storing them in variables
                Scanner sc = new Scanner (System.in);
                System.out.print("\n Enter user name :");
                user_name = sc.nextLine();
                System.out.print("Enter password :");
                password = sc.nextInt();
            }
            catch(Exception ex ){
                
            }
         //check user existing the system or not    
        DailSummaryValue= PosSystem.validate_login(user_name, password,DailSummaryValue);
        
        }while(DailSummaryValue>=0);
    
    }
    //check user existing and returns total sales
    public static Double  validate_login(String user_name,int password,Double DailSummaryValueue){
        
        Double dailysummary = DailSummaryValueue;
        int adminOptions=0;
        String DailSummaryValue;
        String cvalue;
        Double pastSaleValue=0.0;
        
        //check username and password contain cashierlist
        if(cashieruserlist.contains(user_name) && (password == 0000)){
            
            do{
               
                PosSystem.print_items(); //print productlist 
                DailSummaryValue = PosSystem.Sales(prodlist,pastSaleValue);//Call sales method and store DailSummaryValue
               
                cvalue=DailSummaryValue.substring(0,1);
                
                pastSaleValue=Double.parseDouble(DailSummaryValue.substring(1));
                
            }while(cvalue.equalsIgnoreCase("c"));
            
 
            
            return Double.parseDouble(DailSummaryValue.substring(1));
            
            
        }
        //check user name and password cotain adminuserlist
        else if(adminuserlist.contains(user_name) && (password == 123)){
            
            do{
                
            
                System.out.println(newline + "*********************Welcome POS System*********************");
                System.out.println("1.Add Items");
                System.out.println("2.Daily Summary"); //print admins' options
                System.out.println("3.Add More Users");
                System.out.println("4.Remove Users: ");
                System.out.println("5.Product list");
                System.out.println("6.cashieruser list");
                System.out.println("7.Log out as admin");
                
                Scanner sc = new Scanner (System.in);//print enter your options and storing it in variable
                try{
                    System.out.print("Please enter your options: ");
                    adminOptions = sc.nextInt();
                }
                catch(Exception ex){
                    
                }
                 // Admin choose first option and add new items to arraylist call the productlist
                 if(adminOptions != 1)if(adminOptions == 2){
                     
                     System.out.println(newline);
                     System.out.println("************************************************");
                     salesItems.forEach((val) -> {
                         System.out.println(val);
                    });
                     
                     System.out.println("\nDaily Summary = " + dailysummary);
                     System.out.println("************************************************");
                 }
                 //Admin choose third option and add users(Cashiers,Admins)
                 else if(adminOptions == 3){
                     String userType;
                     int numberOfCashiers;
                     String cashiersName;
                     int numberOfAdmins;
                     String adminsName;
                     
                     sc.nextLine();//skip to programm
                     
                     System.out.print("Do you want to add cashier or admin(C/A)? ");
                     
                     userType = sc.nextLine();
                     if(userType.equalsIgnoreCase("C")){//Check whether user type equal "c"
                         System.out.print("How many cashiers do you want to enter? ");
                         numberOfCashiers = sc.nextInt();
                         
                         //looping admin input and add arraylist call the cashierlist
                         for(int x = 1;x <=numberOfCashiers; x++){
                             Scanner ca = new Scanner(System.in);
                             System.out.print("Enter cashier name: ");
                             cashiersName = ca.nextLine();
                             cashieruserlist.add(cashiersName);
                         }
                         

                         PosSystem.cashier_list();
                     }
                     else if(userType.equalsIgnoreCase("A")){ //check whether user type equal "A"
                         Scanner admin = new Scanner(System.in);
                         System.out.print("How many admins do you want to enter? "); //get admin input and store in it variable
                         numberOfAdmins = admin.nextInt();
                        
                         admin.nextLine(); //skip to programm
                         
                         //looping admin input and add arraylist call the adminuserlist
                         for(int x = 1; x <= numberOfAdmins; x++){
                             System.out.print("Enter admin name: ");
                             adminsName = admin.nextLine();
                             adminuserlist.add(adminsName);
                             
                         }
                         PosSystem.admin_list();
                     }
                 }
                 //Admin choose fourth option and remove users(Cashiers,Admins)
                 else if(adminOptions == 4){ 
                     String userTypes;
                     int numberOfCashiers;
                     String cashierName;
                     int numberOfAdmins;
                     String adminName;
                     
                     //get admin input and store it in their variable
                     Scanner remove = new Scanner(System.in);
                     System.out.print("Do you want to remove cashier?(y)");
                     userTypes = remove .nextLine();
                     
                     //check whether user type equal the"C" 
                     if(userTypes.equalsIgnoreCase("y")){
                         System.out.print("How many cashiers do you want to remove? ");//Get admin input and store it in variable
                         numberOfCashiers = remove.nextInt();
                         
                         PosSystem.cashier_list();
                         
                         // Get admin inputand store it in cashierName variable
                         Scanner num = new Scanner(System.in);
                         System.out.print("Enter cashier name: "  );
                         cashierName = num.nextLine();
                         
                         //loop through the array list and check whether the user imputed value
                         //is in the arraylist if it is there it will be removed from the list
                         
                         for (int i = 0; i < cashieruserlist.size(); i++) {
                             if (cashierName.equals(cashieruserlist.get(i))) {
                                 cashieruserlist.remove(i);
                                     
                             }
                         }
                         
                         PosSystem.cashier_list();
                     }
                 
                 }
                 else if(adminOptions == 5){
                     PosSystem.print_items();
                     
                 }
                 else if(adminOptions == 6){
                     PosSystem.cashier_list();
                     
                 }
                 else if(adminOptions == 7){
                     PosSystem.getUserDetails();
                 }   
                 else{
                     System.out.println("Your Options incorrect");
                 
                 }
                 //Admin chooses second option and can watch the daily summary
                 else {
                     int numberOfItems;
                     String itemNames;
                     String Quantity;
                     String price;
                     
                     sc.nextLine();// skip to programm
                     System.out.print("How many items do you want to add: ");
                     numberOfItems = sc.nextInt();// Get user input and store in numberOfItems
                     
                     sc.nextLine();
                     //looping itemnames,Quantity, unit price and add in to the prodlist (arraylist)
                     for(int x = 1;x <= numberOfItems;x++){
                         System.out.print("Please enter itemNames: ");
                         itemNames = sc.nextLine();
                         
                         System.out.print("please enter Quantity: ");
                         Quantity = sc.nextLine();
                         
                         System.out.print("Please enter unit price: ");
                         price = sc.nextLine();
                         
                         prodlist.add(item + itemNames);
                         prodlist.add(qty + Quantity);
                         prodlist.add(rupee + price + newline);
                     }
                     PosSystem.print_items();
                }
            }
            while(adminOptions < 7);
             
             
        }
        else{
        
            System.out.println("\n*****Password or Username is incorrect*****\n");
        }
        return 0.0;
        
     }
    public static void print_items(){
       
        System.out.println("******************************PRODUCT LIST******************************");
       for(String product: prodlist){
            System.out.print(newline + product + " ");
        
        }
        System.out.println("************************************************************************");
       
    }
    public static void cashier_list(){
        System.out.println("*******************cashier_list***************************");
        for(String cashier: cashieruserlist){
            System.out.println(newline + cashier + "" );
        }
        System.out.println("*******************************************************************");
    }
    
    public static void admin_list(){
        System.out.println("*********************admin_list************************************");
        for(String admin: adminuserlist){
            System.out.println(newline + admin + " ");
        
       }
        System.out.println("*******************************************************************");
    }
    
    public static String Sales(ArrayList<String> products,Double pastSaleValue){
        
        String prodname;
        int quantity;
        int price;
        double total = 0;
        String value;
        String payment;
        double userAmount;
        double creditBalance = 12000;
        String finalDecision;
        Double finalTotal;
        
        
        
        Scanner sc = new Scanner (System.in);
        
        do{
        System.out.print("Please enter item name: ");
        prodname=sc.nextLine();
        System.out.print("Please enter quatity: ");
        quantity=sc.nextInt();
        System.out.print("Please enter unit price: ");
        price = sc.nextInt();
        
        salesItems.add(item + prodname + "||" + qty + quantity + "||" + rupee + price);
       
        sc.nextLine();//used to skip a line
        total = total + price * quantity;//calculate total price
        
        System.out.println(newline);
        System.out.print("Do you need to enter more?(Y/N) ");
        value=sc.nextLine();
        System.out.println(newline);
        
        }
        while(value.equalsIgnoreCase("y"));//If user enter more items this loop will run
        
        System.out.print("Total Price = " + total + newline+newline);//If user don't enter items print total price
        
        System.out.print("How do you pay it cash/credit?:  ");
        payment = sc.nextLine();
        
        if(payment.equals("cash")){//If user pay cash print balance
            System.out.print("Please enter amount: ");
            userAmount = sc.nextInt();
            System.out.print("Balance: " + (userAmount - total)+ newline );
        }
        else if(payment.equals("credit")){
           
            
            System.out.println("Balance: " + (creditBalance - total) + newline);//If user pay credit print balance
        }
        
     
        System.out.print("\n Do you want to exit(e) or continue(c)? ");
        sc.nextLine();//skip to programm
        finalDecision = sc.nextLine();
        finalTotal=total+pastSaleValue;//Calculate total sales using previous sales values
        
        if(finalDecision.equalsIgnoreCase("e")){
            return finalDecision+Double.toString(finalTotal);//Return user enter value and final sales total by converting it to string
        }
        else{ 
          
           return finalDecision+Double.toString(finalTotal);//Return user enter value and final sales total by converting it to string 
        }
        
    }
}
