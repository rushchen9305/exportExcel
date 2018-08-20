package cn.rush;


import cn.rush.annotation.Explanation;

import java.util.Date;

public class BeseCommon {

    @Explanation(desc = "str")
    private String testStr;

    @Explanation(desc = "long")
    private Long testLong;

    private Integer testInt;

    private Character testChar;

    private Float testFloat;

    @Explanation(desc = "double",method = "cn.rush.tools.NumberUtils#round",param = {"#{testInt}"})
    private Double testDouble;

    private Boolean testBoolean;

    private Short testShort;

    private Byte testByte;

    @Explanation(desc = "date" ,method="cn.rush.tools.DateUtils#parse",param = {"${cn.rush.tools.DateUtils#YYYYMMDD}"})
    private Date date;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public Long getTestLong() {
        return testLong;
    }

    public void setTestLong(Long testLong) {
        this.testLong = testLong;
    }

    public Integer getTestInt() {
        return testInt;
    }

    public void setTestInt(Integer testInt) {
        this.testInt = testInt;
    }

    public Character getTestChar() {
        return testChar;
    }

    public void setTestChar(Character testChar) {
        this.testChar = testChar;
    }

    public Float getTestFloat() {
        return testFloat;
    }

    public void setTestFloat(Float testFloat) {
        this.testFloat = testFloat;
    }

    public Double getTestDouble() {
        return testDouble;
    }

    public void setTestDouble(Double testDouble) {
        this.testDouble = testDouble;
    }

    public Boolean getTestBoolean() {
        return testBoolean;
    }

    public void setTestBoolean(Boolean testBoolean) {
        this.testBoolean = testBoolean;
    }

    public Short getTestShort() {
        return testShort;
    }

    public void setTestShort(Short testShort) {
        this.testShort = testShort;
    }

    public Byte getTestByte() {
        return testByte;
    }

    public void setTestByte(Byte testByte) {
        this.testByte = testByte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
