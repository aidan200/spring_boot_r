package com.xianzhipt.dao;

import com.xianzhipt.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/8/9.
 */
@Repository
public interface UserDao extends CrudRepository<User,Long> {
    //基于sql 单条件查询
    @Query("select t from User t where t.name = :name")
    Page<User> findByUserName(@Param("name") String name, Pageable pageable);

    Page<User> findAll(Specification<User> specification, Pageable pageable);
}