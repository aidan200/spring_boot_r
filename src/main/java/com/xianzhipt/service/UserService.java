package com.xianzhipt.service;

import com.xianzhipt.bean.User;
import com.xianzhipt.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/8/9.
 */
@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    //单条件
    public Page<User> findUserByName(int page,int rows,String name){
        //排序条件
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        //分页（排序可选）
        Pageable pageable = new PageRequest(page,rows,sort);

        Page<User> users = userDao.findByUserName(name,pageable);

        return users;
    }
    //多条件
    public Page<User> findUserAll(int page,int rows,Map map){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        //分页（排序可选）
        Pageable pageable = new PageRequest(page,rows,sort);
        Page<User> users = userDao.findAll(new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(null != map.get("name")){
                    predicates.add(criteriaBuilder.equal(root.get("name"),map.get("name")));
                }
                if(null != map.get("pwd")){
                    predicates.add(criteriaBuilder.like(root.get("password"), "%"+map.get("pwd")+"%"));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);

        return users;
    }

    public void deleteUserById(Long id){
        userDao.delete(id);
    }
}