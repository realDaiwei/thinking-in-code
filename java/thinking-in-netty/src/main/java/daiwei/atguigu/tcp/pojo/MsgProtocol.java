package daiwei.atguigu.tcp.pojo;

/**
 * Created by Daiwei on 2021/2/27
 */
public class MsgProtocol {

    private int len;

    private byte[] content;

    public MsgProtocol(int len, byte[] content) {
        this.len = len;
        this.content = content;
    }

    public int getLen() {
        return len;
    }

    public byte[] getContent() {
        return content;
    }
}
