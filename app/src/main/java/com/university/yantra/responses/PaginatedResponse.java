package com.university.yantra.responses;

import com.university.yantra.models.Course;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rishabh on 3/9/2018.
 */

public class PaginatedResponse<T> {
    private Integer count;
    private Object next;
    private Object previous;
    private List<T> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Object getNext() {
        return next;
    }

    public void setNext(Object next) {
        this.next = next;
    }

    public Object getPrevious() {
        return previous;
    }

    public void setPrevious(Object previous) {
        this.previous = previous;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
