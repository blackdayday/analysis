package tgc.edu.ljc.stu.repository;

import org.springframework.stereotype.Repository;

import tgc.edu.ljc.stu.custom.BaseRepository;
import tgc.edu.ljc.stu.entity.SysUser;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Integer>{

	public SysUser findByUsername(String username);
}
