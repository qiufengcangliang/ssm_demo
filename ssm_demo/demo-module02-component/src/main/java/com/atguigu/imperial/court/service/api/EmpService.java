package com.atguigu.imperial.court.service.api;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.entity.Memorials;

import java.util.List;

/**
 * @author liuyang
 * @create 2022-11-04 10:46
 */
public interface EmpService {
    Emp getEmpByLogin(String loginAccount, String loginPassword);




}
