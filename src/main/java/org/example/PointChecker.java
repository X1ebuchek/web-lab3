package org.example;

import java.util.Date;

public class PointChecker {
    public short check(double x, double y, double r){
        if (x>5 || x<-5 || y>5 || y<-3) return -1;
        if (x>=0 && y>=0){
            if (y<=(-2)*x+r){
                return 1;
            }
            return 0;
        }
        if (x>=0 && y<=0){
            return 0;
        }
        if (x<=0 && y>0){
            if (Math.sqrt(x*x+y*y)<=r/2){
                return 1;
            }
            return 0;
        }
        if (x<=0 && y<=0){
            if (y>=-r/2 && x>=-r){
                return 1;
            }
            return 0;
        }
        return 0;
    }

}
