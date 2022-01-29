package blockchain;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BlockChain chain;
        File file = new File("BlockChain");
        if (file.exists() && !file.isDirectory())
            chain = new BlockChain("BlockChain");
        else {
            System.out.println("Enter the number of Zeroes: ");
            chain = new BlockChain(scanner.nextInt());
            for (int i = 0; i < 5; i++) {
                chain.addBlock();
            }
            SerializationUtils.WriterCloser();
        }
        chain.print();
    }
}





