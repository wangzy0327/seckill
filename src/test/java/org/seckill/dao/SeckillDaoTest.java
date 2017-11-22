package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.Seckill;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
//配置spring和junit整合,junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;
    @Test
    public void reduceNumber() throws Exception {
        /**
         * 11:23:08.642 [main] DEBUG o.m.s.dto.SpringManagedTransaction - JDBC Connection [com.mchange.v2.c3p0.impl.NewProxyConnection@7fd50002] will not be managed by Spring
         11:23:08.668 [main] DEBUG o.s.dao.SeckillDao.reduceNumber - ==>  Preparing:
         update seckill set number = number - 1
         where seckill_id = ?
         and start_time <= ?
         and end_time >= ?
         and number>0;
         Parameters:1000(Long), 2017-11-20 11:23:08.268(Timestamp), 2017-11-20 11:23:08.268(Timestamp)
         */
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L,killTime);
        System.out.println("updateCount"+updateCount);
    }

    @Test
    public void queryById() throws Exception {
        long id =1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void queryAll() throws Exception {
        /**
         *Caused by: org.apache.ibatis.binding.BindingException: Parameter 'offset' not found.
         * Available parameters are [0, 1, param1, param2]
         */
        //java没有保存形参的记录:queryAll(int offset, int limit) -->queryAll(int arg0,int arg1)
        //需要加形参注解
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        for(Seckill seckill:seckills){
            System.out.println(seckill);
        }
    }

}