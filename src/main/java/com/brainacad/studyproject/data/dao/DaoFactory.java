package com.brainacad.studyproject.data.dao;

import com.brainacad.studyproject.data.dao.impl.StubDaoFactory;

/**
 * Created by User on 11/1/2016.
 */
public abstract class DaoFactory {

    public abstract UserDao getUserDao();

    public static DaoFactory getDaoFactory() {
        return new StubDaoFactory();
    }

}
