package teil2teil3;

import java.util.regex.Pattern;

public class EmailBasicRegex {
    public static void main(String[] args) {
        String regex = "(((V){0,1}[a-z]+(_){0,1})+(\\.[a-z]+)?[0-9]{0,2})@(([a-z]+\\.)?[0-9]{0,1}[a-z]+[0-9]{0,1}(-[a-z]+)?\\.[a-z]+)";
        if (Pattern.matches(regex, "max.mustermann@gmx.de")) {
            System.out.println("WORKS FINE!1");
        }
        if (Pattern.matches(regex, "chunkylover53@aol.com")) {
            System.out.println("WORKS FINE!2");
        }
        if (Pattern.matches(regex, "musterma@informatik.haw-hamburg.de")) {
            System.out.println("WORKS FINE!3");
        }
        if (Pattern.matches(regex, "Veni_Vidi_Vici@2isnot3.eu")) {
            System.out.println("WORKS FINE!4");
        }

        if (Pattern.matches(regex, "hello,dolly@web.de")) {
            System.out.println("WRONG1");
        }
        if (Pattern.matches(regex, "whatever@cro.")) {
            System.out.println("WRONG2");
        }
        if (Pattern.matches(regex, "penny@bbt.one4me")) {
            System.out.println("WRONG3");
        }
        if (Pattern.matches(regex, "(empty)@all?.de")) {
            System.out.println("WRONG4");
        }


    }
}
