package com.xuecheng.manage_course.dao;

import com.xuecheng.framework.domain.course.Teachplan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Administrator.
 */
public interface TeachplanRepository extends JpaRepository<Teachplan,String> {
    /**
     * 定义方法根据课程id和父结点id查询出结点列表，可以使用此方法实现查询根结点
     * @param courseId
     * @param parentId
     * @return
     */
    //SELECT * FROM teachplan WHERE courseid = '297e7c7c62b888f00162b8a7dec20000' AND parentid='0'
    List<Teachplan> findByCourseidAndParentid(String courseId,String parentId);
}
