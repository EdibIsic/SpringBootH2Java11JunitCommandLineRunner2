package com.creditsuisse.demo.service;

import com.creditsuisse.demo.DemoApplication;
import com.creditsuisse.demo.model.EventDataDb;
import com.creditsuisse.demo.model.EventDataJson;
import com.creditsuisse.demo.repository.EventDataRepository;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class JsonReaderService {
    static Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    @Autowired
    EventDataRepository repository;
    private List<EventDataJson> eventDataList;

    public JsonReaderService() {
    }

    public List<EventDataJson> readEventDataFromJson(Reader reader) throws IOException {

        try {
            logger.info("parsing event data from json started");
            Gson gson = new Gson();
            EventDataJson[] eventData = gson.fromJson(reader, EventDataJson[].class);
            eventDataList = Arrays.asList(eventData);
            reader.close();
        } catch (IOException e) {
            logger.error("Error while reading the json file");
            e.printStackTrace();
        }
        logger.info("parsing json eventData successful finished");
        return eventDataList;
    }

    public Boolean mappingToDatabase(List<EventDataJson> eventDataList) {
        logger.info("mapping from json to database eventData started ...");

        Set<String> idList = eventDataList.stream().map(EventDataJson::getId).collect(Collectors.toSet());
        for (String id : idList
        ) {
            Stream<EventDataJson> filteredEventDataList1 = eventDataList.stream().filter((item) -> item.getId().equals(id));
            Stream<EventDataJson> filteredEventDataList0 = eventDataList.stream().filter((item) -> item.getId().equals(id));
            EventDataJson startedEvent = filteredEventDataList1.filter((item) -> item.getState().equals("STARTED")).findAny().orElse(null);
            EventDataJson finishedEvent = filteredEventDataList0.filter((item) -> item.getState().equals("FINISHED")).findAny().orElse(null);
            long duration0 = finishedEvent.getTimestamp() - startedEvent.getTimestamp();
            Boolean alert;
            if (duration0 > 4) {
                alert = true;
            } else {
                alert = false;
            }
            try {
                this.repository.save(new EventDataDb(id, duration0, startedEvent.getType(), startedEvent.getHost(), alert));
                logger.info("mapping to Database Repository action successful");

            } catch (Exception e) {
                logger.error("Exception in database mapping occurred");
                e.printStackTrace();
                return false;

            }
        }
        return true;

    }
}
