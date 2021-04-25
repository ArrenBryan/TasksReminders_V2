package com.example.tasksreminders.ui.tasks;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Tasks implements Comparable {
    private String name;
    private String description;
    private String deadline;

    public Tasks(String name, String deadline, String description) {
        this.name = name;
        this.deadline = deadline;
        this.description = description;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date strDate1, strDate2;

        try {
            strDate1 = sdf.parse(deadline);
            strDate2 = sdf.parse(((Tasks) o).getDeadline());

            if (strDate1.after(strDate2)) {
                return 1;
            }

            else if (strDate1.before(strDate2)) {
                return -1;
            }

            return 0;

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
