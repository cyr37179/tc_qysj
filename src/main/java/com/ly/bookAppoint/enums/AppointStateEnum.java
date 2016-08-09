package com.ly.bookAppoint.enums;

/**
 * Created by cyr37179 on 2016/8/4.
 */
public enum AppointStateEnum {
    SUCCESS(1,"预定成功"),NO_NUMBER(0, "库存不足"), REPEAT_APPOINT(-1, "重复预约"), INNER_ERROR(-2, "系统异常");

    private int state;
    private  String stateInfo;

    private AppointStateEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public  static  AppointStateEnum stateOf(int index){
        for (AppointStateEnum state :values()){
            if (state.getState() == index){
                return state;
            }
        }
        return null;
    }
}
