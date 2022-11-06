package com.atguigu.imperial.court.service.impi;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.entity.EmpExample;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.entity.MemorialsExample;
import com.atguigu.imperial.court.mapper.EmpMapper;
import com.atguigu.imperial.court.mapper.MemorialsMapper;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuyang
 * @create 2022-11-04 10:46
 */


@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;





    @Override
    public Emp getEmpByLogin(String loginAccount, String loginPassword) {


        // 1、密码加密
        String encodedLoginPassword = MD5Util.encode(loginPassword);

        // 2、通过 QBC 查询方式封装查询条件
        EmpExample example = new EmpExample();
        EmpExample.Criteria criteria = example.createCriteria();
        criteria.andLoginAccountEqualTo(loginAccount).andLoginPasswordEqualTo(encodedLoginPassword);
        List<Emp> empList = empMapper.selectByExample(example);

        if (empList != null && empList.size() > 0) {

            // 3、返回查询结果
            return empList.get(0);
        }
        return null;
    }





}
