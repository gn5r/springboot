package com.jptomato.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.jptomato.entity.User;

@Repository
@Mapper
public interface UserRepository {

	@Select("select * from user")
	public List<User> selectList();

	@Select("select * from user where username=#{username}")
	public User findByName(@Param("username") String username);

	@Select("select * from user where id=#{id}")
	public User findById(@Param("id")int id);

	@Insert("insert into user values(#{id},#{username},#{password})")
	public void insert(User user);

	@Update("update user set id=#{id}, username=#{username}, password=#{password} where id=#{id}")
	public void update(User user);

	@Delete("delete from user where id=#{id}")
	public void delete(@Param("id") int id);
}
