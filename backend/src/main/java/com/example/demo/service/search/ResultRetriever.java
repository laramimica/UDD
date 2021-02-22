package com.example.demo.service.search;

import com.example.demo.model.Book;
import com.example.demo.model.RequiredHighlight;
import com.example.demo.model.ResultData;
import com.example.demo.repository.IBookRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ResultRetriever {
	
	@Autowired
	private IBookRepository repository;
	
	public ResultRetriever(){
	}

	public List<ResultData> getResults(org.elasticsearch.index.query.QueryBuilder query,
			List<RequiredHighlight> requiredHighlights) {
		if (query == null) {
			return null;
		}
			
		List<ResultData> results = new ArrayList<ResultData>();
       
        for (Book book : repository.search(query)) {
        	results.add(new ResultData(book.getTitle(), book.getKeywords(), book.getFilename(), ""));
		}
        
		
		return results;
	}
}
