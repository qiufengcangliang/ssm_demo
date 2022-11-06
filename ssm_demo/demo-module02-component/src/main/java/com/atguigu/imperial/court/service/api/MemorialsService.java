package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @author liuyang
 * @create 2022-11-04 14:47
 */
public interface MemorialsService {
    List<Memorials> getAllMemorials();

    Memorials getMemorialById(String memorialsId);


    void feedBack(String memorialsId, String feedbackContent);

    void updateMemorialsStatus(String memorialsId);
}
