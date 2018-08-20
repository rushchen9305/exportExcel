package cn.rush;

import cn.rush.tools.ClassAnalysis;
import org.junit.Test;

import java.util.Date;
import java.util.List;

public class TestAnnotation {

    @Test
    public void testGetTableHead(){
        List<String> list = ClassAnalysis.getDescs(new BeseCommon());
        for (String  s : list){
            System.out.println(s);
        }
    }
    @Test
    public void testGetTablBody(){
        BeseCommon beseCommon = new BeseCommon();
        beseCommon.setTestBoolean(true);
        beseCommon.setTestInt(2);
        beseCommon.setTestByte(Byte.parseByte("66"));
        beseCommon.setTestChar('5');
        beseCommon.setTestDouble(444D);
        beseCommon.setTestStr("2333");
        beseCommon.setTestShort(Short.parseShort("333"));
        beseCommon.setDate(new Date());
        List<String> list = ClassAnalysis.getFieldsValue(beseCommon);
        for (String  s : list){
            System.out.println(s);
        }
    }

}
