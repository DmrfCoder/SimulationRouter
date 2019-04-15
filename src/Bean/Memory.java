package Bean;

import java.util.ArrayList;

/**
 * @author dmrfcoder
 * @date 2019-04-15
 */
public class Memory {
    private int memorySize;
    private ArrayList<Message> arrayList;


    /**
     * @param memorySize :默认传入的单位为M，需要将其转化为字节。
     */
    public Memory(int memorySize) {
        this.memorySize = memorySize;
    }

    public boolean addContentToMemory(Message message) {
        if (arrayList.size() < memorySize) {
            arrayList.add(message);
            return true;
        } else {
            return false;
        }
    }
}
