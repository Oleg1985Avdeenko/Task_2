package org.example.detail;
/**
 * Throws random parts into the landfill and reports the number of discarded parts
 */

import java.util.Random;


public class Factory {

    private static final int MIN_BODY_PART = 1;
    private static final int MAX_BODY_PART = 4;
    private static BodyPart[] bodyParts = BodyPart.values();

    private static Random random = new Random();

    //Throws out random parts
    public static BodyPart throwAwayPart() {
        return bodyParts[random.nextInt(bodyParts.length)];
    }

    // Throws out the number of parts
    public static int getPartNumber() {
        return random.nextInt(MIN_BODY_PART, MAX_BODY_PART);
    }


}
