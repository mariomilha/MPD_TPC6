/*
 * Copyright (C) 2014 Eu
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package pt.isel.mpd14.probe.test.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Eu
 */
public class OtherStudent {
    
    public final int id;
    private String birthDate;

    private List<Grade> grades;
    private String name;
    private Course course;

    public OtherStudent() {
        id = -1;
    }
    
    public OtherStudent(int id, String birthDate, String name, Course course) {
        this.id = id;
        this.birthDate = birthDate;
        this.name = name;
        this.course = course;
        this.grades = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public List<Grade> getGrades() {
        return grades;
    }

    public String getName() {
        return name;
    }

    public Course getCourse() {
        return course;
    }
    
    
    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setGrades(List<Grade> grades) {
        this.grades = grades;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    
}
