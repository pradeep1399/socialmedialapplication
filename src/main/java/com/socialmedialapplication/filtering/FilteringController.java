package com.socialmedialapplication.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/static-filtering")
    public SomeBean staticfFltering(){
        return new SomeBean("value1", "value2", "value3", "value4");
    }

    @GetMapping("/static-filtering-list")
    public List<SomeBean> staticFilteringlist(){
        return Arrays.asList(new SomeBean("value1", "value2", "value3", "value4"),
        new SomeBean("value5", "value6", "value7", "value8"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3", "value4");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field4");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue dynamicFilteringlist(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3", "value4"),
                new SomeBean("value5", "value6", "value7", "value8"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field3", "field4");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return  mappingJacksonValue;
    }
}
