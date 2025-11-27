package strategy;

import base.Bus;
import service.MyArrayList;

import java.util.List;

public interface DataInputStrategy {
    MyArrayList<Bus> getBuses();
}
