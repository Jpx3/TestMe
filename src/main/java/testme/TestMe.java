package testme;

import testme.tests.T001HelloWorld;
import testme.tests.T002Inheritance;
import testme.tests.T003InstanceOf;
import testme.tests.T004DoubleArithmetic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class TestMe {
  private static final Map<Test, TestResult> tests = new ConcurrentHashMap<>();
  private final static String DATE_SPACING = "  ";

  public static void main(String[] args) {
    printFancyHeader();
    prepareTests();
    executeTests();
    printTestResults();
  }

  public static void printFancyHeader() {
    System.out.println();
    System.out.println(" TestMe - Java Bytecode Testing Framework");
    System.out.println(" Copyright Richy 2021, all rights reserved");
    System.out.println();
  }

  private static void prepareTests() {
    prepareTest(T001HelloWorld.class);
    prepareTest(T002Inheritance.class);
    prepareTest(T003InstanceOf.class);
    prepareTest(T004DoubleArithmetic.class);
    printMessage("Running " + tests.size() + " tests");
  }

  private static void prepareTest(Class<? extends Test> testClass) {
    try {
      tests.put(testClass.newInstance(), TestResult.pending());
    } catch (InstantiationException | IllegalAccessException exception) {
      exception.printStackTrace();
    }
  }

  private static void executeTests() {
    AsyncTaskProcessor taskProcessor = new AsyncTaskProcessor();
    taskProcessor.acquire(tests.size());
    for (Test value : tests.keySet()) {
      taskProcessor.push(() -> tests.put(value, executeTest(value)));
    }
    taskProcessor.await();
  }

  private static TestResult executeTest(Test test) {
    try {
      test.test();
      return TestResult.passed();
    } catch (Throwable exception) {
      return TestResult.failed(exception);
    }
  }

  public static void printTestResults() {
    long total = tests.size();
    long passed = tests.values().stream().filter(TestResult::hasPassed).count();
    printMessage(passed + " out of " + total + " have passed");
    if (total != passed) {
      tests.forEach((test, testResult) -> {
        if (testResult.hasFailed()) {
          wait(50);
          printError("Test " + test.name() + " has failed:");
          wait(50);
          testResult.exception().printStackTrace();
          wait(50);
        }
      });
      exit(1);
    } else {
      printMessage("Execution successful");
      exit(0);
    }
  }

  private static void exit(int code) {
    wait(500);
    System.exit(code);
  }

  private static void wait(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void printMessage(String message) {
    String timeFormatted = currentTimeFormatted();
    System.out.println(timeFormatted + DATE_SPACING + message);
  }

  private static void printError(String message) {
    String timeFormatted = currentTimeFormatted();
    System.err.println(timeFormatted + DATE_SPACING + message);
  }

  private static String currentTimeFormatted() {
    return new SimpleDateFormat("HH:mm:ss.SSS").format(new Date());
  }
}
