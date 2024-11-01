package com.delivery.cargo.chain;

import com.delivery.cargo.model.PackageEntity;

public class DeliveryState implements PackageState {

    @Override
    public void next(PackageEntity packageEntity) {
        packageEntity.setState(new DeliveredState());
        System.out.println("Package delivered.");
    }

    @Override
    public void prev(PackageEntity packageEntity) {
        packageEntity.setState(new InTransitState());
        System.out.println("Package reverted to in transit.");
    }

    @Override
    public void printStatus() {
        System.out.println("Package out for delivery.");
    }
}
