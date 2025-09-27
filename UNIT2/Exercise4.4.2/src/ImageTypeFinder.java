import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ImageTypeFinder {
    //SETUP
    Scanner scanner = new Scanner(System.in);
    boolean loopCondition = true;

    // MAIN FUNCTION
    public void start() {
        // Splash Screen
        System.out.println(UI.WELCOME_MESSAGE);

        //MAIN LOOP
        while (loopCondition) {
            // User's path of the image
            System.out.println(UI.IMAGE_PATH);
            String folderPath = "resources/images/";
            String filePath = scanner.nextLine();
            String relativePath = folderPath + filePath;

            // Main function
            TypeFile imageType = findDescriptor(relativePath);

            // Main result
            displayResult(imageType);

            // Exiting Condition
            System.out.println(UI.EXIT_CONDITION);
            String loopConditionUser = scanner.nextLine();
            if (loopConditionUser.equals("Y") || loopConditionUser.equals("y"))
                loopCondition = true;
            else
                loopCondition = false;
        }

        // Exiting the App
        System.out.println(UI.EXIT_MESSAGE);
    }

    private TypeFile findDescriptor(String filePath){
        // reading the file
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // buffer of readed bytes
        byte[] bytesReaded = new byte[6];

        // buffered bytes
        try {
            inputStream.read(bytesReaded);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // bytes from 0 to 5 variable stored
        byte b0 = bytesReaded[0];
        byte b1 = bytesReaded[1];
        byte b2 = bytesReaded[2];
        byte b3 = bytesReaded[3];
        byte b4 = bytesReaded[4];
        byte b5 = bytesReaded[5];

        // descriptor conditions
        if (b0 == (byte) 0x42 && b1 == (byte) 0x4D)
            return TypeFile.BMP;
        else if (b0 == (byte) 0x89 && b1 == (byte) 0x50 && b2 == (byte) 0x4E && b3 == (byte) 0x47) {
            return TypeFile.PNG;
        } else if (b0 == (byte) 0x00 && bytesReaded[1] == (byte) 0x00 && b2 == (byte) 0x01 && b3 == (byte) 0x00) {
            return TypeFile.ICO;
        } else if (b0 == (byte) 0xFF && bytesReaded[1] == (byte) 0xD8 && b2 == (byte) 0xFF) {
            return TypeFile.JPG;
        } else if (b0 == (byte) 0x47 && bytesReaded[1] == (byte) 0x49 && b2 == (byte) 0x46 && b3 == (byte) 0x38 && b4 == (byte) 0x39 &&
                b5 == (byte) 0x61) {
            return TypeFile.GIF;
        } else if (b0 == (byte) 0x47 && bytesReaded[1] == (byte) 0x49 && b2 == (byte) 0x46 && b3 == (byte) 0x38 && b4 == (byte) 0x37 &&
                b5 == (byte) 0x61) {
            return TypeFile.GIF;
        }
        return TypeFile.UNKNOWN;
    }

    private void displayResult(TypeFile imageType) {
        // Getting the TypeFile Enum's name of the result
        String type = imageType.name();
        // Printing result
        System.out.println("The image provided matches a " + type + " image format");
    }
}
