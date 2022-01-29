package blockchain;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        BlockChain chain;
        int blockCounter;
        File file = new File("BlockChain");
        if (file.exists() && !file.isDirectory())
            chain = new BlockChain("BlockChain");
        else {
            System.out.println("Enter the number of Zeroes you want at beginning of each hash: ");
            chain = new BlockChain(scanner.nextInt());
            System.out.println("How Many Blocks Do you want to create ? : ");
            blockCounter = scanner.nextInt() + 1;
            for (int i = 1; i < blockCounter; i++) {
                System.out.println("Creating Block" + i);
                chain.addBlock();
                System.out.println("Block " + i + " Created");
            }
            SerializationUtils.WriterCloser();
        }
        chain.print();
    }
}





