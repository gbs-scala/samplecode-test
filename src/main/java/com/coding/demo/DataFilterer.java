package com.coding.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class DataFilterer {

    public static Collection<?> filterByCountry(Reader source, String country) {
        Collection<RequestData> itemsList = readItems(source);
        return itemsList
                .stream()
                .filter(i -> i.isCountryCode(country))
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public static Collection<?> filterByCountryWithResponseTimeAboveLimit(Reader source, String country, long limit) {
        Collection<RequestData> itemsList = readItems(source);

        return itemsList
                .stream()
                .filter(i -> i.isCountryCode(country) && i.isResponseTimeAboveLimit(limit))
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public static Collection<?> filterByResponseTimeAboveAverage(Reader source) {
        Collection<RequestData> itemsList = readItems(source);

        double average = itemsList
                .stream()
                .mapToDouble(RequestData::getResponseTime)
                .average().orElse(0);

        return itemsList.stream()
                .filter(i -> i.getResponseTime() > average)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public static Collection<RequestData> readItems(Reader source){
        Collection<RequestData> items = new ArrayList<RequestData>();
        BufferedReader inBufferReader = new BufferedReader(source);
        String str;
        try {
            inBufferReader.readLine();
            while ((str = inBufferReader.readLine()) != null) {
                RequestData item = new RequestData(str);
                items.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
}