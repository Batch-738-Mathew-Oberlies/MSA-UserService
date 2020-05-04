package com.revature.rideshare.utilities;

import com.revature.rideshare.models.*;

public class MockObjects {
    public static User getAdonis() {
        User adonis = new User();
        adonis.setUserId(1);
        adonis.setUserName("userName");
        Batch batch = new Batch();
        batch.setBatchLocation("Reston");
        batch.setBatchNumber(15);
        adonis.setBatch(batch);
        adonis.setFirstName("Adonis");
        adonis.setLastName("Cabreja");
        adonis.setEmail("adonis@gmail.com");
        adonis.setPhoneNumber("123-456-7890");
        adonis.setDriver(true);
        adonis.setActive(true);
        adonis.setAcceptingRides(true);
        Address address = new Address();
        address.setCity("Youngsville");
        address.setState("MI");
        address.setStreet("123 Fake Street");
        address.setZip("12123");
        address.setId(5);
        adonis.setHAddress(address);
        adonis.setWAddress(address);
        return adonis;
    }
}
