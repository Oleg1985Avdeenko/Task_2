package org.example.scientists;
/**
 * Common logical class for two scientists. Collects details and passes to the scientist
 */

import org.example.detail.BodyPart;
import org.example.detail.Dump;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Scientist implements Runnable{

    private static final int NIGHT_NUMBER = 100;
    private static final int TIME_OF_NIGHT = 100;

    private List<BodyPart> detailsList = new ArrayList<>();
    private static Random random = new Random();

    public void setDetailsList(List<BodyPart> detailsList) {
        this.detailsList = detailsList;
    }

    public List<BodyPart> getDetailsList() {
        return detailsList;
    }

    // Collects details and passes to the scientist
    public void pickUpDetail() {
        for (int j = 0; j < random.nextInt(1, 4); j++) {
            if (!Dump.getDropedParts().isEmpty()) {
                detailsList.add(Dump.giveBodyPart());
            }
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < NIGHT_NUMBER; i++) {
            pickUpDetail();
            try {
                Thread.sleep(TIME_OF_NIGHT);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
