package blockchain;

import java.io.Serializable;
import java.util.Date;

class Block implements Serializable {

    private final long id;
    private final long timeStamp;
    private final String hash;
    private final String preHash;
    private final long magicNumber;

    public Block(long id, String preHash, long magicNumber) {

        timeStamp = new Date().getTime();
        this.id = id;
        this.preHash = preHash;
        this.magicNumber = magicNumber;
        hash = StringUtil.applySha256(preHash + id + timeStamp + magicNumber);

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
        return "Id: " + id + "\n" + "Timestamp: " + timeStamp + "\n" + "Magic number: " + magicNumber + "\n" + "Hash of the previous block:\n" + preHash + "\n" + "Hash of the block:\n" + hash;
    }


}
