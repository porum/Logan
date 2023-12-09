package com.meituan.cmdline;

import com.beust.jcommander.Parameter;

public class Arguments {
    @Parameter(names = "--help", help = true)
    boolean help;

    @Parameter(description = "<input file>")
    String inputFile;

    @Parameter(names = {"-out"}, description = "output file")
    String outputFile;

    @Parameter(names = {"-legacy"}, description = "use legacy protocol")
    boolean legacy;

    private final JCommanderWrapper<Arguments> mJCommanderWrapper = new JCommanderWrapper<>(this);

    public boolean processArgs(String... args) {
        return mJCommanderWrapper.parse(args);
    }

    public void usage() {
        mJCommanderWrapper.usage();
    }

}
