import java.io.*;

public class FileSorter {
    public void launch() {
        // files
        String filepath1 = "Documentation/file1.txt";
        String filepath2 = "Documentation/file2.txt";
        String fileResult = "Documentation/sorted.txt";

        // get file words count
        int totalWordsFile1 = getAllWordsFromFile(filepath1);
        int totalWordsFile2 = getAllWordsFromFile(filepath2);

        // line count
        int file1WordsCount = 0;
        int file2WordsCount = 0;

        // words from files
        String wordFile1;
        String wordFile2;

        // flags
        boolean flagMainLoop = true;

        // read line

        while (flagMainLoop) {
            wordFile1 = readFile1WithLine(filepath1, file1WordsCount);
            wordFile2 = readFile2WithLine(filepath2, file2WordsCount);

            // compare words
            String wordToWrite = compareWords(wordFile1, wordFile2);

            // write word
            writeToFile(fileResult, wordToWrite);

            // get the lefting words in document
            if (wordToWrite.equals(wordFile1))
                file1WordsCount++;
            if (wordToWrite.equals(wordFile2))
                file2WordsCount++;

            // condition to exit main loop
            if (file1WordsCount >= totalWordsFile1) {
                writeCompleteFile(filepath2, fileResult, file2WordsCount, totalWordsFile2);
                flagMainLoop = false;
            }
            if (file2WordsCount >= totalWordsFile2) {
                writeCompleteFile(filepath1, fileResult, file1WordsCount, totalWordsFile1);
                flagMainLoop = false;
            }
        }
    }

    private int getAllWordsFromFile(String filepath) {
        int result = 0;
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        boolean flag = true;
        while (flag) {
            try {
                if (reader.readLine() != null)
                    result++;
                else
                    flag = false;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

    private String readFile1WithLine(String filepath, int wordNumber) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String result = "";

        try {
            for (int i = 0; i <= wordNumber; i++) {
                result = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private String readFile2WithLine(String filepath, int wordNumber) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader(filepath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String result = "";

        try {
            for (int i = 0; i <= wordNumber; i++) {
                result = reader.readLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    private String compareWords(String word1, String word2) {
        String result = "";

        int lowerCharactersCount = 0;
        lowerCharactersCount = word1.length();
        if (word2.length() < lowerCharactersCount)
            lowerCharactersCount = word2.length();

        for (int i = 0; i < lowerCharactersCount; i++) {
            if (word1.charAt(i) < word2.charAt(i))
                result = word1;
            else if (word2.charAt(i) < word1.charAt(i))
                result = word2;
        }
        return result;
    }

    private void writeToFile(String filepath, String word) {
        PrintWriter writer;

        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(filepath, true)));
            writer.println(word);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeCompleteFile(String filepath, String fileWrite, int lineCount, int totalLines) {
        BufferedReader reader;
        PrintWriter writer;
        String word = "";
        try {
            reader = new BufferedReader(new FileReader(filepath));
            for (int i = 0; i < totalLines; i++) {
                word = reader.readLine();
                if (i >= lineCount) {
                    try {
                        writer = new PrintWriter(new BufferedWriter(new FileWriter(fileWrite, true)));
                        writer.println(word);
                        writer.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



