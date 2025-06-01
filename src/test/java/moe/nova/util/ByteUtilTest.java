package moe.nova.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ByteUtilTest {

    @Test
    public void test() {
        assertEquals(1L, ByteUtil.toNumber(new byte[]{0x01}));
        assertEquals(2L, ByteUtil.toNumber(new byte[]{0x02}));
    }
}
