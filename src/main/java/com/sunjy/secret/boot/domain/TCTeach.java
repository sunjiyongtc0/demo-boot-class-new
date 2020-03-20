package com.sunjy.secret.boot.domain;

import javax.persistence.*;

@Table(name = "DemoClass.t_c_teach")
public class TCTeach {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "teach_id")
    private Long teachId;

    @Column(name = "student_id")
    private String studentId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return course_id
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return teach_id
     */
    public Long getTeachId() {
        return teachId;
    }

    /**
     * @param teachId
     */
    public void setTeachId(Long teachId) {
        this.teachId = teachId;
    }

    /**
     * @return student_id
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * @param studentId
     */
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}