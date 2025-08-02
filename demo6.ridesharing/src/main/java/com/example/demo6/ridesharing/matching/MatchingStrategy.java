package com.example.demo6.ridesharing.matching;

import com.example.demo6.ridesharing.model.*;

import java.util.List;

public interface MatchingStrategy {
    Driver findDriver(Rider rider, Location source, List<Driver> drivers);
}
