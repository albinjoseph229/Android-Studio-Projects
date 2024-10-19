package com.example.studentdb;

public class Student {
    private int id;
    private String name;
    private String course;
    private int semester;

    //constructor
    public Student()
    {

    }
    //ID
    public void setId(int id)
    {
        this.id=id;
    }
    public int getId()
    {
        return this.id;
    }

    //NAME
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }

    //COURSE
    public void setcCourse(String course)
    {
        this.course=course;
    }
    public String getCourse()
    {
        return this.course;
    }

    //SEMESTER
    public void setSemester(int semester)
    {
        this.semester=semester;
    }
    public int getSemester()
    {
        return this.semester;
    }
}
