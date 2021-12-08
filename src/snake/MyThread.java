package snake;

public class MyThread extends Thread {
    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {}
            System.out.println("update");
        }
    }

    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.start();

    }
}
