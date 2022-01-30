package blockchain;

import java.io.*;
import java.util.ArrayList;


class SerializationUtils {
    /**
     * Serialize the given object to the file
     */
    public static void serialize(BlockChain obj, String fileName) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(obj);
        oos.close();
    }

    /**
     * Deserialize to an object from the file
     */
    public static BlockChain deserialize(String fileName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        BlockChain obj = (BlockChain) ois.readObject();
        ois.close();
        return obj;
    }
}

