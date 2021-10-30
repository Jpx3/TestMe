package testme.tests;

import testme.Test;

@SuppressWarnings({"ConstantConditions", "divzero", "NumericOverflow"})
public final class T004DoubleArithmetic implements Test {
  @Override
  public void test() {
    testConv();
    testAdd();
    testSubtract();
    testMultiply();
    testDivide();
    testInverse();
    testNaN();
    testInfinity();
  }

  private void testConv() {
    double val = 36322.58593259;
    validateDouble(val, 0x40e1bc52bff5b3f7L);
  }

  private void testAdd() {
    double valA = 7313.51353;
    double valB = -Math.PI;
    double result = valA + valB;
    validateDouble(result, 0x40bc8e5f374932feL);
  }

  private void testSubtract() {
    double valA = 123.456898621;
    double valB = 987.543212346;
    double result = valA - valB;
    validateDouble(result, 0xc08b00b0c5401092L);
  }

  private void testMultiply() {
    double valA = 123.456898621;
    double valB = 987.543212346;
    double result = valA * valB;
    validateDouble(result, 0x40fdc3f05b234b44L);
  }

  private void testDivide() {
    double valA = 123.456898621;
    double valB = 987.543212346;
    double result = valA / valB;
    validateDouble(result, 0x3fc00076e5a6a429L);
  }

  private void testNaN() {
    double nan = 0.0d / 0.0d;
    if (!Double.isNaN(nan)) {
      throw new IllegalStateException("Zero division does not produce a NaN, a " + nan + " instead");
    }

    double negNan = -0.0d / -0.0d;
    if (!Double.isNaN(negNan)) {
      throw new IllegalStateException("Negative zero division does not produce a NaN, a " + nan + " instead");
    }
  }

  private void testInfinity() {
    double inf = 1.0d / 0.0d;
    if (!Double.isInfinite(inf)) {
      throw new IllegalStateException("Zero division does not produce a infinite value, a " + inf + " instead");
    }

    double negInf = -1.0d / -0.0d;
    if (!Double.isInfinite(negInf)) {
      throw new IllegalStateException("Zero division does not produce a infinite value, a " + inf + " instead");
    }
  }

  private void testInverse() {
    double value = 154.7345491854421;
    value = -value;
    validateDouble(value, 0xc06357816d4b18dfL);
  }

  private void validateDouble(double input, long rawBits) {
    if (Double.doubleToRawLongBits(input) != rawBits) {
      throw new IllegalStateException(input + " does not match expected " + Double.longBitsToDouble(rawBits));
    }
  }

  @Override
  public String name() {
    return "004DoubleArithmetic";
  }
}
