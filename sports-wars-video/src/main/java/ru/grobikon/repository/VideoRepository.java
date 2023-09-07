package ru.grobikon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.grobikon.entity.VideoEntity;

import java.util.UUID;

public interface VideoRepository extends JpaRepository<VideoEntity, UUID> {
}
