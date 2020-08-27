package io.github.nylonmc.core;

import java.util.concurrent.locks.ReentrantLock;

import org.beeware.rubicon.Python;

public class PythonThread {

    private PythonThread(){}
    private static String codeToRun;
    private static final ReentrantLock pythonLock = new ReentrantLock();
    private static Thread pyThread;
    private static Thread jThread;

    public static void init() {
        Thread pythonThread = new Thread(() -> {
            if (Python.init(null, Core.NYLONMC_PATH_FOLDER + ":.", null) != 0) {
                System.out.println("Error initializing Python VM.");
            }
            if (Python.run("nylonmc.main_thread", new String[0]) != 0) {
                System.out.println("Error running Python script.");
            }
        });
        pythonThread.start();
        pyThread = pythonThread;
    }

    public static void runPython(String pythonCode) {
        pythonLock.lock();
        jThread = Thread.currentThread();
        codeToRun = pythonCode;
        pyThread.resume();
        jThread.suspend();
        pythonLock.unlock();
    }

    public static Thread getjThread() {
        return jThread;
    }

    public static Thread getPyThread() {
        return pyThread;
    }

    public static String getCodeInternal() {
        return codeToRun;
    }

}