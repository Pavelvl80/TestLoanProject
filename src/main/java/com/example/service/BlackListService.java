package com.example.service;

import com.example.model.BlackList;
import com.example.model.Person;

/**
 * Created by Edvard Piri on 05.12.2017.
 */
public interface BlackListService {
    boolean isPersonBlackListed(long id);

    BlackList addPersonToBlackList(BlackList blackList);

    BlackList removePersonFromBlackList(BlackList blackList);
}
