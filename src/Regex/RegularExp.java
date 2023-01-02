package Regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExp {
    public static void main(String[] args) {
//        Pattern p = Pattern.compile("\\s");//all space characters [" "]
//        Pattern p = Pattern.compile("\\S");//all characters print except space character [^a-zA-Z0-9] and [a-zA-Z0-9]
//        Pattern p = Pattern.compile("\\d");//all digits characters [0-9]
//        Pattern p = Pattern.compile("\\D");//all characters print except digit character [^a-zA-Z] and [a-zA-Z]
//        Pattern p = Pattern.compile("\\w");//all word characters [a-zA-Z0-9]
//        Pattern p = Pattern.compile("\\W");//all characters print except space character [^a-zA-Z0-9]
//        Pattern p = Pattern.compile(".");//all symbol including special characters also [^a-zA-Z0-9] and [a-zA-Z0-9] and \s

        //Quantifiers in Regex
//        Pattern p = Pattern.compile("a");//Exactly one a
//        Pattern p = Pattern.compile("a+");//Atleast one a
//        Pattern p = Pattern.compile("a*");//Any number of a including zero number also
//        Pattern p = Pattern.compile("a+");//Atmost one a
        //test against this example to try
//        Matcher m = p.matcher("abaabbabbbaaa");
//        while(m.find()){
//            System.out.println(m.start()+"..."+m.group());
//        }


        Pattern p = Pattern.compile("\\s\\w");
        Matcher m = p.matcher("hello@hi^   k*I&");
        while (m.find()) {
            System.out.println(m.start() + "..." + m.group());
        }

        //Gmail Validator
        Pattern p1 = Pattern.compile("[a-zA-z0-9][a-zA-z0-9_.]*@[a-zA-z0-9]+([.][a-zA-z0-9]+)+");

        //Mobile number validator
        //if some regex box -> [] is repeated we can use the curluy brackkts to show the power of how many times then box is being repeated
//        Pattern p2 = Pattern.compile("([0|91]?[7-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])");//we can write this way or else use power method to short down to
        Pattern p2 = Pattern.compile("([0|91]?[7-9][0-9]{9})");

//        Password Validator
        Pattern p3 = Pattern.compile("");

        String phoneNumber = "918654723695";

        String Email = "Ritesh_pandit.2001@yahoo.in";

        Matcher m1 = p.matcher(Email);
        System.out.println(m.find());
        if (m1.find() && m1.group().equals(Email)) {
            System.out.println("valid");
        } else {
            System.out.println("invalid");
        }
    }
}