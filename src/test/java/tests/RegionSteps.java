package tests;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum RegionSteps {
    MOSCOW("Москва и Московская область"),
    SAINT_PETERSBURG("Санкт-Петербург и Ленинградская область"),
    ALTAI_TERRITORY("Алтайский край"),
    NIZHNY_NOVGOROD("Нижегородская область"),
    TATARSTAN("Республика Татарстан");

    private final String name;

    RegionSteps(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static String getRegion() {
        List<RegionSteps> regions = Stream.of(RegionSteps.values()).collect(Collectors.toList());
        return regions.get(ThreadLocalRandom.current().nextInt(regions.size())).getName();
    }
}
