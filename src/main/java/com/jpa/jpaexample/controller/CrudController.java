package com.jpa.jpaexample.controller;

import com.jpa.jpaexample.entity.CrudEntity;
import com.jpa.jpaexample.repository.CrudEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class CrudController {

    private final CrudEntityRepository crudEntityRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/search")
    public String searchAllMember(){
        return crudEntityRepository.findAll().toString();
    }

    @GetMapping("searchParam")
    public String searchParam(@RequestParam(value = "age") int age){
        List resultList = entityManager.createQuery("select name from sample_member where age > :age")
                .setParameter("age",age)
                .getResultList();
        return resultList.toString();
    }
    @GetMapping("paramSearch")
    public String searchParamRepo(@RequestParam(value = "name") String name){
        return crudEntityRepository.searchParamRepo(name).toString();
    }

    @GetMapping("insert")
    public String insertMember(@RequestParam(value = "name") String name,@RequestParam(value = "age") int age){
        if(crudEntityRepository.findById(name).isPresent()){
            return "이미 존재하는 멤버입니다";
        }else{
            CrudEntity entity = CrudEntity.builder().name(name).age(age).build();
            crudEntityRepository.save(entity);
            return "이름" + name + "나이" + age + "으로 추가 되었습니다";
        }
    }

    @GetMapping("update")
    public String updateMember(@RequestParam(value = "name") String name,@RequestParam(value = "age") int age){
        if(crudEntityRepository.findById(name).isEmpty()){
            return "입력한 "+name+"이라는 멤버는 없습니다";
        }
        else{
            crudEntityRepository.save(CrudEntity.builder().name(name).age(age).build());
            return name + "의 나이를 "+ age+ "로 변경하였습니다";
        }
    }
    @GetMapping("delete")
    public String deleteMember(@RequestParam(value = "name") String name){
        if(crudEntityRepository.findById(name).isEmpty()){
            return "입력한 "+name+"이라는 멤버는 없습니다";
        }else {
            crudEntityRepository.delete(CrudEntity.builder().name(name).build());
            return name + "삭제 완료";
        }
    }
}
