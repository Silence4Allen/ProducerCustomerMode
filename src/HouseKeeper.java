import java.util.ArrayList;

public class HouseKeeper {
    private boolean isFull = false;
    private int num = 0;
    private ArrayList list;

    public HouseKeeper(ArrayList list) {
        this.list = list;
    }

    public int getIndex() {
        if (list != null && !list.isEmpty())
            return list.size() - 1;
        return -1;
    }

    public int getNum() {
        num = list.size();
        return num;
    }

    public void pop() {
        if (getIndex() != -1) {
            list.remove(getIndex());
            num--;
        }
    }

    public void add(Object o) {
        list.add(o);
        num++;
    }


}
