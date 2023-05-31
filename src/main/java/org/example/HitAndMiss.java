package org.example;

import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;

public class HitAndMiss extends NotificationBroadcasterSupport implements HitAndMissMBean, Serializable {

    long shots = 0;
    long misses = 0;
    long notificationCounter = 0;

    @Override
    public void hit() {
        shots++;
    }

    @Override
    public void miss() {
        shots++;
        misses++;
    }

    @Override
    public void shotOutOfBounds() {
        shots++;
        misses++;
        sendNotification(new Notification("Shot out of bounds", this, notificationCounter++));
    }

    @Override
    public long getShots() {
        return shots;
    }

    @Override
    public long getMisses() {
        return misses;
    }

    @Override
    public long getHits() {
        return shots - misses;
    }
}
