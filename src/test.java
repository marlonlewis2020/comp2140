import Authentication.Authentication;
class test {
    
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        Authentication auth = new Authentication();
        Authentication none = new Authentication();
        auth.authenticate("mlewis","1403730359");
        none.authenticate("user", "pw");
        
        //testing login user's menu options
        for(String s : auth.getUserMenu()){
            System.out.println("Authenticated User's Menu item: "+s);
        }
        //testing unauthenticated user's menu options
        for(String s : none.getUserMenu()){
            System.out.println("Logged Out User's Menu item: "+s);
        }

        //confirming login/logout changes to state
        System.out.println("'auth' authenticated: "+auth.getAuth_message());
        System.out.println("'none' failed authentication: "+none.getAuth_message());
        auth.authenticate("", "");
        System.out.println("auth signed out: "+auth.getAuth_message());
        none.authenticate("mlewis","1403730359");
        System.out.println("'none' authenticated: "+none.getAuth_message());

        

        /**
        * Stock class test
        */
        //Stock gibbs = new Stock (StockType.Beads,"Gibbits", 150,50);
        //gibbs.createStock();*/
        /*
        int quantity = Stock.getQuantity("Gibbits");
        System.out.println("Quantity = "+ quantity);
        Stock.updateStock('+',12, "Gibbits");
        // Stock.updateStock('-',500, "Gibbits");
        quantity = Stock.getQuantity("Gibbits");
        System.out.println("Quantity = "+ quantity);*/


        /**
        * Bracelet Tests
        */

       /* Bracelet.populate();
        //Bracelet b = new Bracelet("b1", 3.00, "yellow-2;green-4", "yellow-5;green-8", "yellow-10;green-16", "diatta");
        //Bracelet.addToArray(b);
        //b.addToDatabase();
        //System.out.println(b.getName());
        //System.out.println(b.getBracelets());
        //System.out.println(b.getID());
        //System.out.println(b.getCollection());
        //System.out.println(Bracelet.getBraceletIndex("b1"));
        Bracelet b = Bracelet.searchByName("b1");
        System.out.println(b.getName());
        System.out.println(b.getSmallBeadQty());
        System.out.println(b.getLgBeadQty());
        System.out.println(b.getMedBeadQty());
        //Bracelet.deleteBracelet("b1");*/

        /*user test*/
       
        User user = new User ("Callay", "lovelyyy","Admin");
        user.addUser();

}

}