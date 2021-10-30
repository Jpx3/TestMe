package testme;

public final class AssertThat {
  public static void isTrue(boolean bool) {
    if (!bool) {
      throw new AssertionError("Expected input to be <true>, but is <false>");
    }
  }

  public static void equal(Object a, Object b) {
    if (!a.equals(b)) {
      throw new AssertionError("Expected " + a + " and " + b + " to be equal");
    }
  }

  public static void equal(int a, int b) {
    if (a != b) {
      throw new AssertionError("Expected " + a + " and " + b + " to be equal");
    }
  }
}
