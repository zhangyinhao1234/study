package com.github.zhangyinhao1234.study.drools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yinhao.zhang
 * @Date 2020/3/16 2:27 下午
 **/

public class PointParams {
    /**
     * 签到时间
     */
    List<String> checkDate =new ArrayList<>();
    /**
     * 最近七天的时间
     */
    List<String> last7Day =new ArrayList<>();

    int point = 0;

    int checkCount = 0;

    public int getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(int checkCount) {
        this.checkCount = checkCount;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public List<String> getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(List<String> checkDate) {
        this.checkDate = checkDate;
    }

    public List<String> getLast7Day() {
        return last7Day;
    }

    public void setLast7Day(List<String> last7Day) {
        this.last7Day = last7Day;
    }
}
