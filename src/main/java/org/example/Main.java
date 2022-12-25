package org.example;
/**
 * Main class. Starts threads and counts the number of collected robots. The result is entered into the console
 */

import org.example.detail.BodyPart;
import org.example.detail.Dump;
import org.example.scientists.Scientist;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {


    public static void main(String[] args) {
        Dump dump = new Dump();
        Scientist rick = new Scientist();
        Scientist morty = new Scientist();

        Thread threadRick = new Thread(rick);
        Thread threadMorty = new Thread(morty);
        Thread threadDump = new Thread(dump);
        threadRick.start();
        threadMorty.start();
        threadDump.start();
        try {
            threadRick.join();
            threadMorty.join();
            threadDump.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Rick collected " + rick.getDetailsList() + " robot parts");
        System.out.println("Morty collected " + morty.getDetailsList() + " robot parts");
        if (assemblerRobot(rick.getDetailsList()) > assemblerRobot(morty.getDetailsList())) {
            System.out.println("Rick Win!\nHe made " + assemblerRobot(rick.getDetailsList()) + " robots.");
        } else if (assemblerRobot(morty.getDetailsList()) > assemblerRobot(rick.getDetailsList())) {
            System.out.println("Morty Win!\nHe made " + assemblerRobot(morty.getDetailsList()) + " robots.");
        } else {
            System.out.println("It's a tie!\nRick and Morty made " + assemblerRobot(rick.getDetailsList()) + " robots each.");
        }
        System.out.println(assemblerRobot(rick.getDetailsList()));
        System.out.println(assemblerRobot(morty.getDetailsList()));

    }
        public static int assemblerRobot(List <BodyPart> list) {
            Map<BodyPart, Integer> map = new HashMap<>();
            list.removeAll(Collections.singletonList(null));
            for (BodyPart part : list) {
                if (!map.containsKey(part)) {
                    map.put(part, 1);
                } else {
                    map.put(part, map.get(part) + 1);
                }
            }

            if (map.size() < BodyPart.values().length) {
                return 0;
            }
            return Collections.min(map.values());
        }

}