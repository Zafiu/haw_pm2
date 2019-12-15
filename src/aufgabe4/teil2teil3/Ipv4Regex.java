package teil2teil3;

import java.util.regex.Pattern;

public class Ipv4Regex {
    public static void main(String[] args) {
        String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        if (Pattern.matches(regex, "99.186.15.010")) {
            System.out.println("WORKS FINE!1");
        }
    }
}
