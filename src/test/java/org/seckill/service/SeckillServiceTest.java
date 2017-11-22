package org.seckill.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.seckill.dto.Exposer;
import org.seckill.dto.SeckillExecution;
import org.seckill.entity.Seckill;
import org.seckill.exception.RepeatKillException;
import org.seckill.exception.SeckillCloseException;
import org.seckill.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void getSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);
        /**
         * Closing non transactional SqlSession
         */
    }

    @Test
    public void getSeckillById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillService.getSeckillById(id);
        logger.info("seckill={}",seckill);
    }

    @Test
    public void exportSeckillUrl() throws Exception {
        long id = 1000L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("exposer={}"+exposer);
        /**
         * Exposer{exposed=false,
         * md5='29a452e378dca349b5968fc4b184c3b5',
         * seckillId=1000,
         * now=1511325599112,
         * start=1446307200000,
         * end=1446393600000}
         */
    }

    /**
     * 测试代码完整逻辑,注意可重复执行
     * 集成测试秒杀逻辑,注意测试重复秒杀
     * @throws Exception
     */
    @Test
    public void executeLogic() throws Exception{
        long id = 1000L;
        long userPhone = 13579099903L;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        if(exposer.isExposed()){
            logger.info("exposer={}"+exposer);
            String md5 = exposer.getMd5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id,userPhone,md5);
                logger.info("result={}"+seckillExecution);
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            }catch (SeckillException e) {
                logger.error(e.getMessage());
            }
        }else{
            //秒杀未开启
            logger.warn("exposer={}"+exposer);
            //秒杀结束
            logger.info("exposer={}"+exposer);
        }
    }

    @Test
    public void executeSeckill() throws Exception {
        long id = 1000L;
        long userPhone = 13589099903L;
        String md5 = "29a452e378dca349b5968fc4b184c3b5";

        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id,userPhone,md5);
            logger.info("execution={}"+seckillExecution);
            logger.info("seckillExeccution={}"+seckillExecution);
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        }catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        }catch (SeckillException e) {
            logger.error(e.getMessage());
        }
        /**
         * org.seckill.exception.SeckillCloseException: seckill is closed
         */
    }

}