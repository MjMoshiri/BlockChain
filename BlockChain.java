package blockchain;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


class BlockChain implements Serializable {
    private long blockCount = 0;
    private final ArrayList<Block> blocks = new ArrayList<>();
    private int zeroes = 0;
    private static long timeStamp;
    private static final BlockChain INSTANCE = new BlockChain();
    private static final int HARDSHIP_MINIMUM_SECOND =10;
    private static final int HARDSHIP_MAXIMUM_SECOND =30;

    public int getZeroes() {
        return zeroes;
    }

    public ArrayList<Block> getBlocks() {
        return blocks;
    }

    public String getLastBlockHash() {
        return blockCount > 0 ? blocks.get((int) (blockCount - 1)).getHash() : "0";
    }

    public long getBlockCount() {
        return blockCount;
    }


    private BlockChain() {
        timeStamp = new Date().getTime();
    }

    public static BlockChain getInstance() {
        return INSTANCE;
    }

    public void saveBlockChain(String blockChainFile) throws IOException {
        SerializationUtils.serialize(INSTANCE, blockChainFile);
        //Can be more optimal
    }

    public void loadBlockChain(String blockChainFile) throws IOException, ClassNotFoundException {
        File file = new File(blockChainFile);
        if (file.exists() & !file.isDirectory()) {
            BlockChain tempChain = (SerializationUtils.deserialize(blockChainFile));
            INSTANCE.blocks.addAll(tempChain.getBlocks());
            INSTANCE.zeroes = tempChain.getZeroes();
            INSTANCE.blockCount = tempChain.getBlockCount();
            timeStamp = new Date().getTime();
        }

    }

    private void addBlock(Block block) {
        blocks.add(block);
        blockCount++;
    }

    public void addBlock() {
    }

    public synchronized boolean validateBlock(Block block, int minerId) {
        if (block.getHash().startsWith("0".repeat(zeroes)))
            if (block.getPreHash().equals(getLastBlockHash())) {
                addBlock(block);
                System.out.println("BLock:" + "\nCreated by miner # " + minerId + "\n" + block);
                checkProofOfWork();
                return true;
            }
        return false;
    }

    private static void checkProofOfWork() {
        long timeTaken = (new Date().getTime() - timeStamp) / 1000;
        System.out.println("Block was generating for " + timeTaken + "seconds");
        timeStamp = new Date().getTime();
        if (timeTaken < HARDSHIP_MINIMUM_SECOND) {
            INSTANCE.zeroes++;
            System.out.println("N increased to " + INSTANCE.zeroes + "\n");
        } else if (timeTaken > HARDSHIP_MAXIMUM_SECOND) {
            INSTANCE.zeroes--;
            System.out.println("N decreased to " + INSTANCE.zeroes + "\n");
        } else
            System.out.println("N stayed the same" + "\n");

    }

    public boolean validate() {
        String preHash = "0";
        for (Block block : blocks) {
            if (!block.getPreHash().equals(preHash)) {
                return false;
            }
            preHash = block.getHash();
        }
        return true;
    }

    public void print() {
        for (Block block : blocks) {
            System.out.println(block);
        }
    }
}
