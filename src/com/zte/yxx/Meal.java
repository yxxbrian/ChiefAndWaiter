package com.zte.yxx;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2019/3/20.
 */
public class Meal {

    static AtomicInteger atomMealNum = new AtomicInteger(0);

    private Meal() {
        mealNum = atomMealNum.getAndIncrement();
    }

    private int mealNum;

    public int getMealNum() {
        return mealNum;
    }

    public static synchronized Meal produceMeal()
    {
        cookingMeal();
        return new Meal();
    }

    static void cookingMeal(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException ex){

        }
    }

    static void eatingMeal(){
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException ex){

        }
    }

}
