package com.demo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Runner de Cucumber con JUnit Platform Suite.
 *
 * <p>Esta clase conecta los archivos .feature de {@code src/test/resources/features/}
 * con los Step Definitions del paquete {@code com.demo.steps}, y activa los plugins
 * de reporte de Serenity.
 *
 * <p>Para ejecutar solo los tests de Cucumber:
 * <pre>
 *   ./gradlew clean test --tests "com.demo.CucumberTestSuite" aggregate
 * </pre>
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.demo.steps")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "io.cucumber.core.plugin.SerenityReporterParallel,pretty")
public class CucumberTestSuite {
    // Esta clase sirve únicamente como punto de entrada del runner.
    // No contiene código de prueba propio.
}
