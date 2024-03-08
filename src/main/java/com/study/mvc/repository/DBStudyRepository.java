package com.study.mvc.repository;

import com.study.mvc.entity.Study;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper //xml 파일이 자동 component 생성
public interface DBStudyRepository {
    public int save(Study study);
    public Study findStudyById(int id);
    public Study findStudyByName(String name);
    public List<Study> findAll(); // MAP 을 List 로 자동변환
    public int deleteById(int id);
    public int putById(Study study);
    public int patchById(Study study);
}
