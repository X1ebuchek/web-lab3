package org.example;

public interface HitAndMissMBean {

    default void shot(boolean isHit) {
        if (isHit)
            hit();
        else
            miss();
    }


    void hit();

    void miss();

    void shotOutOfBounds();

    long getShots();

    long getMisses();

    long getHits();


}
