package com.atguigu.imperial.court;

import com.atguigu.imperial.court.entity.Emp;
import com.atguigu.imperial.court.entity.EmpExample;
import com.atguigu.imperial.court.entity.Memorials;
import com.atguigu.imperial.court.mapper.EmpMapper;
import com.atguigu.imperial.court.mapper.MemorialsMapper;
import com.atguigu.imperial.court.service.api.EmpService;
import com.atguigu.imperial.court.service.api.MemorialsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


/**
 * @author liuyang
 * @create 2022-11-03 19:20
 */



@ExtendWith(SpringExtension.class)  //在测试中使用Spring测试框架功能,必须引入
@ContextConfiguration(value = {"classpath:spring.xml"}) ////设置Spring测试环境的配置文件
public class ImperialCourtTest {


    @Autowired
    private DataSource dataSource;

    @Test
    public void testDataSource() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println("connection = " + connection);

    }


   @Autowired
    private EmpMapper empMapper;

    @Test
    public void testEmpMapper() {
        List<Emp> empList = empMapper.selectByExample(new EmpExample());
        for (Emp emp : empList) {
            System.out.println("emp = " + emp);
        }
    }



    @Autowired
    private EmpService empService;

    @Test
    public void testLogin(){
        String loginAccount = "xiaoxuanzi1654";
        String loginPassword = "16540504";
        Emp empByLogin = empService.getEmpByLogin(loginAccount, loginPassword);
        System.out.println(empByLogin);
    }



    @Autowired
    private MemorialsService memorialsService;

    @Test
    public void getAllMemorials(){
        List<Memorials> list = memorialsService.getAllMemorials();
        for(Memorials memorial : list){
            System.out.println(memorial);
        }
    }



    @Test
    public void getMemorialById(){
        Memorials memorial = memorialsService.getMemorialById("1");
        System.out.println(memorial);
    }


    @Test
    public void feedBack(){
        memorialsService.feedBack("1","捐款");
    }




}
