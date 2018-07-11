public class Producer extends Thread implements Observerable {
    private HouseKeeper houseKeeper;
    private int time;
    private Observer observer;

    public Producer(HouseKeeper houseKeeper, int time) {
        this.houseKeeper = houseKeeper;
        this.time = time;
    }

    @Override
    public void run() {
        super.run();

        while (true) {
            if (houseKeeper.getNum() < 50) {
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                houseKeeper.add(1);
                System.out.println("Producer + 1 , house = " + houseKeeper.getNum());
                notifyObserver(this);
            } else {
                try {
                    System.out.println("Producer is waiting...");
                    System.out.println("Producer thread = " + this);
                    notifyObserver(this);
                    synchronized (this) {
                        wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    @Override
    public void registerObserver(Observer o) {
        this.observer = o;
    }

    @Override
    public void removeObserver(Observer o) {
        this.observer = null;
    }

    @Override
    public void notifyObserver(Thread thread) {
        observer.update(thread);
    }
}
