package testme;

public final class TestResult {
  private final State state;
  private final Throwable exception;

  private TestResult(State state) {
    this.state = state;
    exception = null;
  }

  private TestResult(Throwable exception) {
    state = State.FAILED;
    this.exception = exception;
  }

  public Throwable exception() {
    return exception;
  }

  public boolean hasPassed() {
    return this == PASSED || state == State.PASSED;
  }

  public boolean isPending() {
    return this == PENDING || state == State.PENDING;
  }

  public boolean hasFailed() {
    return exception != null;
  }

  private final static TestResult PASSED = new TestResult(State.PASSED);
  private final static TestResult PENDING = new TestResult(State.PENDING);

  public static TestResult passed() {
    return PASSED;
  }

  public static TestResult pending() {
    return PENDING;
  }

  public static TestResult failed(Throwable exception) {
    return new TestResult(exception);
  }

  public enum State {
    PASSED,
    FAILED,
    PENDING
  }
}
