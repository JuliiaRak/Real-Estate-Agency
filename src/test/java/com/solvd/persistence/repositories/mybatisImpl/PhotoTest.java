package com.solvd.persistence.repositories.mybatisImpl;

import com.solvd.domain.Photo;
import com.solvd.persistence.repositories.PhotoRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PhotoTest {
    private static final Logger LOGGER = LogManager.getLogger(PhotoTest.class);

    public static void main(String[] args) {
        PhotoRepository repository = new PhotoRepositoryMybatisImpl();

        Photo photo = new Photo();
        photo.setLink("g.com/p1");
        repository.create(photo, 1);
        photo.setLink("g.com/p2");
        repository.create(photo, 1);
        photo.setLink("g.com/p3");
        repository.create(photo, 2);
        LOGGER.info("All: " + repository.findAll());
        LOGGER.info("All by RE with id 1: " + repository.findAllByRealEstateId(1));
        LOGGER.info("All by RE with id 2: " + repository.findAllByRealEstateId(2));
        repository.deleteByRealEstateId(1);
        LOGGER.info("Should be empty -> " + repository.findAllByRealEstateId(1));
        LOGGER.info("Should be true -> " + repository.findAllByRealEstateId(1).isEmpty());
        repository.deleteByRealEstateId(2);
    }
}
