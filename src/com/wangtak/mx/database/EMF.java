package com.wangtak.mx.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class EMF {
    private static final EntityManagerFactory emfInstance =
        Persistence.createEntityManagerFactory("Mysql");

    private EMF() {}

    public static EntityManagerFactory get() {
        return emfInstance;
    }
}