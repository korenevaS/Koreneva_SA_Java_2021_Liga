package com.github.korenevaS.queue.repository.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AutoPopulatingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Timetable {
    Map<Integer, List<String>> timetable = new HashMap<>();

    List<String> timetableForWeekday = new ArrayList();
    List<String> timetableForWeekends = new ArrayList<>();

    public Timetable() {
        timetableForWeekday.add("9:00");
        timetableForWeekday.add("10:00");
        timetableForWeekday.add("11:00");
        timetableForWeekday.add("12:00");
        timetableForWeekday.add("14:00");
        timetableForWeekday.add("15:00");
        timetableForWeekday.add("16:00");
        timetableForWeekday.add("17:00");
        timetableForWeekday.add("18:00");
        timetable.put(1, timetableForWeekends);
        timetable.put(2, timetableForWeekday);
        timetable.put(3, timetableForWeekday);
        timetable.put(4, timetableForWeekday);
        timetable.put(5, timetableForWeekday);
        timetable.put(6, timetableForWeekday);
        timetable.put(7, timetableForWeekends);
    }
}
