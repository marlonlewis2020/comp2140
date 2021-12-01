import Authentication.Authentication;

class test {

    /**
     * Authentication Tests
     */
    private Authentication authenticationTests(){
        try {
            //testing login feature
            Authentication auth = new Authentication();
            System.out.println("[AUTHENTICATED USER] role: "+auth.authenticate("mlewis","password123"));

            Authentication none = new Authentication(); 
            System.out.println("[ANON USER] role: "+none.authenticate("user", "pw"));
            
            // displating user's menu options
            for(String s : auth.getUserMenu()){
                System.out.println("AUTHENTICATED USER'S] Menu option: "+s);
            }

            // CHECKING AUTHENTICATION STATUS
            System.out.println("[AUTHENTICATED] MESSAGE: " + auth.getAuth_message());
            System.out.println("[NOT AUTHENTICATED] MESSAGE: " + none.getAuth_message()); 

            // SIGNING OUT AND GETTING AUTHENTICATION STATUS MESSAGE
            auth.authenticate("", "");
            System.out.println("auth signed out: " + auth.getAuth_message());

            // SIGNING IN AND GETTING AUTHENTICATION
            none.authenticate("mlewis","password123");
            System.out.println("'none' authenticated: " + none.getAuth_message());
            return none;
            
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("\n!!!!!AUTHENTICATION EXCEPTION ENCOUNTERED!!!!!\n");
            return null;
        }
    }

    /**        
    * Stock class test            
    */
    private String stockTests(){
        try {
            Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
            gibbs.createStock();
            
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits"));
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 
            Stock.updateStock('+',499, "Gibbits"); 
            Stock.updateStock('+',499, "Panther"); 
            Stock.updateStock('-',500, "Gibbits");
            Stock.updateStock('-',500, "Panther");
            System.out.println("Gibbits Quantity = "+ Stock.getQuantity("Gibbits")); 
            System.out.println("Panther Quantity = "+ Stock.getQuantity("Panther")); 

            // VIEWING ALL STOCK
            System.out.println("\n\n[ALL STOCK]");
            for(Stock s:Stock.viewStock(1)){
                System.out.println(s.toString());
            }

            // CREATING LOW STOCK ITEM
            Stock not = new Stock (StockType.Beads,"iron", 150,200);
            not.createStock();

            Stock white = new Stock (StockType.Beads,"white", 150,50);
            white.createStock();

            Stock black = new Stock (StockType.Beads,"black", 150,200);
            black.createStock();

            // VIEWING LOW STOCK ITEMS
            System.out.println("\n\n[LOW STOCK]");
            for(Stock s:Stock.viewStock(0)){
                System.out.println(s.toString());
            }

            System.out.println("\n\n[ADDING PANTHER BEAD]");
            Stock sec = new Stock(StockType.Beads,"Panther", 9150,50);
            ;
            System.out.println("\n\n[TOSTRING() FROM CLASS TEST]: "+sec.toString());
            sec.createStock();
            System.out.println("[VIEWITEM(STOCKNAME) TEST]" + Stock.viewItem(sec.getStockName()).toString());
            return "Stock Tests completed successfully; \n";
        } catch (Exception e) {
            // System.out.println(e.getMessage()); 
            System.out.println("\n!!!!!STOCK EXCEPTION ENCOUNTERED!!!!!\n");
            return "Stock Tests failed; \n";
        }
    }

    /**
     * Bracelet Tests
     */
    private String braceletTests(){
        try{
            Bracelet.populate();
            Bracelet b; 
            b = new Bracelet("b1", 3.00, "yellow-2;green-4", "yellow-5;green-8", "yellow-10;green-16", "diatta");
            //System.out.println(b.getName());
            //System.out.println(b.getBracelets());
            //System.out.println(b.getID());
            //System.out.println(b.getCollection());
            //System.out.println(Bracelet.getBraceletIndex("b1"));
            // Bracelet b = Bracelet.searchByName("b1");
            Bracelet.addToArray(b);
            b.addToDatabase();
            System.out.println(b.getName());
            System.out.println(b.getSmallBeadQty());
            System.out.println(b.getLgBeadQty());
            System.out.println(b.getMedBeadQty());
            //Bracelet.deleteBracelet("b1");
            return "Bracelet Tests completed successfully; \n";
        }
        catch(Exception e){
            System.out.println("\n!!!!!BRACELET EXCEPTION ENCOUNTERED!!!!!\n");
            return "Bracelet Tests failed; \n";
        }
    }

    private String orderTests(){
        try {
            // CREATING ORDER 
            Order fdr = new Order("8764385612", "Marlon Lewis", "2,3,4", "b1,b1,b1", "Half Way Tree"); // Requires a public static getCost(String braceletName) method from Bracelet to test and run        System.out.println("[Order object created]");
            
            // ADDING ORDER TO THE DATABASE
            System.out.println("[ORDER STATUS] Added to Database: "+fdr.addToDatabase());

            return "Order Tests completed successfully; \n";
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("\n!!!!!ORDER EXCEPTION ENCOUNTERED!!!!!\n");
            return "Order Tests failed; \n";
        }
    }

    private String userTests(){
        try{
            // Adding Users
            User callay = new User ("Calzy", "beautiful", Role.ADMIN);
            callay.addUser();
            System.out.println("[USER ADDED] "+callay.getUserName().toUpperCase()+" ADDED TO DATABASE");

            User marlon = new User ("Marly", "blessed", Role.ADMIN);
            marlon.addUser();
            System.out.println("[USER ADDED] "+marlon.getUserName().toUpperCase()+" ADDED TO DATABASE");

            return "User Tests completed successfully; \n";
        }
        catch(Exception e){
            System.out.println("\n!!!!!ORDER EXCEPTION ENCOUNTERED!!!!!\n");
            return "User Tests failed; \n";
        }
    }

    private String confirmations(Authentication a,String b, String c, String d, String e, String f){
        String x = "\n\nAuthentication Tests failed; \n";
        if(a!=null){x = "\n\nAuthentication Tests completed successfully; \n";}
        a.logout();
        return x+b+c+d+e+f;
    }
    
    public static void main(String[] args) {
        /**
         * -----  COMMENT OUT TEST METHODS YOU DO NOT WANT TO RUN!!!!!              -----
         * -----  ENSURE AUTHENTICATIONTESTS() ALWAYS RUNS BEFORE YOUR CODE!!!!!    -----
         * System.out.println("password123".hashCode());
        */

        /*        * CREATING TEST OBJECT        */
        test t = new test();
        
        /*        * RUNNING TESTS               */
        Authentication a = t.authenticationTests(); //Authentication
        String b = t.stockTests(); //Stock
        String c = t.braceletTests(); //Bracelet
        String d = t.orderTests(); //Order//"[ORDER TEST PENDING]; \n"; //Order
        String e = "[CUSTOMER TEST PENDING]; \n"; //t.customerTests(); //Customer
        String f = t.userTests(); //Stock//"[USER TEST PENDING]; \n"; //User

        /*        * END OF TESTS. CLOSING       */       
        System.out.println(t.confirmations(a,b,c,d,e,f));
        // PRINTING CONFIRMATIONS
    }
}