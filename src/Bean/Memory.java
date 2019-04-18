package Bean;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Vector;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Memory {
    private int memorySize;
    private Vector<Message> messageVector;
    private volatile float curSize;


    public float getMemoryPercentage() {
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
            messageVector.remove(message);
            return message;
        }
    }

    public Vector<Message> getMessageVector() {
        return messageVector;
    }

    public boolean removeMessageFromMemory(Message message) {
        if (messageVector.lastElement() != null && messageVector.lastElement().equals(message)) {
            messageVector.remove(messageVector.lastElement());
            curSize -= message.getContent().length();
            return true;
        }
        return false;
    }

    /**
     * @param memorySize :默认传入的单位为M，需要将其转化为字节。
     */
    public Memory(int memorySize) {
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
