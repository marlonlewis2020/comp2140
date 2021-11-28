import Authentication.Authentication;
class test {
    public static void main(String[] args) {
        Bracelet b = new Bracelet("b1", 3.00, "yello-2;green-4", "yello-5;green-8", "yello-10;green-16", "diatta");
        Bracelet.addToArray(b);
        Bracelet.getBraceletIndex("b1");

        b.addToDatabase();
        System.out.println(b.getName());
        System.out.println(b.getBracelets());
        System.out.println(b.getID());
        System.out.println(b.getCollection());
        System.out.println(Bracelet.getBraceletIndex("b1"));
        System.out.println(Bracelet.searchByName("b1"));

        System.out.println("password123".hashCode());
        Authentication auth = new Authentication();
        Authentication none = new Authentication();
        auth.authenticate("mlewis","1403730359");
        none.authenticate("user", "pw");

        System.out.println("'auth' authenticated: "+auth.getAuth_message());
        System.out.println("'none' failed authentication: "+none.getAuth_message());
        auth.authenticate("", "");
        System.out.println("auth signed out: "+auth.getAuth_message());
        none.authenticate("mlewis","1403730359");
        System.out.println("'none' authenticated: "+none.getAuth_message());


}
}
