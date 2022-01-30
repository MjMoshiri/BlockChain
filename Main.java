package blockchain;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        BlockChain chain = BlockChain.getInstance();
        final int MINER_COUNT = 8;
        Miner[] miners = new Miner[MINER_COUNT];
        ExecutorService executor = Executors.newFixedThreadPool(MINER_COUNT);
        chain.loadBlockChain("BlockChain");
        for (int i = 0; i < MINER_COUNT; i++) {
            miners[i] = new Miner(i + 1, chain);
            executor.submit(miners[i]);
        }
        while (chain.getBlockCount() < 20) {
            TimeUnit.SECONDS.sleep(1);
            chain.saveBlockChain("BlockChain");
        }
        executor.shutdownNow();

    }
}





