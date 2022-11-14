package dev.volkangurbuz.workschedule.model;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum EMonthYear {
  JANUARY(),
  FEBRUARY(),
  MARCH(),
  APRIL(),
  MAY(),
  JUNE(),
  JULY(),
  AUGUST(),
  SEPTEMBER(),
  OCTOBER(),
  NOVEMBER(),
  DECEMBER();

  private int year;

  EMonthYear(int year) {
    this.year = year;
  }

  public int getMonthOrder() {
    return this.year;
  }
}
