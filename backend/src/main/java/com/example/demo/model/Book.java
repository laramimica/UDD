package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Getter
@Setter
@Document(indexName = "book", createIndex = false, shards = 5, replicas = 1)
public class Book {

    @Id
    @Field(type = FieldType.Keyword)
    private String filename;

    @Field(type = FieldType.Text, analyzer = "serbian")
    private String title;

    @Field(type = FieldType.Text, analyzer = "serbian")
    private String keywords;

    @Field(type = FieldType.Text, analyzer = "serbian")
    private String genres;

    @Field(type = FieldType.Text, analyzer = "serbian")
    private String writer;

    @Field(type = FieldType.Text, analyzer = "serbian")
    private String text;
}
