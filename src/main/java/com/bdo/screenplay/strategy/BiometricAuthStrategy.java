package com.bdo.screenplay.strategy;

import com.bdo.screenplay.iu.AuthStrategy;

public class BiometricAuthStrategy implements AuthStrategy {
    private String biometricData;

    public BiometricAuthStrategy(String biometricData) {
        this.biometricData = biometricData;
    }

    @Override
    public boolean authenticate(String data) {
        // Simulate biometric authentication
        return this.biometricData.equals(data);
    }
}
