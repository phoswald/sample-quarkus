package com.github.phoswald.sample;

import io.quarkus.test.junit.QuarkusIntegrationTest;

@QuarkusIntegrationTest
public class ApplicationIT extends ApplicationTest {

    // Execute the same tests but in native mode.
    // NOTE:
    // - not executed by default because of "<skipITs>true</skipITs>" in pom.xml.
    // - this uses properties from src/main/resources, not src/test/resources!
}
