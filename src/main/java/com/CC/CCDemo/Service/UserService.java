package com.CC.CCDemo.Service;

import com.CC.CCDemo.Dao.RoleDao;
import com.CC.CCDemo.Dao.UserRepository;
import com.CC.CCDemo.Demo.Area;
import com.CC.CCDemo.Demo.Role;
import com.CC.CCDemo.Demo.SortDto;
import com.CC.CCDemo.Demo.User;
import com.CC.CCDemo.SortTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.util.*;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userDao;
    @Autowired
    private RoleDao roleDao;

    public Optional<User> findById(Long Id){
        return userDao.findById(Id);
    }

    public    List<User> findAll(User user,Integer roleId,Area area) {
        Specification<User> specification = new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //用于暂时存放查询条件的集合
                List<Predicate> predicatesList = new ArrayList<>();
                //--------------------------------------------
                //查询条件示例
                if(!StringUtils.isEmpty(user.getId())){
                    Predicate namePredicate = cb.equal(root.get("id"), user.getId());
                    predicatesList.add(namePredicate);
                }
                //equal示例
                if (!StringUtils.isEmpty(user.getUserName())){
                    Predicate namePredicate = cb.equal(root.get("name"), user.getUserName());
                    predicatesList.add(namePredicate);
                }
                //like示例
                if (!StringUtils.isEmpty(user.getNickName())){
                    Predicate nickNamePredicate = cb.like(root.get("nickName"), '%'+user.getNickName()+'%');
                    predicatesList.add(nickNamePredicate);
                }
                //between示例
                if (user.getBirthday() != null) {
                    Predicate birthdayPredicate = cb.between(root.get("birthday"), user.getBirthday(), new Date());
                    predicatesList.add(birthdayPredicate);
                }

                //关联表查询示例
                if (!StringUtils.isEmpty(roleId)) {
                    Join<User,Role> joinTeacher = root.join("roles",JoinType.LEFT);
                    Predicate coursePredicate = cb.equal(joinTeacher.get("id"), roleId);
                    predicatesList.add(coursePredicate);
                }

                //复杂条件组合示例
                if (area.getStatus()!=null && !StringUtils.isEmpty(area.getAreaName())&& area.getId()!=0 ) {
                    Join<User,Area> joinExam = root.join("areas",JoinType.LEFT);
                    Predicate predicateExamStatus = cb.equal(joinExam.get("status"),area.getStatus());
                    Predicate predicateExamAreaName = cb.equal(joinExam.get("areaName"),area.getAreaName());
                    Predicate predicateExamAreaId = cb.equal(joinExam.get("id"),area.getId());
                    //组合
                    Predicate predicateExam = cb.or(predicateExamStatus,predicateExamAreaName);
                    Predicate predicateExamAll = cb.and(predicateExamAreaId,predicateExam);
                    predicatesList.add(predicateExamAll);
                }
                //--------------------------------------------
                //排序示例(先根据学号排序，后根据姓名排序)
                query.orderBy(cb.asc(root.get("id")),cb.asc(root.get("userName")));
                //最终将查询条件拼好然后return
                Predicate[] predicates = new Predicate[predicatesList.size()];
                return cb.and(predicatesList.toArray(predicates));
            }

        };


        return userDao.findAll(specification);
    }
    public void findAndUpdateById(Long Id){
        Optional<User> userOptional=userDao.findById(Id);
        User user;
        user = userOptional.orElseGet(User::new);
        user.setNickName("test111");
        userDao.save(user);
    }
    public User findByUserName(String userName){
        return userDao.findByUserName(userName);
    }
    public List<User> findByRegTime(String regTime){
        return userDao.findByRegTime(regTime);
    }
    public void saveUser(User user){
        userDao.save(user);
    }
    public void addUser(User userForm){
        String passWord=new BCryptPasswordEncoder().encode(userForm.getPassWord());
        userForm.setPassWord(passWord);
        userDao.save(userForm);
    }
    private void encryptPassword(User userInfo){
        String password = userInfo.getPassWord();
        password = new BCryptPasswordEncoder().encode(password);
        userInfo.setPassWord(password);
    }
    public void updateById(){
        User user=new User();
        Long id=new Long("7");
        user.setId(id );
        user.setUserName("test2");
     userDao.updateById("test3",id);
    }
    public void saveUserRole(){
        User user=new User();
        Role role=new Role();
        user=userDao.findById(Long.parseLong("13")).orElseGet(User::new);
        role.setRoleName("系统维护员");
        role=roleDao.findByRoleName("系统维护员").orElse(new Role("系统维护员"));
        Set<Role> roleSet=new HashSet<>();
        roleSet.add(role);
        Role role1=new Role();
        role1.setRoleName("测试");
        role1=roleDao.findByRoleName("测试").orElse(new Role("测试"));
        roleSet.add(role1);
        roleDao.save(role);
        roleDao.save(role1);
        user.setRoles(roleSet);
        userDao.save(user);
    }
    public String buildPageRequest() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
      //  List<User> userList=userDao.findAll(sort);
        List<User> userList=userDao.findAll(SortTools.basicSort(new SortDto("asc","id"),new SortDto("nickName")));
        StringJoiner stringJoiner=new StringJoiner("-");
        for (User u:userList             ) {
            stringJoiner.add(u.getNickName());
        }
        return stringJoiner.toString();
    }
}
