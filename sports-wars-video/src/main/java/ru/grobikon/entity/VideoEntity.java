package ru.grobikon.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;
import java.util.UUID;

/**
 * Информация о видео
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "video_metadata")
public class VideoEntity {

    /**
     *  Идентификатор записи
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Название файла с видео
     */
    @Column
    private String fileName;

    /**
     * Тип файла
     */
    @Column
    private String contentType;

    /**
     * Описание
     */
    @Column
    private String description;

    /**
     * Размер видео
     */
    @Column
    private Long fileSize;

    /**
     * Длина видео
     */
    @Column
    private Long videoLength;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        VideoEntity videoEntity = (VideoEntity) o;
        return id != null && Objects.equals(id, videoEntity.id);
    }

    @Override
    public int hashCode() {
        return id != null ? Objects.hashCode(id) : getClass().hashCode();
    }
}
