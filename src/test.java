import Authentication.Authentication;

class test {
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        Authentication auth = new Authentication();
        //Authentication none = new Authentication();
        auth.authenticate("mlewis","1403730359");
        // //none.authenticate("user", "pw");
        
        // //testing login user's menu options
        // for(String s : auth.getUserMenu()){
        //     System.out.println("Authenticated User's Menu item: "+s);
        // }
        // //testing unauthenticated user's menu options
        // for(String s : none.getUserMenu()){
        //     System.out.println("Logged Out User's Menu item: "+s);
        // }

        // //confirming login/logout changes to state
        // System.out.println("'auth' authenticated: "+auth.getAuth_message());
        // System.out.println("'none' failed authentication: "+none.getAuth_message());
        // //auth.authenticate("", "");
        //System.out.println("auth signed out: "+auth.getAuth_message());
        //none.authenticate("mlewis","1403730359");
        //System.out.println("'none' authenticated: "+none.getAuth_message());

            // VIEWING ALL STOCK
            System.out.println("\n\n[ALL STOCK]");
            for(Stock s:Stock.viewStock(1)){
                System.out.println(s.toString());
            }

        /**
        * Stock class test
        */
        //Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
        //gibbs.createStock();
        // int quantity = Stock.getQuantity("Gibbits");
        // System.out.println("Quantity = "+ quantity);
        // Stock.updateStock('+',12, "Gibbits");
        // // Stock.updateStock('-',500, "Gibbits");
        // quantity = Stock.getQuantity("Gibbits");
        // System.out.println("Quantity = "+ quantity);

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
            // System.out.println(e.getMessage()); 
            System.out.println("!!!!!BRACELET EXCEPTION ENCOUNTERED!!!!!");
            return "Bracelet Tests failed; \n";
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

        Bracelet.populate();
        //Bracelet b = new Bracelet("b1", 3.00, "yellow-2;green-4", "yellow-5;green-8", "yellow-10;green-16", "diatta");
        //Bracelet.addToArray(b);
        //b.addToDatabase();
        //System.out.println(b.getName());
        //System.out.println(b.getBracelets());
        //System.out.println(b.getID());
        //System.out.println(b.getCollection());
        //System.out.println(Bracelet.getBraceletIndex("b1"));
        // Bracelet b = Bracelet.searchByName("b1");
        // System.out.println(b.getName());
        // System.out.println(b.getSmallBeadQty());
        // System.out.println(b.getLgBeadQty());
        // System.out.println(b.getMedBeadQty());
        //Bracelet.deleteBracelet("b1");


        /**
         * Customer Class
         */
        Customer mercedes = new Customer(8768164681L, "Mercedes", "Sedecrem");
        Customer marlon = new Customer(8764385612L, "Marlon", "Sovereign");
        Customer gabriel = new Customer(8768964681L, "Gabriel", "Portmore");

        mercedes.addToDatabase();
        marlon.addToDatabase();
        gabriel.addToDatabase();

        




}
}


