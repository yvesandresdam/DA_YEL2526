import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BMPReader {
    private String filePath = "";
    private int fileSize;
    private int fileWidth;
    private int fileHeight;
    private int fileBPP;

    InputStream inputStream = null;
    FileInputStream fileInputStream = null;
    boolean mainLoop = true;

    public void start() {
        // This is the main loop of the program
        while (mainLoop) {
            // sets up the BMP file
            setupFile();

            // sets the BMP file information
            List<Byte> bytesList = new ArrayList<>();
            bytesList = setDataBytesInformation();

            // gets the BMP file information
            getDataInformation(bytesList);

            // displays the BMP file information
            displayInformation();

            // evaluate the loop condition
            mainLoop = repeatMainLoop();
        }
    }

    // 1._
    // sets up the BMP file
    private void setupFile() {
        // Sets the folder path
        String folderPath = "resources/BMP/";

        // Sets the users path
        Scanner scanner = new Scanner(System.in);
        System.out.println("Set the path of the BMP File");
        String userPath = scanner.nextLine();

        // Sets the relative file path
        filePath = folderPath + userPath;
    }

    // 2._
    // sets the BMP file information
    private List<Byte> setDataBytesInformation() {
        // File is readed
        try {
            fileInputStream = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 54 bytes are stored at 'readedBytes' array[]
        byte[] readedBytes = new byte[54];
        try {
            fileInputStream.read(readedBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // readedBytes array is Listed for easy management of the bytes
        List<Byte> resultList = new ArrayList<>();
        for (byte b : readedBytes) {
            resultList.add(b);
        }

        // method returns a list with the first 56 bytes of the BMP file
        return resultList;
    }


    // 3._
    // gets the BMP file information
    private void getDataInformation(List<Byte> bytesList) {
        // This function gets different range of bytes, from the 56 element byteList
        int offsetBytes = 0;
        int numberBytes = 0;

        // FILESIZE
        offsetBytes = 2;
        numberBytes = 4;
        List<Byte> fileSizeBytes = getDescriptorByteList(bytesList, offsetBytes, numberBytes);
        fileSize = calculateHexadecimalValue(fileSizeBytes);

        // FILEWIDTH
        offsetBytes = 18;
        numberBytes = 4;
        List<Byte> fileWidthBytes = getDescriptorByteList(bytesList, offsetBytes, numberBytes);
        fileWidth = calculateHexadecimalValue(fileWidthBytes);

        // FILEHEIGHT
        offsetBytes = 22;
        numberBytes = 4;
        List<Byte> fileHeightBytes = getDescriptorByteList(bytesList, offsetBytes, numberBytes);
        fileHeight = calculateHexadecimalValue(fileHeightBytes);

        // FILEBITSPIXEL
        offsetBytes = 28;
        numberBytes = 2;
        List<Byte> fileBitsPixelBytes = getDescriptorByteList(bytesList, offsetBytes, numberBytes);
        fileBPP = calculateHexadecimalValue(fileBitsPixelBytes);
    }

    private List<Byte> getDescriptorByteList(List<Byte> bytesList, int offsetBytes, int numberBytes) {
        // This function gets the list of bytes with given offset and number of bytes
        List<Byte> listResult = new ArrayList<>();
        for (int i = offsetBytes; i < offsetBytes + numberBytes; i++) {
            listResult.add(bytesList.get(i));
        }

        return listResult;
    }

    private int calculateHexadecimalValue(List<Byte> byteList) {
        // Result of the casting operation
        int result = 0;
        int bytesListSize = 0;

        // Size of the bytes list. Useful to calculate the weight of the byte
        bytesListSize = byteList.size();

        // Decimal variable and List of Decimals variables
        int decimal;
        List<Integer> decimalList = new ArrayList<>();

        // From the bytesList (Byte) to the decimalList (Decimal)
        for (Byte b : byteList) {
            decimal = Byte.toUnsignedInt(b);
            decimalList.add(decimal);
        }

        // Operation to calculate the value of the hexadecimal number
        for (int i = 0; i < bytesListSize; i++) {
            result += (int) (decimalList.get(i) * Math.pow(256, i));
        }

        return result;
    }

    // 4._
    // displays the BMP file information
    private void displayInformation() {
        System.out.println("BMPReader data information:");
        System.out.printf("File size: %s %s\n", fileSize, "bytes");
        System.out.printf("File width: %s %s\n", fileWidth, "bytes");
        System.out.printf("File height: %s %s\n", fileHeight, "bytes");
        System.out.printf("File bpp: %s %s\n", fileBPP, "bytes");
    }

    // 5._
    // evaluate the loop condition
    private boolean repeatMainLoop() {
        // returns a boolean that loops the app
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to review another file?\n'Y/N'");
        String userInput = scanner.nextLine();

        if (userInput.equals('Y') || userInput.equals('y'))
            return true;
        return false;
    }
}
