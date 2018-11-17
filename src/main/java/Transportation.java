public interface Transportation {
    final static int READY_MIN = 45;
    final static int BREAK_TIME_MIN = 30;
    double calculateBreakTime();
    double getTotalTimeTravel();
    String toString();
}
