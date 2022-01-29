package blockchain;

import java.io.*;
import java.util.ArrayList;

class SerializationUtils {
    private static boolean key = false;
    private static FileOutputStream fos;
    private static BufferedOutputStream bos;
    private static ObjectOutputStream oos;

    private static void CreatWriter(String fileName) throws IOException {
        File file = new File(fileName);
        if (file.exists() && !file.isDirectory()) {
            fos = new FileOutputStream(fileName, true);
        } else {
            fos = new FileOutputStream(fileName);
        }
        bos = new BufferedOutputStream(fos);
        oos = new ObjectOutputStream(bos);
    }

    public static void WriterCloser() throws IOException {
        oos.close();
    }

    public static void serialize(Block obj, String fileName) throws IOException {
        if (!key) {
            CreatWriter(fileName);
            key = true;
        }
        oos.writeObject(obj);
    }

    public static ArrayList<Block> deserialize(String fileName) throws IOException {
        final ArrayList<Block> blocks = new ArrayList<>();
        FileInputStream fis = new FileInputStream(fileName);
        BufferedInputStream bis = new BufferedInputStream(fis);
        ObjectInputStream ois = new ObjectInputStream(bis);
        while (true) {
            try {
                blocks.add((Block) ois.readObject());
            } catch (Exception e) {
                break;
            }
        }
        ois.close();
        return blocks;
    }
}
