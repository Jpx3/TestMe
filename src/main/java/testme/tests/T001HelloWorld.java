package testme.tests;

import testme.AssertThat;
import testme.Test;

public final class T001HelloWorld implements Test {
  @Override
  public void test() {
    output("Test");
  }

  private void output(String input) {
    AssertThat.equal(input, "Test");
  }

  @Override
  public String name() {
    return "001HelloWorld";
  }
}
