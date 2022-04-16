package tgc.edu.ljc.stu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tgc.edu.ljc.stu.custom.BaseRepository;
import tgc.edu.ljc.stu.entity.Student;

@Repository
public interface StudentRepository extends BaseRepository<Student, Integer>{

//	public List<Student> findByTbClassId(Integer banjiId);
	@Query(value = "from Student t where t.tbClass.id=?1 order by t.stuNo")
	public List<Student> findByTbClassIdOrderByStuNo(Integer banjiId);

}
