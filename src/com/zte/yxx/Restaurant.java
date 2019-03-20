package com.zte.yxx;

/**
 * Created by Administrator on 2019/3/20.
 */
public class Restaurant {

    public Restaurant(String strName) {
        this.strRestaurantName = strName;
        this.chief = new Chief(this);
        this.waiter = new Waiter(this);
    }

    public final String strRestaurantName;

    private Waiter waiter;
    private Chief chief;

    public Waiter getWaiter() {
        return waiter;
    }

    public Chief getChief() {
        return chief;
    }


    public void startWork()
    {
        waiter.work();
        chief.work();
    }

}
