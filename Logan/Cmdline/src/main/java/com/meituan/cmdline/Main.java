package com.meituan.cmdline;

import com.meituan.LegacyLoganProtocol;
import com.meituan.LoganProtocol;
import com.meituan.ResultEnum;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        int result = 0;
        try {
            Arguments arguments = new Arguments();
            if (arguments.processArgs(args)) {
                if (arguments.help) {
                    arguments.usage();
                    return;
                }
                result = parse(arguments);
            }
        } catch (Throwable th) {
            System.err.println("Incorrect arguments: " + th);
            result = -1;
        } finally {
            System.exit(result);
        }
    }

    public static int parse(Arguments arguments) throws IOException {
        File input = new File(arguments.inputFile); // 原始文件路径
        File output = new File(arguments.outputFile); // 输出文件路径
        try (FileInputStream inputStream = new FileInputStream(input)) {
            LoganProtocol protocol = new LoganProtocol(inputStream, output);
            ResultEnum result = protocol.process();
            System.out.println("result: " + result);
        }
        System.out.println("output lines: " + LoganProtocol.countLines(output));

        if (arguments.legacy) {
            output = new File(output.getAbsolutePath() + ".legacy");
            try (FileInputStream inputStream = new FileInputStream(input)) {
                LegacyLoganProtocol protocol = new LegacyLoganProtocol(inputStream, output);
                ResultEnum result = protocol.process();
                System.out.println("legacy result: " + result);
            }
            System.out.println("legacy output lines: " + LoganProtocol.countLines(output));
        }

        return 0;
    }

}
