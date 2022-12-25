package org.example.detail;
/**
 * Retrieves discarded parts from the factory and allows assistants to pick up the parts
 */

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Dump implements Runnable {
    private static final int FIRST_NIGHT_PART_NUMBER = 20;
    private static final int NIGHT_NUMBER = 100;
    private static final int TIME_OF_NIGHT = 100;
    private static int count = 0;

    private static BlockingQueue<BodyPart> dropedParts = new ArrayBlockingQueue<>(500);

    public static BlockingQueue<BodyPart> getDropedParts() {
        return dropedParts;
    }

    public static void setDropedParts(BlockingQueue<BodyPart> dropedParts) {
        Dump.dropedParts = dropedParts;
    }

    // Receives discarded parts from the factory
    public static BlockingQueue<BodyPart> getBodyPart() {

        if (count < 1) {
            for (int i = 0; i < FIRST_NIGHT_PART_NUMBER; ++i) {
                dropedParts.add(Factory.throwAwayPart());
            }
            count++;
        } else {

            for (int j = 0; j < Factory.getPartNumber(); j++) {
                dropedParts.add(Factory.throwAwayPart());
            }
        }

        return dropedParts;
    }

    //Allows an assistant to pick up a part
    public static BodyPart giveBodyPart() {
        return dropedParts.poll();
    }


    @Override
    public void run() {
        for (int i = 0; i < NIGHT_NUMBER; i++) {
            getBodyPart();
            try {
                Thread.sleep(TIME_OF_NIGHT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
