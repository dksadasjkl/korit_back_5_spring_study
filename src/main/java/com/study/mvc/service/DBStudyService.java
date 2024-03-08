package com.study.mvc.service;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.dto.DBStudyInsertRespDto;
import com.study.mvc.dto.DBStudySelectRespDto;
import com.study.mvc.entity.Study;
import com.study.mvc.repository.DBStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DBStudyService {
    @Autowired
    private DBStudyRepository dbStudyRepository;

//    public int createStudy (DBStudyReqDto dbStudyReqDto) {
//        // [ All ]
////        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);
//        // [ noArgs ]
////        Study study = new Study();
////        study.setName(dbStudyReqDto.getName());
////        study.setAge(dbStudyReqDto.getAge());
//        // [ Builder ]
//        Study study = Study.builder()
//                .name(dbStudyReqDto.getName())
//                .age(dbStudyReqDto.getAge())
//                .build();
//
//        return dbStudyRepository.save(study);
//    }

    public DBStudyInsertRespDto createStudy (DBStudyReqDto dbStudyReqDto) {
        // [ All ]
//        Study study = new Study(0, dbStudyReqDto.getName(), dbStudyReqDto.getAge(), null);
        // [ noArgs ]
//        Study study = new Study();
//        study.setName(dbStudyReqDto.getName());
//        study.setAge(dbStudyReqDto.getAge());
        // [ Builder ]
        Study findStudy = dbStudyRepository.findStudyByName(dbStudyReqDto.getName());
        if(findStudy != null) {
            return DBStudyInsertRespDto.builder()
                    .successStatus(false)
                    .build();
        }

        Study study = Study.builder()
                .name(dbStudyReqDto.getName())
                .age(dbStudyReqDto.getAge())
                .build();

        int successCount = dbStudyRepository.save(study); // db에 insert 될 때 기본값이 행의 수를 리턴함

        DBStudyInsertRespDto dbStudyResDto =
                DBStudyInsertRespDto.builder()
                    .id(study.getId())
                    .name(study.getName())
                    .age(study.getAge())
                    .successStatus(successCount > 0)
                    .successCount(successCount)
                    .build();

        return dbStudyResDto;
    }

    public DBStudySelectRespDto findStudyById(int id) {
        Study study = dbStudyRepository.findStudyById(id);
        System.out.println(study);

        DBStudySelectRespDto dbStudySelectRespDto =
                DBStudySelectRespDto.builder()
                    .id(study.getId())
                    .name(study.getName())
                    .age(study.getAge())
                    .build();

        return dbStudySelectRespDto;
    }

    public DBStudySelectRespDto findStudyByName(String name) {
        Study study = dbStudyRepository.findStudyByName(name);

        return study == null ? null : study.toDto();
    }

    public List<DBStudySelectRespDto> findAll() {
        List<DBStudySelectRespDto> respDtoList = new ArrayList<>();
        List<Study> studyList = dbStudyRepository.findAll();
        for (Study study : studyList) {
            respDtoList.add(study.toDto());
        }

        return respDtoList;
    }
    public  List<DBStudySelectRespDto> findAll2() {
        return  dbStudyRepository.findAll()
                .stream()
                .map(Study::toDto)
                .collect(Collectors.toList());
    }

    public int deleteById(int id) {

        return dbStudyRepository.deleteById(id);
    }

    public int putById(int id, DBStudyReqDto dbStudyReqDto) {
        return dbStudyRepository.putById(dbStudyReqDto.toEntity(id));

    }
    public int patchById(int id, DBStudyReqDto dbStudyReqDto) {
        return dbStudyRepository.patchById(dbStudyReqDto.toEntity(id));
    }


}
