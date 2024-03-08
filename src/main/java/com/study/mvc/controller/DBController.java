package com.study.mvc.controller;

import com.study.mvc.dto.DBStudyReqDto;
import com.study.mvc.service.DBStudyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DBController {

    @Autowired
    private DBStudyService dbStudyService;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody DBStudyReqDto dbStudyReqDto) {
        return ResponseEntity.ok(dbStudyService.createStudy(dbStudyReqDto));
    }

    @GetMapping("/select/study/{id}")
    public ResponseEntity<?> selectStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.findStudyById(id));
    }

    @GetMapping("/select/study")    // ?name=""
    public ResponseEntity<?> selectStudy(@RequestParam  String name) {
        return ResponseEntity.ok(dbStudyService.findStudyByName(name));
    }

    @GetMapping("/select/studys")
    public ResponseEntity<?> selectStudyAll() {
        return ResponseEntity.ok(dbStudyService.findAll());
    }

    @DeleteMapping("/delete/study/{id}")
    public ResponseEntity<?> deleteStudy(@PathVariable int id) {
        return ResponseEntity.ok(dbStudyService.deleteById(id));
    }

    // 수정 방법 1
    @PutMapping("/update/study/{id}")
    public ResponseEntity<?> putStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
            // 전체 수정
        return ResponseEntity.ok(dbStudyService.putById(id, dbStudyReqDto));
    }

    // 수정 방법 2
    @PatchMapping("/update/study/{id}")
    public ResponseEntity<?> patchStudy(
            @PathVariable int id,
            @RequestBody DBStudyReqDto dbStudyReqDto) {
            // 부분 수정
        return ResponseEntity.ok(dbStudyService.patchById(id, dbStudyReqDto));
    }
}
