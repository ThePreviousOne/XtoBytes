package thepreviousone.xasbytes;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        File file = new File(args[0]);

        try {
            // Reading a Image file from file system
            FileInputStream imageInFile = new FileInputStream(file);
            byte imageData[] = new byte[(int) file.length()];
            imageInFile.read(imageData);

            // Converting Image byte array into Base64 String
            String imageDataString = encodeImage(imageData);

            // Converting a Base64 String into Image byte array
            byte[] imageByteArray = decodeImage(imageDataString);

            int length = imageDataString.length();

            Files.write(Paths.get("image"), imageByteArray);
            // Write a image byte array into file system
            PrintWriter printWriter = new PrintWriter ("XasBytes");
            printWriter.println ("StringBuilder().append(\"" + imageDataString.substring(0, 100) + "\")");
            for (int i = 0; i < length; i++) {
                if ( i % 100 == 0 && i > 100) {
                    printWriter.println(".append(\"" + imageDataString.substring(i - 100, i) + "\")");
                    System.out.println(i);
                } else if (i == length-1) {
                    printWriter.println(".append(\"" + imageDataString.substring(i - ((length - 1) % 100), i) + "\");");
                    System.out.println(i);
                }
            }
            printWriter.close ();

            imageInFile.close();

            System.out.println("Image Successfully Manipulated!");
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
    }

    /**
     * Encodes the byte array into base64 string
     *
     * @param imageByteArray - byte array
     * @return String a {@link java.lang.String}
     */
    public static String encodeImage(byte[] imageByteArray){
        return Base64.encodeBase64URLSafeString(imageByteArray);
    }

    /**
     * Decodes the base64 string into byte array
     *
     * @param imageDataString - a {@link java.lang.String}
     * @return byte array
     */
    public static byte[] decodeImage(String imageDataString){
        return Base64.decodeBase64(imageDataString);
    }
}
