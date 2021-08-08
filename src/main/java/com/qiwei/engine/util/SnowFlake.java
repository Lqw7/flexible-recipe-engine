package com.qiwei.engine.util;

import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Twitter's Distributed Self-Augmenting ID Snowflake Algorithm
 **/
@Component
public class SnowFlake {

    /**
     * Start time stamp
     */
    private final static long START_STMP = 1609459200000L; // 2021-01-01 00:00:00

    /**
     * Number of bits occupied by each part
     */
    private final static long SEQUENCE_BIT = 12; //Number of digits occupied by the serial number
    private final static long MACHINE_BIT = 5;   //Number of bits occupied by machine identification
    private final static long DATACENTER_BIT = 5;//Number of bits occupied by the data centre

    /**
     * Maximum value for each part
     */
    private final static long MAX_DATACENTER_NUM = -1L ^ (-1L << DATACENTER_BIT);
    private final static long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * Displacement of each part to the left
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private long datacenterId = 1;  //Data Centres
    private long machineId = 1;     //Machine identification
    private long sequence = 0L; //Serial number
    private long lastStmp = -1L;//Last time stamp

    public SnowFlake() {
    }

    public SnowFlake(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.datacenterId = datacenterId;
        this.machineId = machineId;
    }

    /**
     * Generate the next ID
     *
     * @return
     */
    public synchronized long nextId() {
        long currStmp = getNewstmp();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStmp == lastStmp) {
            //Self-incrementing serial number within the same millisecond
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //The number of sequences in the same millisecond has reached its maximum
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            //Within different milliseconds, the serial number is set to 0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT //Timestamp section
                | datacenterId << DATACENTER_LEFT       //Data centre section
                | machineId << MACHINE_LEFT             //Machine identification section
                | sequence;                             //Serial number section
    }

    private long getNextMill() {
        long mill = getNewstmp();
        while (mill <= lastStmp) {
            mill = getNewstmp();
        }
        return mill;
    }

    private long getNewstmp() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws ParseException {
        // 时间戳
        // System.out.println(System.currentTimeMillis());
        // System.out.println(new Date().getTime());
        //
        // String dateTime = "2021-01-01 08:00:00";
        // SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        // System.out.println(sdf.parse(dateTime).getTime());

        SnowFlake snowFlake = new SnowFlake(1, 1);

        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            System.out.println(snowFlake.nextId());
            System.out.println(System.currentTimeMillis() - start);
        }
    }
}