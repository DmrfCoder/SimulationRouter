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
    private String originIp;
    private String content;


    public String getTargetAddress() {
        return targetAddress;
    }

    public Message() {
        studentNumber = "161630213";
    }

    public Message(int processId, int port, int sendId, String originIp, String targetAddress, String content) {
        this.processId = processId;
        this.port = port;
        this.sendId = sendId;
        this.targetAddress = targetAddress;
        this.content = content;
        studentNumber = "161630213";
        this.originIp = originIp;
    }

    public String getOriginIp() {
        return originIp;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(processId).append(":").append(port).append(":").append(sendId).append(":").append(studentNumber);

        return stringBuilder.toString();
    }
}
