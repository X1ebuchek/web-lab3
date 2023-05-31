package org.example;

public interface HitPercentMBean {
    double getPercentOfHits();

    default void shot(boolean isHit) {
        if (isHit)
            hit();
        else
            miss();
    }

    void hit();

    void miss();
}
