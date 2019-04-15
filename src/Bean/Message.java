package Bean;

/**
 * @author dmrfcoder
 * @date 2019-04-13
 */
public class Message {
    private int processId;
    private int port;
    private int sendId;
    private String studentNumber;
    private String targetAddress;
    private String content;

    public Message() {
        studentNumber = "161630213";
    }

    public Message(int processId, int port, int sendId, String targetAddress, String content) {
        this.processId = processId;
        this.port = port;
        this.sendId = sendId;
        this.targetAddress = targetAddress;
        this.content = content;
        studentNumber = "161630213";
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(processId).append(":").append(port).append(":").append(sendId).append(":").append(studentNumber);

        return stringBuilder.toString();
    }
}
