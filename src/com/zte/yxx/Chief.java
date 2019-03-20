package com.zte.yxx;

/**
 * Created by Administrator on 2019/3/20.
 */
public class Chief {

    public Chief(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    Restaurant restaurant;

    Meal cookedMeal;

    private boolean working = false;

    public synchronized void work()
    {
        if(working)
            return;

        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Chief chief = restaurant.getChief();
                    synchronized (chief) {
                        while (cookedMeal != null) {
                            //cooked but not fetched by waiter
                            System.out.println("INFO=I am chief, I have cooked but no waiters come fetch, I'm going to sleep to wait.");
                            try {
                                chief.wait();
                            } catch (InterruptedException ex) {
                                System.out.println("INFO=chief get a interrupt signal: " + ex.getMessage());
                                return;
                            }
                        }

                        //now cook is get away, so cook a meal
                        Meal meal = Meal.produceMeal();
                        cookedMeal = meal;
                        System.out.println("INFO=I am chief, I have cooked an awesome meal, waiters, come and fetch!");

                        //meal is cooked, so notify waiters to fetch
                        chief.notifyAll();

                    }
                }
            }
        });
        thread.start();
    }

}
