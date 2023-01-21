package org.example;

import java.util.Date;

public class PointChecker {
    public boolean check(double x, double y, double r){
        if (x>=0 && y>=0){
            if (y<=(-2)*x+r){
                return true;
            }
            return false;
        }
        if (x>=0 && y<=0){
            return false;
        }
        if (x<=0 && y>0){
            if (Math.sqrt(x*x+y*y)<=r/2){
                return true;
            }
            return false;
        }
        if (x<=0 && y<=0){
            if (y>=-r/2 && x>=-r){
                return true;
            }
            return false;
        }
        return false;
    }

}
