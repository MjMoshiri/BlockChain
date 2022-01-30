package blockchain;

import java.util.concurrent.ThreadLocalRandom;

public class Miner implements Runnable {
    private final BlockChain chain;
    private final int id;

    public Miner(int id, BlockChain chain) {
        this.id = id;
        this.chain = chain;
    }

    public void mine() {
        do {
            Block block = new Block(chain.getBlockCount() + 1, chain.getLastBlockHash(), ThreadLocalRandom.current().nextLong(Long.MAX_VALUE));
            chain.validateBlock(block,id);
        } while (!Thread.currentThread().isInterrupted());
    }

    @Override
    public void run() {
        mine();
    }
}
