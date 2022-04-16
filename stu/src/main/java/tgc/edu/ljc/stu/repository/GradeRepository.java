package tgc.edu.ljc.stu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tgc.edu.ljc.stu.custom.BaseRepository;
import tgc.edu.ljc.stu.entity.Course;
import tgc.edu.ljc.stu.entity.Grade;

@Repository
public interface GradeRepository extends BaseRepository<Grade, Integer>{

	public Grade findByCourseIdAndStudentId(Integer courseId, Integer studentId);
	
	@Query(value = "select distinct t.course from Grade t where t.student.tbClass.id=?1")
	public List<Course> findCoursesByTbClassId(Integer tbClassId);

	public List<Grade> findByStudentId(Integer id);

}
