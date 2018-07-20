import java.util.ArrayList;

public class Main implements Observer {

    private static Main main;

    public static void main(String[] args) {
        System.out.println("Hello World!");
        main = new Main();
        Main.doSth();
    }

    private static Producer producer;
    private static Customer customer;
    private static HouseKeeper houseKeeper;

    public static void doSth() {
        houseKeeper = new HouseKeeper(new ArrayList());
        producer = new Producer(houseKeeper, 200);
        customer = new Customer(houseKeeper, 1000);

        producer.registerObserver(main);
        customer.registerObserver(main);

        producer.start();
        customer.start();
    }

    @Override
    public void update(Thread thread) {
        if (thread.getClass() == Producer.class) {
            synchronized (customer) {
                customer.notify();
            }
        } else if (thread.getClass() == Customer.class) {
            synchronized (producer) {
                producer.notify();
            }
        }
    }

}
