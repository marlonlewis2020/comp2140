import Authentication.Authentication;

class test {
    public static void main(String[] args) {
        // System.out.println("password123".hashCode());
        /**
         * Authentication Tests
         */
        Authentication auth = new Authentication();
        //Authentication none = new Authentication();
        auth.authenticate("mlewis","password123");
        // //none.authenticate("user", "pw");

        Customer mercedes = new Customer("8768164681", "Mercedes", "Sedecrem");
        Customer marlon = new Customer("8764385612", "Marlon", "Sovereign");
        Customer gabriel = new Customer("8768964681", "Gabriel", "Portmore");

        mercedes.addToDatabase();
        marlon.addToDatabase();
        gabriel.addToDatabase();

        auth.logout();




}
}