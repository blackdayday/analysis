package tgc.edu.ljc.stu.repository;

import org.springframework.stereotype.Repository;

import tgc.edu.ljc.stu.custom.BaseRepository;
import tgc.edu.ljc.stu.entity.Course;

@Repository
public interface CourseRepository extends BaseRepository<Course, Integer>{

}
