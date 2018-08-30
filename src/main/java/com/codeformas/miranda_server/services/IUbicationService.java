package com.codeformas.miranda_server.services;

import com.codeformas.miranda_server.model.domain.Ubication;
import java.util.HashMap;

public interface IUbicationService {
    HashMap listAll();

    HashMap getById(Long id);

    HashMap saveOrUpdate(Ubication ubication);

    HashMap delete(Ubication ubication);

}
