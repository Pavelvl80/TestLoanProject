package com.example.service.impl;

import com.example.service.FilterService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Service
public class FilterServiceImpl implements FilterService {
    private final HashMap<String, Pair> time = new HashMap<>();

    private final class Pair {
        private final long first;
        private final long last;

        private Pair(final long first, final long last) {
            this.first = first;
            this.last = last;
        }
    }

    public boolean filer(String country) {
        boolean result;
        do {
            Pair pair = time.get(country);
            Pair temp = pair;
            if (pair == null) {
                pair = new Pair(System.currentTimeMillis(), 1);
                temp = pair;
            } else {
                if ((System.currentTimeMillis() - pair.first)/1000 > 1) {
                    pair = new Pair(System.currentTimeMillis(), 1);
                } else {
                    pair = new Pair(pair.first, pair.last + 1);
                }
            }
            result = time.putIfAbsent(country, pair) == null || time.replace(country, temp, pair);
        } while (!result);
        return time.get(country).last > 1;
    }
}
