package com.github.elliothb.daocli;

import picocli.CommandLine;

public class App {
    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new Compile());
        int exitCode = commandLine.execute(args);
        System.exit(exitCode);
    }
}
