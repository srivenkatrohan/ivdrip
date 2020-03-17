package com.example.ivdrip;

public class DripRateMember {
    private Integer Vol;
    private Integer Drop;
    private Integer Duration;
    private Integer Output;
    private boolean Switch;

    public DripRateMember() {
    }

    public Integer getVol() {
        return Vol;
    }

    public void setVol(Integer vol) {
        Vol = vol;
    }

    public Integer getDrop() {
        return Drop;
    }

    public void setDrop(Integer drop) {
        Drop = drop;
    }

    public Integer getDuration() {
        return Duration;
    }

    public void setDuration(Integer duration) {
        Duration = duration;
    }

    public Integer getOutput() {
        return Output;
    }

    public void setOutput(Integer output) {
        Output = output;
    }

    public boolean isSwitch() {
        return Switch;
    }

    public void setSwitch(boolean aSwitch) {
        Switch = aSwitch;
    }
}
