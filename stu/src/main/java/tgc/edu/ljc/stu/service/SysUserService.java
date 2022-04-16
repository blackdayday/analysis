package tgc.edu.ljc.stu.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tgc.edu.ljc.stu.custom.BaseService;
import tgc.edu.ljc.stu.entity.SysRole;
import tgc.edu.ljc.stu.entity.SysUser;
import tgc.edu.ljc.stu.repository.SysUserRepository;
import tgc.edu.ljc.stu.security.User2;

@Service
public class SysUserService extends BaseService<SysUser, Integer> implements UserDetailsService {
	
	public SysUser getOne(Integer id) {
		return sysUserDAO.getOne(id);
	}


	@Autowired
	private SysUserRepository sysUserDAO;
	
	
	public SysUser findById(Integer id) {
		Optional<SysUser> optional = sysUserDAO.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	public Page<SysUser> findAll(Pageable pageable) {
		return sysUserDAO.findAll(pageable);
	}


	public List<SysUser> findAll() {
		return sysUserDAO.findAll();
	}


	public List<SysUser> findAll(Sort sort) {
		return sysUserDAO.findAll(sort);
	}


	public List<SysUser> findAllById(Iterable<Integer> ids) {
		return sysUserDAO.findAllById(ids);
	}


	public <S extends SysUser> Page<S> findAll(Example<S> example, Pageable pageable) {
		return sysUserDAO.findAll(example, pageable);
	}


	public <S extends SysUser> List<S> findAll(Example<S> example) {
		return sysUserDAO.findAll(example);
	}


	public <S extends SysUser> List<S> findAll(Example<S> example, Sort sort) {
		return sysUserDAO.findAll(example, sort);
	}


	public <S extends SysUser> S save(S entity) {
		return sysUserDAO.save(entity);
	}


	public <S extends SysUser> List<S> saveAll(Iterable<S> entities) {
		return sysUserDAO.saveAll(entities);
	}


	public void deleteById(Integer id) {
		sysUserDAO.deleteById(id);
	}


	public void delete(SysUser entity) {
		sysUserDAO.delete(entity);
	}
	
	

	public SysUser findByUsername(String username) {
		return sysUserDAO.findByUsername(username);
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = findByUsername(username);
		if(user==null) {
			throw new UsernameNotFoundException(username);
		}
		List<GrantedAuthority> authorities=new ArrayList<>();
		List<SysRole> roles = user.getRoles();
		for (SysRole sysRole : roles) {
			authorities.add(new SimpleGrantedAuthority(sysRole.getCode()));
		}
		return new User2(username, user.getPassword(),user.getName(), authorities);
	}


	
	
}
