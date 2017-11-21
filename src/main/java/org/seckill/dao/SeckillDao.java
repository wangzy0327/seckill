package org.seckill.dao;

import org.apache.ibatis.annotations.Param;
import org.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     * 如果影响行数>1,表示更新的记录行数
     */
    int reduceNumber(@Param("seckillId")long seckillId,@Param("killTime")Date killTime);

    /**
     * 根据id查询秒杀对象
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 根据偏移量查询秒杀商品列表
     * @param offset
     * @param limit
     * @return
     */
    //需要加注解@Param(""),否则java将方法加载后, queryAll(int offset,int limit) -> queryAll(arg0,arg1)
    // 注入sql参数时，找不到
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit")int limit);
}
