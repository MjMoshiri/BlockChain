package blockchain;

import java.io.IOException;
import java.util.ArrayList;


class BlockChain {
    private long blockCount = 0;
    private final ArrayList<Block> blocks = new ArrayList<>();
    private final int zeroes;

    public BlockChain(int zeroes) {
        this.zeroes = zeroes;
    }

    public BlockChain(String blockChainFile) throws IOException {
        blocks.addAll((SerializationUtils.deserialize(blockChainFile)));
        zeroes = blocks.get(0).zeroes;
    }

    public void addBlock() throws IOException {
        String preHash = (blockCount == 0) ? "0" : blocks.get((int) blockCount - 1).getHash();
        blockCount++;
        Block block = new Block(blockCount, preHash, zeroes);
        blocks.add(block);
        SerializationUtils.serialize(block, "BlockChain");
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
