package blockchain;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

class Block implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final long id;
    private final long timeStamp;
    private final String hash;
    private final String preHash;
    private final long magicNumber;
    private final long timeTaken;
    public final int zeroes;

    public Block(long id, String preHash, int zeroes) {
        this.zeroes = zeroes;
        timeStamp = new Date().getTime();
        this.id = id;
        this.preHash = preHash;
        long tempMagicNumber = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
        String tempHash = StringUtil.applySha256(preHash + id + timeStamp + tempMagicNumber);
        while (!tempHash.startsWith("0".repeat(zeroes))) {
            tempMagicNumber = ThreadLocalRandom.current().nextLong(Long.MAX_VALUE);
            tempHash = StringUtil.applySha256(preHash + id + timeStamp + tempMagicNumber);
        }
        magicNumber = tempMagicNumber;
        hash = tempHash;
        timeTaken = (new Date().getTime() - timeStamp) / 1000;
    }

    public String getHash() {
        return hash;
    }

    public long getId() {
        return id;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public String getPreHash() {
        return preHash;
    }

    public String toString() {
        return "Block:\n" + "Id: " + id + "\n" + "Timestamp: " + timeStamp + "\n" + "Magic number: " + magicNumber + "\n" + "Hash of the previous block:\n" + preHash + "\n" + "Hash of the block:\n" + hash + "\n" + "Block was generating for " + timeTaken + " seconds\n";
    }


}
