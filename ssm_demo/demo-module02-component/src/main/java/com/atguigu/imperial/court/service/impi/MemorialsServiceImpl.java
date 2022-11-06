package com.atguigu.imperial.court.service.impi;

import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.entity.MemorialsExample;
import com.atguigu.imperial.court.mapper.MemorialsMapper;
import com.atguigu.imperial.court.service.api.MemorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author liuyang
 * @create 2022-11-04 14:48
 */


@Service
@Transactional
public class MemorialsServiceImpl implements MemorialsService{


    @Autowired
    private MemorialsMapper memorialsMapper;



    @Override
    public List<Memorials> getAllMemorials() {

        MemorialsExample memorialsExample = new MemorialsExample();
        return memorialsMapper.selectByExample(memorialsExample);
    }


    @Override
    public Memorials getMemorialById(String memorialsId) {
        MemorialsExample memorialsExample = new MemorialsExample();
        MemorialsExample.Criteria criteria = memorialsExample.createCriteria();
        int id = Integer.parseInt(memorialsId);
        criteria.andMemorialsIdEqualTo(id);
        List<Memorials> list = memorialsMapper.selectByExample(memorialsExample);
        return list.get(0);
    }


    @Override
    public void feedBack(String memorialsId, String feedbackContent) {
        Memorials memorials = new Memorials();
        memorials.setFeedbackContent(feedbackContent);
        memorials.setMemorialsStatus(2);

        MemorialsExample example = new MemorialsExample();
        MemorialsExample.Criteria criteria = example.createCriteria();
        int id = Integer.parseInt(memorialsId);
        criteria.andMemorialsIdEqualTo(id);
        //第一个参数：要修改的数据。第二个参数：mysql语句where后面的条件
        memorialsMapper.updateByExampleSelective(memorials,example);
    }



    @Override
    public void updateMemorialsStatus(String memorialsId) {
        Memorials memorials = new Memorials();
        memorials.setMemorialsStatus(1);

        MemorialsExample example = new MemorialsExample();
        MemorialsExample.Criteria criteria = example.createCriteria();
        int id = Integer.parseInt(memorialsId);
        criteria.andMemorialsIdEqualTo(id);
        memorialsMapper.updateByExampleSelective(memorials,example);
    }




}
