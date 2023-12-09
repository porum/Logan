package com.meituan.cmdline;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class JCommanderWrapper<T> {

    public final JCommander mCommander;

    JCommanderWrapper(T obj) {
        mCommander = JCommander.newBuilder().addObject(obj).build();
    }

    public boolean parse(String... args) {
        try {
            mCommander.parse(args);
            return true;
        } catch (ParameterException e) {
            System.err.println("Arguments parse error: " + e.getMessage());
            return false;
        }
    }

    public void usage() {
        mCommander.usage();
    }

}
