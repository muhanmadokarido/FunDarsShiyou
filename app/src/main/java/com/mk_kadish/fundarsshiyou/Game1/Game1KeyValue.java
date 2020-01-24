package com.mk_kadish.fundarsshiyou.Game1;

public class Game1KeyValue
{
    public  int key;
    public int value;
    public int used;

    public int getUsed() {
        return used;
    }

    public void setUsed(int used) {
        this.used = used;
    }

    public Game1KeyValue(int key, int value)
    {
        this.key = key;
        this.value = value;
        this.used=0;
    }


}