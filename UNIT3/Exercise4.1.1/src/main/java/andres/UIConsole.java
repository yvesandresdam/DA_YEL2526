package andres;

public class UIConsole {
    public static int calculateStringLongitude(String word) {
        int numberChars = word.length();
        if (numberChars < 10)
            return 5;
        else if (numberChars < 20)
            return 4;
        else if (numberChars < 30)
            return 3;
        else if (numberChars < 40)
            return 2;
        else
            return 1;
    }
}
