package com.example.carrentalproject;

public class MockUserRepository implements UserRepository {
    @Override
    public boolean registerUser(User user) {
        return true;
    }
}
