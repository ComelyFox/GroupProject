package sorting;

import base.Bus;
import strategy.SortingStrategy;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class BusTrickySorter implements SortingStrategy {
    @Override
    public void sort(List<Bus> buses) {
        List<Bus> evenList = buses.stream()
                .filter(x ->x.getSerialNumber()%2==0)
                .sorted(Comparator.comparing(Bus::getSerialNumber))
                .collect(Collectors.toList());

        Map<Integer, Bus> oddMap = IntStream.range(0, buses.size())
                .filter(i -> buses.get(i).getSerialNumber() % 2 != 0)
                .boxed()
                .collect(Collectors.toMap(
                        i -> i,
                        buses::get
                ));

        List<Bus> result = new ArrayList<>(buses.size());
        Iterator<Bus> evenIterator = evenList.iterator();
        for (int i = 0; i < buses.size(); i++) {
            Bus bus = oddMap.get(i);
            if (bus != null) {
                result.add(bus);
            } else {
                result.add(evenIterator.next());
            }
        }

        buses.clear();
        buses.addAll(result);
    }
}
