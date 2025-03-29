package dao;

import collection.Dragon;

import java.util.TreeSet;

public record DateAndDragons(String date, TreeSet<Dragon> dragons) {
}
