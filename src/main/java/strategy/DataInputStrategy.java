package strategy;

import base.Bus;
import service.MyArrayList;


public interface DataInputStrategy {
    MyArrayList<Bus> getBuses();
}
