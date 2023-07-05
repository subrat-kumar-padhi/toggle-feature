//package com.toggle.poc;
//
//import jdk.jfr.Label;
//import org.togglz.core.Feature;
//import org.togglz.core.context.FeatureContext;
//
//public enum MyFeatures implements Feature {
//
//    @Label("First Feature")
//    FEATURE_ONE;
//
////    @Label("Second Feature")
////    @ActiveByDefault
////
////    FEATURE_TWO;
//
//    public boolean isActive() {
//        return FeatureContext.getFeatureManager().isActive(this);
//    }
//}
