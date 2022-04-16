package tgc.edu.ljc.stu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tgc.edu.ljc.stu.custom.BaseService;
import tgc.edu.ljc.stu.entity.Student;
import tgc.edu.ljc.stu.repository.StudentRepository;

@Service
public class StudentService extends BaseService<Student, Integer>{
	@Autowired
	private StudentRepository studentDAO;
	
	public List<Student> findByTbClassId(Integer banjiId) {
		return studentDAO.findByTbClassIdOrderByStuNo(banjiId);
		
	}

}
