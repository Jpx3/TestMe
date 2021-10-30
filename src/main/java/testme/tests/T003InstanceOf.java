package testme.tests;

import testme.Test;

public final class T003InstanceOf implements Test {
  @SuppressWarnings("ConstantConditions")
  @Override
  public void test() {
    A aSpecializedA = new C();
    if (!(aSpecializedA instanceof C)) {
      throw new IllegalStateException("Invalid instanceOf result");
    }
    if (!(aSpecializedA instanceof B)) {
      throw new IllegalStateException("Invalid instanceOf result");
    }
    if (null instanceof Object) {
      throw new IllegalStateException("Null is defined not to be an instanceof anything in bytecode");
    }
    if (!(Class.class instanceof Object)) {
      throw new IllegalStateException("Class is an object");
    }
  }

  public static class A {

  }

  public static class B extends A {

  }

  public static class C extends B {

  }

  @Override
  public String name() {
    return "003InstanceOf";
  }
}
