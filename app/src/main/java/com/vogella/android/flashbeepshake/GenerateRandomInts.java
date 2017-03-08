package com.vogella.android.flashbeepshake;

import java.util.Random;

/**
 * Created by Gideon Nyamachere on 3/8/2017.
 */

public class GenerateRandomInts {
    public  static int ranInt = 0;
    private static Random rand = new Random();

    public int getAdditionInt(){
        System.out.print("GenerateRandomInts: getAdditionInt()");
        int min = 2;
        int max = 100;
        return ranInt = rand.nextInt((max - min) + 1) + min;
    }
    public int getMultInt(){
        int min = 1;
        int max = 12;
        return ranInt = rand.nextInt((max - min) + 1) + min;
    }
}
