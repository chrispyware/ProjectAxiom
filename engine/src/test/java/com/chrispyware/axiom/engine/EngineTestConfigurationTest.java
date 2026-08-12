package com.chrispyware.axiom.engine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.beans.Transient;

class EngineTestConfigurationTest {

    @Test
    void junitRunsInEngineModule() {
        assertEquals(4, 2 + 2);
    }
}