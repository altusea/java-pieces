package moe.nova.playground;

import com.fasterxml.uuid.Generators;
import com.fasterxml.uuid.NoArgGenerator;
import com.fasterxml.uuid.impl.UUIDUtil;
import org.dromara.hutool.core.data.id.NanoId;
import org.dromara.hutool.core.util.ByteUtil;

import java.util.UUID;

import static moe.nova.util.ConsoleUtil.printSeparateLine;

public class UUIDTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("generate a nanoid which is url-friendly:");
        System.out.println(NanoId.randomNanoId());

        printSeparateLine("timeBasedGenerator");
        NoArgGenerator v1Gen = Generators.timeBasedGenerator();
        UUID v1 = v1Gen.generate();
        System.out.println(v1Gen.getType() + ": " + v1);
        byte[] v1Bytes = UUIDUtil.asByteArray(v1);
        System.out.println("v1Long: " + ByteUtil.toLong(v1Bytes));

        printSeparateLine("timeBasedReorderedGenerator");
        NoArgGenerator v6Gen = Generators.timeBasedReorderedGenerator();
        UUID v6 = v6Gen.generate();
        System.out.println(v6Gen.generate() + ": " + v6);
        byte[] v6Bytes = UUIDUtil.asByteArray(v6);
        System.out.println("v6Long: " + ByteUtil.toLong(v6Bytes));

        printSeparateLine("timeBasedEpochGenerator");
        NoArgGenerator v7Gen = Generators.timeBasedEpochGenerator();
        UUID v7 = v7Gen.generate();
        System.out.println(v7Gen.getType() + ": " + v7);
        byte[] v7Bytes = UUIDUtil.asByteArray(v7);
        System.out.println("v7Long: " + ByteUtil.toLong(v7Bytes));
    }
}
