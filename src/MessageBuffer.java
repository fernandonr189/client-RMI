import java.util.ArrayList;

public class MessageBuffer {
    private ArrayList<String> buffer;
    private boolean isEmpty;

    public MessageBuffer() {
        buffer = new ArrayList<String>();
        isEmpty = true;
    }

    public synchronized String consume() {
        if(isEmpty) {
            try {
                wait();
            }
            catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String str = buffer.getFirst();
        buffer.removeFirst();
        if(buffer.isEmpty()) {
            isEmpty = true;
        }
        notifyAll();
        return str;
    }

    public synchronized void produce(String msg) {
        buffer.add(msg);
        isEmpty = false;
        notifyAll();
    }
}
