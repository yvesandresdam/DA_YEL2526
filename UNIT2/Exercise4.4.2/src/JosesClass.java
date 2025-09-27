import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class JosesClass {

    /*/ TUTORIAL CLASS /*/
    /*/ I'm writing the theory of the exercise as seen on class /*/

    InputStream inputStream = null;
    String filePath = "";

    public void start() {
        System.out.println("Enter de path of the file");
        Scanner scanner = new Scanner(System.in);
        filePath = scanner.nextLine();

        try {
            inputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        byte[] myDescriptor = new byte[6];
        int bytesReaded = 0;

        try {
            bytesReaded = inputStream.read(myDescriptor);
            System.out.println((byte) myDescriptor[0]);
            System.out.println("0x" + Integer.toHexString(myDescriptor[0]).toUpperCase());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
