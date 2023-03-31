package edu.eci.arep.app;

import java.util.ArrayList;
import java.util.List;

public class ConjeturaCollatz {
    private static ConjeturaCollatz instance;

    public static ConjeturaCollatz getInstance() {
        if(instance==null){
            instance = new ConjeturaCollatz();
        }
        return instance;
    }

    public List<Integer> enterNumber(String num){
        int numCast = Integer.parseInt(num);
        List<Integer> secuence = new ArrayList<>();
        secuence.add(numCast);
        while (numCast != 1){
            if(numCast % 2 == 0){
                numCast = numCast / 2;
            }else {
                numCast = (numCast*3) + 1;
            }
            secuence.add(numCast);
        }
        return secuence;
    }
}
