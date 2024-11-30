package entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonUserFactoryTest {
    @Test
    void CommonUserFactoryWorkingTest(){
        CommonUserFactory CUF = new CommonUserFactory();
        Assertions.assertEquals(CUF.create("Name", "Password").getUsername(), "Name");
        Assertions.assertEquals(CUF.create("Name", "Password").getPassword(), "Password");
    }
}
