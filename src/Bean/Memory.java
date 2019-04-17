package Bean;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Memory {
    private int memorySize;
    private Queue<Message> messageQueue;

    public float getMemoryPercentage() {
        return (messageQueue.size() + 1) / memorySize;
    }

    public Queue<Message> getMessageQueue() {
        return messageQueue;
    }

    public boolean removeMessageFromMemory(Message message) {
        if (messageQueue.peek() != null && messageQueue.peek().equals(message)) {
            messageQueue.poll();
            return true;
        }
        return false;
    }

    /**
     * @param memorySize :默认传入的单位为M，需要将其转化为字节。
     */
    public Memory(int memorySize) {
        this.memorySize = memorySize;
        this.messageQueue = new LinkedList<>();
    }

    public boolean addContentToMemory(Message message) {
        if (messageQueue.size() < memorySize) {
            messageQueue.offer(message);
            return true;
        } else {
            return false;
        }
    }
}
