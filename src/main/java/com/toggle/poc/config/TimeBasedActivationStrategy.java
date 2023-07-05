package com.toggle.poc.config;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.togglz.core.activation.Parameter;
import org.togglz.core.activation.ParameterBuilder;
import org.togglz.core.repository.FeatureState;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.user.FeatureUser;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class TimeBasedActivationStrategy implements ActivationStrategy {

  private static final String ID = "time-based";
  private static final String NAME = "Time based";
  private static final String START_TIME_PARAMETER = "start";
  private static final String END_TIME_PARAMETER = "end";

  private final TimeKeeper timeKeeper;

  @Override
  public String getId() {
    return ID;
  }

  @Override
  public String getName() {
    return NAME;
  }

  @Override
  public boolean isActive(FeatureState featureState,
                          FeatureUser user) {
    var now = timeKeeper.getUtcTime();

    var periodStart = LocalTime.parse(featureState.getParameter(START_TIME_PARAMETER));
    var periodEnd = LocalTime.parse(featureState.getParameter(END_TIME_PARAMETER));

    return !now.isBefore(periodStart) && !now.isAfter(periodEnd);
  }

  @Override
  public Parameter[] getParameters() {
    return new Parameter[]{
        ParameterBuilder.create(START_TIME_PARAMETER)
            .label("Start time")
            .matching("^[0-9][0-9]:[0-9][0-9]$")
            .description("Provide time in UTC for enabling the feature matching format HH:MM"),
        ParameterBuilder.create(END_TIME_PARAMETER)
            .label("End time")
            .matching("^[0-9][0-9]:[0-9][0-9]$")
            .description("Provide time in UTC for disabling the feature matching format HH:MM"),
    };
  }
}

