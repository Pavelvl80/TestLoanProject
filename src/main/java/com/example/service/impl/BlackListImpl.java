package com.example.service.impl;

import com.example.dao.BlackListDao;
import com.example.model.BlackList;
import com.example.model.Person;
import com.example.service.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
@Service
public class BlackListImpl implements BlackListService {
    private final BlackListDao blackListDao;

    @Autowired
    public BlackListImpl(BlackListDao blackListDao) {
        this.blackListDao = blackListDao;
    }

    @Override
    public boolean isPersonBlackListed(long id) {
        return blackListDao.findByPerson(new Person(id)) != null;
    }

    @Override
    public BlackList addPersonToBlackList(BlackList blackList) {
        return blackListDao.save(blackList);
    }

    @Override
    public BlackList removePersonFromBlackList(BlackList blackList) {
        blackListDao.delete(blackList);
        return blackList;
    }
}
