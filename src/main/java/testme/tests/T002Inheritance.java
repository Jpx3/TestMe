package testme.tests;

import testme.Test;

public final class T002Inheritance implements Test {
  @Override
  public void test() {
    Audi audi = new Audi();
    audi.awesomeMethod();
    expectState(3);
  }

  private static int state = 0;

  public final static class Audi extends Car {
    public Object hihi = new Object();
    @Override
    public void awesomeMethod() {
      hihi = 0;
      stateChange(0, 1);
      super.awesomeMethod();
      stateChange(2, 3);
      if ((int) hihi != 0) {
        throw new AssertionError("Invalid field target");
      }
    }
  }

  public static class Car {
    public Object hihi = new Object();
    public void awesomeMethod() {
      hihi = 3;
      stateChange(1, 2);
    }
  }

  public static void stateChange(int from, int to) {
    if (state != from) {
      throw new IllegalStateException("Did not expect state change");
    }
    state = to;
  }

  public static void expectState(int requiredState) {
    if (requiredState != state) {
      throw new IllegalStateException("Expected state to be at " + requiredState + " but is at " + state);
    }
  }

  @Override
  public String name() {
    return "002Inheritance";
  }
}
