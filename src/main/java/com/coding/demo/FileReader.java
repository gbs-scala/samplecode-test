package com.coding.demo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FileReader {

    public List<RequestData> getFile(Reader source) {
        List<RequestData> list = new ArrayList<RequestData>();
        try {
            BufferedReader breader = new BufferedReader(source);
            list = breader.lines().skip(1).map(mountRequestLog).filter(request -> request != null)
                    .collect(Collectors.toList());
            breader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return list;
    }

    private static Function<String, RequestData> mountRequestLog = (lines) -> {
        RequestData item = new RequestData();

        List<String> listLine = Arrays.stream(lines.split(",")).filter(line -> line != null)
                .collect(Collectors.toList());

        item.setRequestTime(Long.valueOf(listLine.get(0)));
        item.setCountryCode(listLine.get(1));
        item.setResponseTime(Integer.valueOf(listLine.get(2)));
        return item;
    };
}
