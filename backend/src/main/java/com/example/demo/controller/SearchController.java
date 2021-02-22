package com.example.demo.controller;

import com.example.demo.model.AdvancedQuery;
import com.example.demo.model.RequiredHighlight;
import com.example.demo.model.ResultData;
import com.example.demo.model.SearchType;
import com.example.demo.model.SimpleQuery;
import com.example.demo.service.search.QueryBuilder;
import com.example.demo.service.search.ResultRetriever;
import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ResultRetriever resultRetriever;

    @PostMapping("/advanced")
    public List<ResultData> advancedSearch(@RequestParam String title,
                                           @RequestParam String keywords,
                                           @RequestParam String writer,
                                           @RequestParam String genres,
                                           @RequestParam String text,
                                           @RequestParam String operation,
                                           @RequestParam SearchType searchType) throws Exception {

        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();

        BoolQueryBuilder builder = QueryBuilders.boolQuery();

        if (!title.equals("".trim())) {
            org.elasticsearch.index.query.QueryBuilder queryBuilder = QueryBuilder.buildQuery(searchType, "title", title.toLowerCase());
            if (operation.equals("AND")) {
                builder.must(queryBuilder);
            } else {
                builder.should(queryBuilder);
            }
            rh.add(new RequiredHighlight("title", title));
        }

        if (!keywords.equals("".trim())) {
            org.elasticsearch.index.query.QueryBuilder queryBuilder = QueryBuilder.buildQuery(searchType, "keywords", keywords.toLowerCase());
            if (operation.equals("AND")) {
                builder.must(queryBuilder);
            } else {
                builder.should(queryBuilder);
            }
            rh.add(new RequiredHighlight("keywords", keywords));
        }

        if (!genres.equals("".trim())) {
            org.elasticsearch.index.query.QueryBuilder queryBuilder = QueryBuilder.buildQuery(searchType, "genres", genres.toLowerCase());
            if (operation.equals("AND")) {
                builder.must(queryBuilder);
            } else {
                builder.should(queryBuilder);
            }
            rh.add(new RequiredHighlight("genres", genres));
        }

        if (!writer.equals("".trim())) {
            org.elasticsearch.index.query.QueryBuilder queryBuilder = QueryBuilder.buildQuery(searchType, "writer", writer.toLowerCase());
            if (operation.equals("AND")) {
                builder.must(queryBuilder);
            } else {
                builder.should(queryBuilder);
            }
            rh.add(new RequiredHighlight("writer", writer));
        }

        if (!text.equals("".trim())) {
            org.elasticsearch.index.query.QueryBuilder queryBuilder = QueryBuilders.queryStringQuery(text.toLowerCase());
            if (operation.equals("AND")) {
                builder.must(queryBuilder);
            } else {
                builder.should(queryBuilder);
            }
            rh.add(new RequiredHighlight("text", text));
        }

        List<ResultData> results = resultRetriever.getResults(builder, rh);

        return results;
    }

    @PostMapping(value = "/search/term", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchTermQuery(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilder
            .buildQuery(SearchType.regular, simpleQuery.getField(), simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/fuzzy", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchFuzzy(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilder
            .buildQuery(SearchType.fuzzy, simpleQuery.getField(), simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/prefix", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchPrefix(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilder
            .buildQuery(SearchType.prefix, simpleQuery.getField(), simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/range", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchRange(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilder
            .buildQuery(SearchType.range, simpleQuery.getField(), simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/phrase", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchPhrase(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilder
            .buildQuery(SearchType.phrase, simpleQuery.getField(), simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(simpleQuery.getField(), simpleQuery.getValue()));
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/boolean", consumes = "application/json")
    public ResponseEntity<List<ResultData>> searchBoolean(@RequestBody AdvancedQuery advancedQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query1 = QueryBuilder
            .buildQuery(SearchType.regular, advancedQuery.getField1(), advancedQuery.getValue1());
        org.elasticsearch.index.query.QueryBuilder query2 = QueryBuilder
            .buildQuery(SearchType.regular, advancedQuery.getField2(), advancedQuery.getValue2());

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (advancedQuery.getOperation().equalsIgnoreCase("AND")) {
            builder.must(query1);
            builder.must(query2);
        } else if (advancedQuery.getOperation().equalsIgnoreCase("OR")) {
            builder.should(query1);
            builder.should(query2);
        } else if (advancedQuery.getOperation().equalsIgnoreCase("NOT")) {
            builder.must(query1);
            builder.mustNot(query2);
        }

        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        rh.add(new RequiredHighlight(advancedQuery.getField1(), advancedQuery.getValue1()));
        rh.add(new RequiredHighlight(advancedQuery.getField2(), advancedQuery.getValue2()));
        List<ResultData> results = resultRetriever.getResults(builder, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }

    @PostMapping(value = "/search/queryParser", consumes = "application/json")
    public ResponseEntity<List<ResultData>> search(@RequestBody SimpleQuery simpleQuery) throws Exception {
        org.elasticsearch.index.query.QueryBuilder query = QueryBuilders.queryStringQuery(simpleQuery.getValue());
        List<RequiredHighlight> rh = new ArrayList<RequiredHighlight>();
        List<ResultData> results = resultRetriever.getResults(query, rh);
        return new ResponseEntity<List<ResultData>>(results, HttpStatus.OK);
    }
}
