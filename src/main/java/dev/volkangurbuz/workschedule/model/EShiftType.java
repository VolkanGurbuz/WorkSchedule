package dev.volkangurbuz.workschedule.model;

public enum EShiftType {
  MORNING(8, 16),
  EVENING(16, 24),
  NIGHT(24, 8);

  EShiftType(int shiftStart, int shiftFinish) {}
}
