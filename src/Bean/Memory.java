package Bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Memory {
    private double memorySize;
    private final Vector<Message> messageVector;
    private volatile double curSize;


    public double getMemoryPercentage() {

        if (memorySize - curSize < 0.5) {
            return 100;
        }
        return (curSize / memorySize) * 100;
    }

    public int getMemoryCurCount() {
        return messageVector.size();
    }


    public Message getMessage() {
        if (messageVector.size() == 0) {
            return null;
        } else {
            Message message = messageVector.lastElement();
            return message;
        }
    }

    public Vector<Message> getMessageVector() {
        return messageVector;
    }

    public boolean removeMessageFromMemory(Message message) {

        synchronized (messageVector) {
            if (messageVector.contains(message)) {
                messageVector.remove(message);
                curSize -= message.getContent().length();
                return true;
            }
        }

        return false;
    }

    /**
     * @param memorySize :默认传入的单位为M，需要将其转化为字节。
     */
    public Memory(double memorySize) {
        this.memorySize = memorySize * 1024 * 1024;
        this.messageVector = new Vector<>();
        this.curSize = 0;
    }

    public boolean addContentToMemory(Message message) {
        if (curSize < memorySize) {
            messageVector.add(message);
            curSize += message.getContent().length();
            return true;
        } else {
            return false;
        }
    }
}
