package com.backend.store.utils;

import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Random;

@Component
public class Shuffle {
    public <T> List<T> shuffle(List<T> list) {
        int currentIndex = list.size();
        Random random = new Random();

        // While there remain elements to shuffle.
        while (currentIndex != 0) {
            // Pick a remaining element.
            int randomIndex = random.nextInt(currentIndex);
            currentIndex--;

            // And swap it with the current element.
            T temp = list.get(currentIndex);
            list.set(currentIndex, list.get(randomIndex));
            list.set(randomIndex, temp);
        }

        return list;
    }
}
