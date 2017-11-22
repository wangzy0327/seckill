package org.seckill.dto;

/**
 * 暴露秒杀地址DTO
 */
public class Exposer {
    //是否开启秒杀
    private boolean exposed;
    //一种加密措施
    private String md5;

    private long seckillId;
    //系统当前时间(毫秒)
    private long now;
    //秒杀开始时间
    private long start;
    //秒杀结束时间
    private long end;

    public Exposer(boolean exposed, String md5, long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, long seckillId, String md5, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.md5 = md5;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public String getMd5() {
        return md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public long getNow() {
        return now;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
