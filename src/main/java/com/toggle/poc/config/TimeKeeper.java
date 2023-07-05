package com.toggle.poc.config;

import java.time.LocalTime;
import org.springframework.stereotype.Service;

@Service
public class TimeKeeper {

  public LocalTime getUtcTime() {
    return LocalTime.now();
  }
}
