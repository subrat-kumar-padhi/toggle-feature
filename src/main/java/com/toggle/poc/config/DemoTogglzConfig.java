package com.toggle.poc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.togglz.core.activation.ActivationStrategyProvider;
import org.togglz.core.activation.DefaultActivationStrategyProvider;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.manager.FeatureManagerBuilder;
import org.togglz.core.repository.StateRepository;
import org.togglz.core.repository.jdbc.JDBCStateRepository;
import org.togglz.core.spi.ActivationStrategy;
import org.togglz.core.spi.FeatureProvider;

import javax.sql.DataSource;
import java.util.List;

@Configuration
public class DemoTogglzConfig {

  @Bean
  public FeatureProvider featureProvider() {
    var featureProvider = new EnumBasedFeatureProvider();
    featureProvider.addFeatureEnum(FeatureFlags.class);
    return featureProvider;
  }

//  @Bean
//  public StateRepository stateRepository(DataSource dataSource) {
//    return new JDBCStateRepository(dataSource, "togglz_features");
//  }

  @Bean
  public ActivationStrategyProvider activationStrategyProvider(List<ActivationStrategy> activationStrategies) {
    var activationStrategyProvider = new DefaultActivationStrategyProvider();
    activationStrategyProvider.addActivationStrategies(activationStrategies);
    return activationStrategyProvider;
  }

  @Bean
  public FeatureManager featureManager(
          //StateRepository stateRepository,
                                       FeatureProvider featureProvider,
                                       ActivationStrategyProvider activationStrategyProvider) {
    return new FeatureManagerBuilder()
       // .stateRepository(stateRepository)
        .featureProvider(featureProvider)
        .activationStrategyProvider(activationStrategyProvider)
        .build();
  }
}

