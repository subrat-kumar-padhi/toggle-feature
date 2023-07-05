package com.toggle.poc.config;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;

public enum FeatureFlags implements Feature{

    @Label("New description")
    DESCRIPTION_UPDATE,

    @Label("Discount Applied")
    DISCOUNT_APPLIED,

    @Label("Custom Strategy Testing")
    DEMO_FLAG;
}
