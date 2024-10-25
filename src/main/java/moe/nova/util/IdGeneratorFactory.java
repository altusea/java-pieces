package moe.nova.util;

import com.fasterxml.uuid.EthernetAddress;
import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.impl.TimeBasedGenerator;
import org.dromara.hutool.core.data.id.IdConstants;
import org.dromara.hutool.core.data.id.IdUtil;
import org.dromara.hutool.core.data.id.Snowflake;

import java.util.UUID;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class IdGeneratorFactory {

    public static TimeBasedGenerator getTimeBasedGenerator() {
        EthernetAddress ethernetAddress = EthernetAddress.fromInterface();
        return Generators.timeBasedGenerator(ethernetAddress);
    }

    public static void main(String[] args) {
        TimeBasedGenerator uuidGenerator = getTimeBasedGenerator();
        UUID uuid = uuidGenerator.generate();
        System.out.println(uuid.toString());
        System.out.println(uuid.timestamp());

        printSeparateLine("test Snowflake:");
        var snowflake = new Snowflake(IdConstants.DEFAULT_WORKER_ID, IdConstants.DEFAULT_DATACENTER_ID, true);
        System.out.println(snowflake.nextStr());
        System.out.println(snowflake.next());
        System.out.println("compare: " + snowflake.next());
        System.out.println("compare: " + Long.MAX_VALUE);
        printSeparateLine();
        System.out.println(IdUtil.fastSimpleUUID());
        System.out.println(IdUtil.simpleUUID());
    }
}
