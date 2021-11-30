import Authentication.Authentication;
class test {
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        
        Authentication auth = new Authentication();
        auth.authenticate("mlewis","password123");

        /**
         * Customer Class
         */
        // Customer mercedes = new Customer(Long.valueOf("8768164681"), "Mercedes", "Sedecrem");
        // Customer marlon = new Customer(Long.valueOf("8764385612"), "Marlon", "Sovereign");
        // Customer gabriel = new Customer(Long.valueOf("8768964681"), "Gabriel", "Portmore");

        // mercedes.addToDatabase();
        // marlon.addToDatabase();
        // gabriel.addToDatabase();

        Order fdr = new Order("8764385612", "Marlon Lewis", "2,3,4", "rock1,rockingb,rocknroll", "Half Way Tree"); // Requires a public static getCost(String braceletName) method from Bracelet to test and run        System.out.println("[Order object created]");
        System.out.println("[ORDER STATUS] Added to Database: "+fdr.addToDatabase());
        auth.logout();

}
}


