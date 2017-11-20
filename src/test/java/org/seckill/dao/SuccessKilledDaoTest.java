package org.seckill.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.entity.SuccessKilled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件位置
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() throws Exception {
        long secId = 1000L;
        long phone = 13589091001L;
        int insertCount = successKilledDao.insertSuccessKilled(secId,phone);
        System.out.println("insertCount = "+insertCount);
        //主键重复不允许重复插入
    }

    @Test
    public void queryByIdWithSeckill() throws Exception {
        long secId = 1000L;
        long phone = 13589091001L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(secId,phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
        /**
         * SuccessKilled{seckillId=1000,
         * userPhone=13589091001,
         * state=0,
         * createTime=Mon Nov 20 14:24:06 CST 2017,
         *
         Seckill{seckillId=1000,
         name='1000元秒杀iphone6',
         number=100,
         startTime=Sun Nov 01 00:00:00 CST 2015,
         endTime=Mon Nov 02 00:00:00 CST 2015,
         createTime=Sun Nov 19 18:57:43 CST 2017}
         *
         *
         */
    }

}