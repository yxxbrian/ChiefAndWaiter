package com.zte.yxx;

/**
 * Created by Administrator on 2019/3/20.
 */
public class Waiter {

    public Waiter(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    Restaurant restaurant;

    public void work()
    {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //to void dead block, first query chief for meal
                while (true)
                {
                    Chief chief = restaurant.getChief();
                    synchronized (chief)
                    {
                        System.out.println("INFO= Waiter says: hello chief, have you cooked a meal?");
                        while (chief.cookedMeal == null)
                        {
                            System.out.println("INFO= Waiter says: chief hasn't cook, I have to wait.");
                            //chief hasn't cook, wait now
                            try {
                                chief.wait();
                            } catch (InterruptedException ex) {
                                System.out.println("INFO= I am waiter, I got a interrupt signal.");
                                return;
                            }
                        }

                        System.out.println("INFO= I am waiter, I have get a meal from chief, thanks chief");

                        //now, lastly chief has cooked a meal
                        Meal meal = chief.cookedMeal;
                        chief.cookedMeal = null;

                        //eat meal
                        System.out.println("INFo= I am waiter, I'm hungry, I'll eat this meal myself.");
                        meal = null;
                        Meal.eatingMeal();

                        //tell chief to cook again
                        chief.notifyAll();

                    }
                }
            }
        });
        thread.start();
    }
}
