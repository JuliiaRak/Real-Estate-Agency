package com.solvd.persistence.repositories.mybatisImpl;

import com.solvd.domain.Tag;
import com.solvd.persistence.repositories.TagRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TagTest {
    private static final Logger LOGGER = LogManager.getLogger(TagTest.class);

    public static void main(String[] args) {
        TagRepository repository = new TagRepositoryMybatisImpl();

        Tag tag = new Tag();
        tag.setName("test");
        repository.create(tag);
        tag.setName("test2");
        repository.create(tag);
        LOGGER.info("All: " + repository.findAll());
        repository.deleteByName("test2");
        LOGGER.info("All: " + repository.findAll());
        repository.deleteByName("test");
    }
}
