package com.peierlong.lambda;

import java.util.ArrayList;
import java.util.List;

/**
 * @author elong
 * @version V1.0
 * @date 2018/7/20
 */
public class StreamDemo {

    private static void foreach() {
        List<Artist> artists = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            artists.add(new Artist("zs" + i, "London"));
        }
        // start >>>>>
        long count = artists.stream().filter(artist -> artist.isFrom("London")).count();
        System.out.println(count);
    }

    public static void main(String[] args) {
        foreach();
    }

}
